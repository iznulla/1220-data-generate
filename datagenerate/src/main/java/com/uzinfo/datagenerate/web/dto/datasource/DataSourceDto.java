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
    private String name;
    private String url;
    private String username;
    private String password;
    private String driver;

    public static DataSourceDto from(DataBaseEntity dataSourceEntity) {
        return DataSourceDto.builder()
                .name(dataSourceEntity.getName())
                .url(dataSourceEntity.getUrl())
                .username(dataSourceEntity.getUsername())
                .password(dataSourceEntity.getPassword())
                .driver(dataSourceEntity.getDriver())
                .build();
    }
}
