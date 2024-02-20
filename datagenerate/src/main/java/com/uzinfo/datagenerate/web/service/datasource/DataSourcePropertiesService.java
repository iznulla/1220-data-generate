package com.uzinfo.datagenerate.web.service.datasource;

import com.uzinfo.datagenerate.web.configuration.datasource.*;
import com.uzinfo.datagenerate.web.dto.datasource.DataSourceDto;
import com.uzinfo.datagenerate.web.entity.DataBaseEntity;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.repository.base.DataSourcePropertiesRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@Service
public class DataSourcePropertiesService {
    private final DataSourcePropertiesRepository dataSourcePropertiesRepository;

    private final DataSourceTwoConfig dataSourceTwoConfig;
    private  final DataSourceRouting dataSourceRouting;

    public String setDataSourceProperties(String name) {
        DataSourceDto dataSourceDto = DataSourceDto.from(dataSourcePropertiesRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Data Source not found with id " + name)
        ));
        try {
            dataSourceRouting.getConnection().close();
            dataSourceTwoConfig.setJdbcUrl(dataSourceDto.getUrl());
            dataSourceTwoConfig.setUsername(dataSourceDto.getUsername());
            dataSourceTwoConfig.setPassword(dataSourceDto.getPassword());
            dataSourceTwoConfig.setDriverClassName(dataSourceDto.getDriver());
            Map<Object, Object> dataSourceMap = new HashMap<>();
            dataSourceMap.put(DataSourceEnum.DATASOURCE_DEST, dataSourceRouting.dataSourceTwoDataSource());
            dataSourceRouting.setDataSourceMap(dataSourceMap);
            DataSourceContextHolder.clearBranchContext();
            DataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_DEST);

        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        return String.format("Data Source %s set successfully", dataSourceDto.getName());
    }

    public DataSourceDto create(DataSourceDto dataSourceDto) {
        try {
            DataBaseEntity dataBaseEntity = new DataBaseEntity();
            dataBaseEntity.setName(dataSourceDto.getName());
            dataBaseEntity.setUrl(dataSourceDto.getUrl());
            dataBaseEntity.setUsername(dataSourceDto.getUsername());
            dataBaseEntity.setPassword(dataSourceDto.getPassword());
            dataBaseEntity.setDriver(dataSourceDto.getDriver());
            dataSourcePropertiesRepository.save(dataBaseEntity);
            return DataSourceDto.from(dataBaseEntity);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    public DataSourceDto getByName(String name) {
        return DataSourceDto.from(dataSourcePropertiesRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Data Source not found with name " + name)
        ));
    }

    public DataSourceDto getById(Long id) {
        return DataSourceDto.from(dataSourcePropertiesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Data Source not found with id " + id)
        ));
    }

    public List<DataSourceDto> getAll() {
        return dataSourcePropertiesRepository.findAll().stream().map(DataSourceDto::from).toList();
    }

}
