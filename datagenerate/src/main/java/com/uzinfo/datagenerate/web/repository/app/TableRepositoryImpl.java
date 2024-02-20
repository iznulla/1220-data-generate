package com.uzinfo.datagenerate.web.repository.app;

import com.uzinfo.datagenerate.web.configuration.datasource.DataSourceRouting;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.model.TableModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Repository
@RequiredArgsConstructor
public class TableRepositoryImpl implements TableRepository {
    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    @Qualifier("appDataSource")
//    private DataSource dataSource;
//
    private final DataSourceRouting dataSourceRouting;

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
            List<TableModel> tableModel = new ArrayList<>();
            while (tablesRS.next()) {
                TableModel table = new TableModel();
                ResultSet columnsRS = metaData.getColumns(null, null, tablesRS.getString("TABLE_NAME"), null);
                table.setTableName(tablesRS.getString("TABLE_NAME"));
                List<String> columns = new ArrayList<>();
                while (columnsRS.next()) {
                    columns.add(columnsRS.getString("COLUMN_NAME"));
                }
                table.setColumns(columns);
                tableModel.add(table);
            }
            return Optional.of(tableModel);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Tables not found\n" + e.getMessage());
        }
    }
}

