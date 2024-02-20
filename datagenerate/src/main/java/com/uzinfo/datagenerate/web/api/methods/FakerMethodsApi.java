package com.uzinfo.datagenerate.web.api.methods;

import com.uzinfo.datagenerate.web.dto.methods.MethodValuesDto;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.service.faker.FakerBaseClassMethodsServiceImpl;
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
@RequestMapping("/api/v1/methods")
public class FakerMethodsApi {
    private final FakerBaseClassMethodsServiceImpl fakerBaseClassMethodsService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = MethodValuesDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "POST create method",
            description = "Добавляет в базу данных метод из библиотеки DataFaker"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MethodValuesDto methodValuesDto) {
        return new ResponseEntity<>(fakerBaseClassMethodsService.create(methodValuesDto), HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = MethodValuesDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "PATCH update method",
            description = "Обновляет метод из библиотеки DataFaker в базе данных"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MethodValuesDto methodValuesDto) {
        return new ResponseEntity<>(fakerBaseClassMethodsService.update(id, methodValuesDto), HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = MethodValuesDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET get all name methods",
            description = "Получает все названия методов из базы данных"
    )
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(fakerBaseClassMethodsService.getAll(), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = MethodValuesDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET method by id",
            description = "Получает метод по id из базы данных"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(fakerBaseClassMethodsService.getById(id), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = MethodValuesDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET methods by name",
            description = "Получает методы по названию из базы данных"
    )
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return new ResponseEntity<>(fakerBaseClassMethodsService.getByName(name), HttpStatus.OK);
    }

    @ApiResponses({
//            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = MethodValuesDto.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "DELETE method by ID",
            description = "Удаляет метод по Id из базы данных"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        fakerBaseClassMethodsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
