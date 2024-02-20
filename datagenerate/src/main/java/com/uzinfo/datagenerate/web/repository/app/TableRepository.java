package com.uzinfo.datagenerate.web.repository.app;

import com.uzinfo.datagenerate.web.model.TableModel;

import java.util.List;
import java.util.Optional;



public interface TableRepository {
    void createTable(String sql);

    Optional<List<TableModel>> getTables();
}
