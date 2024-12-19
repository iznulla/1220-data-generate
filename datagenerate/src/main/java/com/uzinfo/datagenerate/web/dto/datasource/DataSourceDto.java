package com.uzinfo.datagenerate.web.dto.datasource;

import com.uzinfo.datagenerate.web.entity.enums.Database;
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
    private Database database;
    private boolean addedStatus;
}
