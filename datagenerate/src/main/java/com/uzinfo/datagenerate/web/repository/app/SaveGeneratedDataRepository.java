package com.uzinfo.datagenerate.web.repository.app;

import com.uzinfo.datagenerate.web.model.TableModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface SaveGeneratedDataRepository {
    int save(String tableNAme, Map<String, Object> generatedValue);
//    Optional<List<TableModel>> getTables();
}
