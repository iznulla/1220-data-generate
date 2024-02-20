package com.uzinfo.datagenerate.web.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FieldDto {
    String fieldName;
    String generateBaseType;
    String generateValue;
    List<FilterParamsDto> filterParamsDtoList;
}
