package com.uzinfo.datagenerate.web.repository.app;

import com.uzinfo.datagenerate.web.configuration.datasource.DataSourceRouting;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.model.ColumnModel;
import com.uzinfo.datagenerate.web.model.TableModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        try {
            DatabaseMetaData metaData = dataSourceRouting.getConnection().getMetaData();
            ResultSet tablesRS = metaData.getTables(null, null, null, new String[]{"TABLE"});
            List<TableModel> tableModelList = new ArrayList<>();
            while (tablesRS.next()) {
                String tableName = tablesRS.getString("TABLE_NAME");
                TableModel table = new TableModel();
                table.setTableName(tableName);


                ResultSet pKey = metaData.getPrimaryKeys(null, null, tableName);
                if (pKey.last()) {
                    table.setPrimaryKeyName(pKey.getString("PK_NAME"));
                }

                ResultSet importedKeys = metaData.getImportedKeys(null, null, tableName);
                Map<String, String> fkColumnsAndTables = new HashMap<>();
                while (importedKeys.next()) {
                    String fkColumnName = importedKeys.getString("PKCOLUMN_NAME");
                    String fkTableName = importedKeys.getString("PKTABLE_NAME");
                    fkColumnsAndTables.put(fkColumnName, fkTableName);
                }

                List<ColumnModel> columns = new ArrayList<>();
                ResultSet columnsRS = metaData.getColumns(null, null, tablesRS.getString("TABLE_NAME"), null);
                while (columnsRS.next()) {
                    ColumnModel column = new ColumnModel();
                    String columnName = columnsRS.getString("COLUMN_NAME");
                    column.setName(columnName);
                    column.setType(columnsRS.getString("TYPE_NAME"));
                    column.setNullable(columnsRS.getString("IS_NULLABLE"));
                    column.setIsAutoIncrement(columnsRS.getString("IS_AUTOINCREMENT"));
                    column.setIsGeneratedColumn(columnsRS.getString("IS_GENERATEDCOLUMN"));
//                    column.setKeySeq(columnsRS.getShort("KEY_SEQ"));
                    if (fkColumnsAndTables.containsKey(columnName)) {
                        column.setFk(true);
                        column.setForeignKeyTable(fkColumnsAndTables.get(columnName));
                    }
                    columns.add(column);
                }
                table.setColumns(columns);
                table.setColumnsCount(columns.size());

                tableModelList.add(table);
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

