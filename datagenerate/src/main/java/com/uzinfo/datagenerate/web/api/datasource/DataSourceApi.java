package com.uzinfo.datagenerate.web.api.datasource;

import com.uzinfo.datagenerate.web.dto.datasource.DataSourceDto;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.service.datasource.DataSourcePropertiesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/datasource")
@CrossOrigin
public class DataSourceApi {
    private final DataSourcePropertiesService dataSourcePropertiesService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DataSourceDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "POST set data source properties and select database by name",
            description = "Устанавливает свойства базы данных и выбирает базу данных по названию из базы данных"
    )
    @PostMapping("/property/{name}")
    public ResponseEntity<?> setProperties(@PathVariable String name) {
        return new ResponseEntity<>(dataSourcePropertiesService.setDataSourceProperties(name), HttpStatus.CREATED);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DataSourceDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "POST create data source properties",
            description = "Добавляет в базу данных свойства базы данных"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody DataSourceDto dataSourceDto) {
        return new ResponseEntity<>(dataSourcePropertiesService.create(dataSourceDto), HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DataSourceDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET get all data source properties",
            description = "Получает все свойства базы данных из базы данных"
    )
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(dataSourcePropertiesService.getAll(), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DataSourceDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET get data source properties by id",
            description = "Получает свойства базы данных по id из базы данных"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(dataSourcePropertiesService.getById(id), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DataSourceDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET data source properties by name",
            description = "Получает свойства базы данных по названию из базы данных"
    )
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return new ResponseEntity<>(dataSourcePropertiesService.getByName(name), HttpStatus.OK);
    }


}
