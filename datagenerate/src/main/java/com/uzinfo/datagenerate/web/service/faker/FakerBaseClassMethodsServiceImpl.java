package com.uzinfo.datagenerate.web.service.faker;

import com.uzinfo.datagenerate.web.dto.methods.MethodValuesDto;
import com.uzinfo.datagenerate.web.entity.MethodValues;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.repository.base.FakerBaseClassMethodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FakerBaseClassMethodsServiceImpl {

    private final FakerBaseClassMethodsRepository fakerBaseClassMethodsRepository;

    public void save(List<MethodValues> methodValues) {
        fakerBaseClassMethodsRepository.saveAll(methodValues);
    }

    public Optional<List<MethodValuesDto>> getAll() {
        List<MethodValues> methodValues = fakerBaseClassMethodsRepository.findAll();
        List<MethodValuesDto> methodValuesDtos = new ArrayList<>();
        for (MethodValues methodValue : methodValues) {
            methodValuesDtos.add(MethodValuesDto.from(methodValue));
        }
        return Optional.of(methodValuesDtos);
    }

//    @Override
    public void delete(Long id) {
        MethodValues methodValues = fakerBaseClassMethodsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Method with id " + id + " not found")
        );
        fakerBaseClassMethodsRepository.deleteById(methodValues.getId());
    }

    public Optional<MethodValuesDto> getById(Long id) {
        return Optional.of(MethodValuesDto.from(fakerBaseClassMethodsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Method with id " + id + " not found")
        )));
    }

//    @Override
    public Optional<MethodValuesDto> create(MethodValuesDto methodValuesDto) {
        MethodValues methodValues = MethodValues.builder()
                .methodValue(methodValuesDto.getMethodValue())
                .description(methodValuesDto.getDescription())
                .hasParams(methodValuesDto.isHasParams())
                .paramsMaxCount(methodValuesDto.getParamsMaxCount())
                .build();
        fakerBaseClassMethodsRepository.save(methodValues);
        return Optional.of(MethodValuesDto.from(methodValues));
    }

    public Optional<MethodValuesDto> update(Long id, MethodValuesDto methodValuesDto) {
        MethodValues methodValues = fakerBaseClassMethodsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Method with id " + id + " not found")
        );
        methodValues.setMethodValue(methodValuesDto.getMethodValue());
        methodValues.setDescription(methodValuesDto.getDescription());
        methodValues.setHasParams(methodValuesDto.isHasParams());
        methodValues.setParamsMaxCount(methodValuesDto.getParamsMaxCount());
        fakerBaseClassMethodsRepository.save(methodValues);
        return Optional.of(MethodValuesDto.from(methodValues));
    }

    public Optional<List<MethodValuesDto>> getByName(String name) {
        return Optional.of(fakerBaseClassMethodsRepository.findAllBymethodValue(name).orElseThrow(
                () -> new ResourceNotFoundException("Method with name " + name + " not found")
        ).stream().map(MethodValuesDto::from).toList());
    }
}
