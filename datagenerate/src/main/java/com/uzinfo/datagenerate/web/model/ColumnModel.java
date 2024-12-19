package com.uzinfo.datagenerate.web.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColumnModel {
    private String name;
    private String type;
    private String nullable;
    private String isAutoIncrement;
    private String isGeneratedColumn;
    private String defaultValue;
    private String columnSize;
    private short keySeq;
    private String fKeyTableName;
}
