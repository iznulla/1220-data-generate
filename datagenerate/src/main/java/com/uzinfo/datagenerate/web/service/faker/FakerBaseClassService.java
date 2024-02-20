package com.uzinfo.datagenerate.web.service.faker;

import com.uzinfo.datagenerate.web.dto.methods.BaseMethodDto;

import java.util.List;
import java.util.Optional;

public interface FakerBaseClassService {
    Optional<BaseMethodDto> getByName(String name);
    Optional<BaseMethodDto> getById(Long id);
    Optional<BaseMethodDto> create(BaseMethodDto baseMethodDto);
    Optional<BaseMethodDto> update(Long id, BaseMethodDto baseMethod);
    Optional<List<BaseMethodDto>> getAll();
    void delete(Long id);

}
