package com.uzinfo.datagenerate.web.dto.datasource;

import com.uzinfo.datagenerate.web.entity.DataBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataSourceDto {
    private Long id;
    private String name;
    private String url;
    private String username;
    private String password;
    private String description;
    private String driver;
    private boolean addedStatus;

    public static DataSourceDto from(DataBaseEntity dataSourceEntity) {
        return DataSourceDto.builder()
                .id(dataSourceEntity.getId())
                .name(dataSourceEntity.getName())
                .url(dataSourceEntity.getUrl())
                .username(dataSourceEntity.getUsername())
                .password(dataSourceEntity.getPassword())
                .description(dataSourceEntity.getDescription())
                .driver(dataSourceEntity.getDriver())
                .addedStatus(false)
                .build();
    }
}
