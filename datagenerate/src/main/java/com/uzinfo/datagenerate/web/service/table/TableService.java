package com.uzinfo.datagenerate.web.service.table;

import com.uzinfo.datagenerate.web.dto.table.TableDto;
import com.uzinfo.datagenerate.web.model.TableModel;

import java.util.List;
import java.util.Optional;

public interface TableService {
    void create(TableDto sql);
    Optional<List<TableModel>> getTables();
}
