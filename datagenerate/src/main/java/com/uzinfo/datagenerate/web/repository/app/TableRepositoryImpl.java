package com.uzinfo.datagenerate.web.repository.app;

import com.uzinfo.datagenerate.web.configuration.datasource.DataSourceRouting;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.model.ColumnModel;
import com.uzinfo.datagenerate.web.model.TableModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

@Data
@Repository
@RequiredArgsConstructor
public class TableRepositoryImpl implements TableRepository {
    private final JdbcTemplate jdbcTemplate;

    private final DataSourceRouting dataSourceRouting;
    private List<TableModel> tableModelListProxy = new ArrayList<>();

    public void createTable(String sql) {
        try {
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Table is not created\n" + e.getMessage());

        }
    }

    @Override
    public Optional<List<TableModel>> getTables() {
        List<TableModel> tableModelList = new ArrayList<>();

        try (Connection connection = dataSourceRouting.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            try (ResultSet tablesRS = metaData.getTables(null, (metaData.getUserName() == null || metaData.getUserName().equals("user")) ? null : metaData.getUserName(), null, new String[]{"TABLE"})) {
                System.out.println("FFFFF" + metaData.getUserName());
                while (tablesRS.next()) {
                    String tableName = tablesRS.getString("TABLE_NAME");
                    TableModel table = new TableModel();
                    table.setTableName(tableName);

                    // Получение первичного ключа
                    try (ResultSet pKey = metaData.getPrimaryKeys(null, null, tableName)) {
                        if (pKey.next()) {
                            table.setPrimaryKeyName(pKey.getString("PK_NAME"));
                        }
                    }

                    // Получение внешних ключей
                    Map<String, String> fkColumnsAndTables = new HashMap<>();
                    try (ResultSet importedKeys = metaData.getImportedKeys(null, null, tableName)) {
                        while (importedKeys.next()) {
                            String fkColumnName = importedKeys.getString("FKCOLUMN_NAME");
                            String pkTableName = importedKeys.getString("PKTABLE_NAME");
                            fkColumnsAndTables.put(fkColumnName, pkTableName);
                        }
                    }
                    table.setFkColumnsAndTables(fkColumnsAndTables);

                    // Получение списка столбцов таблицы

                    try (ResultSet columnsRS = metaData.getColumns(null, metaData.getUserName(), tableName, null)) {
                        List<ColumnModel> columns = new ArrayList<>();
                        while (columnsRS.next()) {
                            ColumnModel column = new ColumnModel();
                            column.setName(columnsRS.getString("COLUMN_NAME"));
                            column.setType(columnsRS.getString("TYPE_NAME"));
                            column.setNullable(columnsRS.getString("IS_NULLABLE"));
                            column.setIsAutoIncrement(columnsRS.getString("IS_AUTOINCREMENT"));
                            column.setIsGeneratedColumn(columnsRS.getString("IS_GENERATEDCOLUMN"));
                            columns.add(column);
                        }
                        table.setColumns(columns);
                        table.setColumnsCount(columns.size());
                    }


                    tableModelList.add(table);
                }
            }

            this.tableModelListProxy.clear();
            this.tableModelListProxy = tableModelList;
            return Optional.of(tableModelList);

        } catch (Exception e) {
            throw new ResourceNotFoundException("Tables not found\n" + e.getMessage());
        }
    }


    @Override
    public Optional<List<TableModel>> getTablesProxy() {
        try {
            return Optional.of(tableModelListProxy);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Tables not found\n" + e.getMessage());
        }
    }

}

