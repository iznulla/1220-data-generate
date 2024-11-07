package com.uzinfo.datagenerate.web.repository.app;

import com.uzinfo.datagenerate.web.configuration.datasource.DataSourceRouting;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.model.TableModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<List<TableModel>> getTables(int page, int pageSize) {
        try {
            DatabaseMetaData metaData = dataSourceRouting.getConnection().getMetaData();
            ResultSet tablesRS = metaData.getTables(null, "ORA_USER", null, new String[]{"TABLE"});
            List<TableModel> tableModelList = new ArrayList<>();
//            int start = (page - 1) * pageSize;
//            int end = start + pageSize;
//            int currentIndex = 0;

            // Перебор записей с учетом пагинации
            while (tablesRS.next()) {
//                if (currentIndex >= start && currentIndex < end) {
                    TableModel table = new TableModel();
                    table.setTableName(tablesRS.getString("TABLE_NAME"));

                    ResultSet columnsRS = metaData.getColumns(null, null, tablesRS.getString("TABLE_NAME"), null);
                    List<String> columns = new ArrayList<>();
                    while (columnsRS.next()) {
                        columns.add(columnsRS.getString("COLUMN_NAME") + " " + columnsRS.getString("TYPE_NAME"));
                    }
                    table.setColumns(columns);
                    tableModelList.add(table);
                }
//                if (currentIndex >= end) {
//                    break;
//                }
//                currentIndex++;
//            }
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

