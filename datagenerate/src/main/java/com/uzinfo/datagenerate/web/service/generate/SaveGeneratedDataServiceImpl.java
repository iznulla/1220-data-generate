package com.uzinfo.datagenerate.web.service.generate;

import com.uzinfo.datagenerate.dataapp.generate.FakeGenerator;
import com.uzinfo.datagenerate.web.dto.FieldsDto;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.repository.app.SaveGeneratedDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
//@Transactional
public class SaveGeneratedDataServiceImpl implements SaveGeneratedDataService {
    private final SaveGeneratedDataRepository saveGeneratedDataRepository;

    @Override
    public int save(FieldsDto fieldsDto) {
        long startTime = System.currentTimeMillis();
        try {
            List<Map<String, Object>> model = FakeGenerator.generate(fieldsDto);
            for (Map<String, Object> map : model) {
                try {
                    saveGeneratedDataRepository.save(fieldsDto.getTableName(), map);
                } catch (Exception e) {
                    throw new ResourceNotFoundException("Не верные данные в таблице " + fieldsDto.getTableName()
                            + "\n" + e.getMessage());
                }
            }
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println("Время выполнения цикла: " + (elapsedTime / 1000) + " секунд");
            return 0;
        } catch (Exception e) {
            throw  new ResourceNotFoundException(e.getMessage());
        }

    }
}
