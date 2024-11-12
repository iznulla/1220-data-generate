package com.uzinfo.datagenerate.web.model;

import lombok.*;

import java.util.Map;

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
    private short keySeq;
    private boolean isFk;
}
