package com.uzinfo.datagenerate.web.api.save;

//import com.uzinfo.datagenerate.web.configuration.datasource.DataSourceContextHolder;
//import com.uzinfo.datagenerate.web.configuration.datasource.DataSourceEnum;
import com.uzinfo.datagenerate.web.dto.FieldsDto;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.service.generate.SaveGeneratedDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/generate")
public class SaveDataApi {
    private final SaveGeneratedDataService saveGeneratedDataService;
//    private final DataSourceContextHolder dataSourceContextHolder;

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = FieldsDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "POST generate and fill data in fields",
            description = "Добавляет в базу данных сгенерированные данные"
    )
    @PostMapping
    public ResponseEntity<?> fill(@RequestBody FieldsDto fieldsDto) {
//        dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_DEST);
        return new ResponseEntity<>(saveGeneratedDataService.save(fieldsDto), HttpStatus.CREATED);
    }
}
