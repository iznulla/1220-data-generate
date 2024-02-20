package com.uzinfo.datagenerate.web.service.faker;

import com.uzinfo.datagenerate.web.dto.methods.BaseMethodDto;
import com.uzinfo.datagenerate.web.dto.methods.MethodValuesDto;
import com.uzinfo.datagenerate.web.entity.BaseMethod;
import com.uzinfo.datagenerate.web.entity.MethodValues;
import com.uzinfo.datagenerate.web.repository.base.*;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FakerBaseClassServiceImpl implements FakerBaseClassService {

    private final FakerBaseClassRepository fakerBaseClassRepository;
    @Override
    public Optional<BaseMethodDto> getByName(String name) {
        return Optional.of(BaseMethodDto.from(fakerBaseClassRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("BaseClass with name: " + name + " not found")
        )));
    }

    @Override
    public Optional<BaseMethodDto> getById(Long id) {
        return Optional.of(BaseMethodDto.from(fakerBaseClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("BaseClass with id: " + id + " not found")
        )));
    }

    @Override
    public Optional<BaseMethodDto> create(BaseMethodDto baseMethodDto) {
        try {
            List<MethodValues> methodValuesList = new ArrayList<>();
            BaseMethod baseMethod = BaseMethod.builder()
                    .name(baseMethodDto.getName())
                    .description(baseMethodDto.getDescription())
                    .methodValues(methodValuesList)
                    .build();
            for (MethodValuesDto methodValuesDto : baseMethodDto.getMethodValues()) {
                MethodValues methodValues = MethodValues.builder()
                        .methodValue(methodValuesDto.getMethodValue())
                        .hasParams(methodValuesDto.isHasParams())
                        .description(methodValuesDto.getDescription())
                        .paramsMaxCount(methodValuesDto.getParamsMaxCount())
                        .build();
                methodValues.setBaseMethod(baseMethod);
                methodValuesList.add(methodValues);
            }
            fakerBaseClassRepository.save(baseMethod);
            return Optional.of(baseMethodDto);
        } catch (Exception e) {
            throw new ResourceNotFoundException("BaseClass with name: " + baseMethodDto.getName() + " not found");
        }
    }

    @Override
    public Optional<BaseMethodDto> update(Long id, BaseMethodDto baseMethod) {
        BaseMethod baseClass = fakerBaseClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("BaseClass with id: " + id + " not found")
        );
        baseClass.setName(baseMethod.getName());
        baseClass.setDescription(baseMethod.getDescription());
        fakerBaseClassRepository.save(baseClass);

        return Optional.of(
                BaseMethodDto.from(baseClass)
        );
    }

    @Override
    public Optional<List<BaseMethodDto>> getAll() {
        try {
            List<BaseMethod> baseMethods = fakerBaseClassRepository.findAll();
            return Optional.of(baseMethods.stream().map(BaseMethodDto::from).toList());
        } catch (Exception e) {
            throw new ResourceNotFoundException("BaseClasses not found");
        }

    }

    @Override
    public void  delete(Long id) {
        BaseMethod baseClass = fakerBaseClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("BaseClass with id: " + id + " not found")
        );
        fakerBaseClassRepository.deleteById(baseClass.getId());
    }
}
