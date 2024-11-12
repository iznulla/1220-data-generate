package com.uzinfo.datagenerate.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableModel {
    private String tableName;
    private String primaryKeyName;
    private int columnsCount;
    private Long tableLastId;
    private List<ColumnModel> columns;
    private Map<String, String> fkColumnsAndTables;
}
