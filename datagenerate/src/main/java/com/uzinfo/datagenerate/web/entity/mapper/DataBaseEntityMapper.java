package com.uzinfo.datagenerate.web.entity.mapper;

import com.uzinfo.datagenerate.web.dto.datasource.DataSourceDto;
import com.uzinfo.datagenerate.web.entity.DataBaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DataBaseEntityMapper {

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "addedStatus", ignore = true)
    DataBaseEntity fromDTO(DataSourceDto source);

    DataSourceDto fromDataBaseEntity(DataBaseEntity source);

    @Mapping(target = "id", ignore = true)
    void updateDataBaseEntityFromDTO(DataSourceDto source, @MappingTarget DataBaseEntity target);
}
