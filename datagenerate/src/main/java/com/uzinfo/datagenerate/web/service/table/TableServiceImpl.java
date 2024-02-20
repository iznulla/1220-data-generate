package com.uzinfo.datagenerate.web.service.table;

import com.uzinfo.datagenerate.web.configuration.datasource.DataSourceContextHolder;
import com.uzinfo.datagenerate.web.configuration.datasource.DataSourceEnum;
import com.uzinfo.datagenerate.web.dto.table.TableDto;
import com.uzinfo.datagenerate.web.model.TableModel;
import com.uzinfo.datagenerate.web.repository.app.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;
    @Override
    public void create(TableDto sql) {
        tableRepository.createTable(sql.getSchema());
    }

    @Override
    public Optional<List<TableModel>> getTables() {
        DataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_DEST);
        return Optional.of(tableRepository.getTables().orElseThrow());
    }
}
