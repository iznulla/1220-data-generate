package com.uzinfo.datagenerate.web.service.datasource;

import com.uzinfo.datagenerate.web.configuration.datasource.*;
import com.uzinfo.datagenerate.web.dto.datasource.DataSourceDto;
import com.uzinfo.datagenerate.web.entity.DataBaseEntity;
import com.uzinfo.datagenerate.web.entity.mapper.DataBaseEntityMapper;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.repository.base.DataSourcePropertiesRepository;
import com.uzinfo.datagenerate.web.service.table.TableService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@Service
public class DataSourcePropertiesService {
    private final DataSourcePropertiesRepository dataSourcePropertiesRepository;

    private final TableService tableService;

    private final DataSourceTwoConfig dataSourceTwoConfig;
    private  final DataSourceRouting dataSourceRouting;
    private final DataBaseEntityMapper dataBaseEntityMapper;

    @Transactional
    public String setDataSourceProperties(Long id) {
        DataSourceDto dataSourceDto = DataSourceDto.from(dataSourcePropertiesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Data Source not found with id " + id)
        ));
        try {
            dataSourceTwoConfig.setJdbcUrl(dataSourceDto.getUrl());
            dataSourceTwoConfig.setUsername(dataSourceDto.getUsername());
            dataSourceTwoConfig.setPassword(dataSourceDto.getPassword());
            dataSourceTwoConfig.setDriverClassName(dataSourceDto.getDriver());
            Map<Object, Object> dataSourceMap = new HashMap<>();
            dataSourceMap.put(DataSourceEnum.DATASOURCE_DEST, dataSourceRouting.dataSourceTwoDataSource());
            dataSourceRouting.setDataSourceMap(dataSourceMap);
            DataSourceContextHolder.clearBranchContext();
            DataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_DEST);
            tableService.getTables().orElseThrow();

        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        return String.format("Data Source %s set successfully", dataSourceDto.getName());
    }

    public DataSourceDto create(DataSourceDto dataSourceDto) {
        try {
            if (dataSourceDto.getId() != null) {
                DataBaseEntity dataBaseEntity = dataSourcePropertiesRepository.findById(dataSourceDto.getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Data Source not found with id " + dataSourceDto.getId())
                );
                dataBaseEntityMapper.updateDataBaseEntityFromDTO(dataSourceDto, dataBaseEntity);
                DataBaseEntity dataBaseEntitySaved = dataSourcePropertiesRepository.save(dataBaseEntity);
                DataSourceDto dataSourceDtoResponse = DataSourceDto.from(dataBaseEntitySaved);
                dataSourceDtoResponse.setAddedStatus(true);
                return dataSourceDtoResponse;
            } else {
                DataBaseEntity dataBaseEntity = dataBaseEntityMapper.fromDTO(dataSourceDto);
                DataBaseEntity savedDataBase = dataSourcePropertiesRepository.save(dataBaseEntity);
                DataSourceDto dataSourceDtoResponse = DataSourceDto.from(savedDataBase);
                dataSourceDtoResponse.setAddedStatus(true);
                return dataSourceDtoResponse;
            }
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

    public void deleteById(Long id) {
        if (!dataSourcePropertiesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Data Source not found with id " + id);
        }
        dataSourcePropertiesRepository.deleteById(id);
    }

    public List<DataSourceDto> getAll() {
        return dataSourcePropertiesRepository.findAll().stream().map(DataSourceDto::from).toList();
    }

}
