package com.uzinfo.datagenerate.web.dto.methods;

import com.uzinfo.datagenerate.web.entity.MethodValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MethodValuesDto {
    private String methodValue;
    private String description;
    private boolean hasParams;
    private Integer paramsMaxCount;
    private String baseClass;

    public static MethodValuesDto from(MethodValues methodValues) {
        return MethodValuesDto.builder()
                .methodValue(methodValues.getMethodValue())
                .description(methodValues.getDescription())
                .hasParams(methodValues.isHasParams())
                .paramsMaxCount(methodValues.getParamsMaxCount())
                .baseClass(methodValues.getBaseMethod().getName())
                .build();
    }
}