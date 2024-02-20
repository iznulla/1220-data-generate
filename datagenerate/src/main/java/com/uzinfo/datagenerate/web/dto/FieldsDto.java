package com.uzinfo.datagenerate.web.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FieldsDto {
    String tableName;
    List<FieldDto> fields;
    int count;
}
