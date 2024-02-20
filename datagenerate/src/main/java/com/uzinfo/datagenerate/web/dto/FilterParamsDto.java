package com.uzinfo.datagenerate.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilterParamsDto {
    private boolean isActive;
    private String valueType;
    private String value;
}
