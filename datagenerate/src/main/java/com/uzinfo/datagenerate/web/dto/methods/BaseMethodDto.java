package com.uzinfo.datagenerate.web.dto.methods;

import com.uzinfo.datagenerate.web.entity.BaseMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseMethodDto {
    private String name;
    private String description;
    private List<MethodValuesDto> methodValues;

    public static BaseMethodDto from(BaseMethod baseMethod) {
        return BaseMethodDto.builder()
                .name(baseMethod.getName())
                .description(baseMethod.getDescription())
                .methodValues(baseMethod.getMethodValues().stream().map(MethodValuesDto::from).toList())
                .build();
    }
}
