package com.uzinfo.datagenerate.web.api.methods;

import com.uzinfo.datagenerate.web.dto.methods.BaseMethodDto;
import com.uzinfo.datagenerate.web.entity.BaseMethod;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.service.faker.FakerBaseClassService;
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
@RequestMapping("/api/v1/base-faker-classes")
public class BaseFakerClassesApi {
    private final FakerBaseClassService methodService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = BaseMethod.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "POST create Base Class with methods",
            description = "Добавляет в базу данных базовый класс и его метоы из библиотеки DataFaker"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody BaseMethodDto baseMethod) {
        return new ResponseEntity<>(methodService.create(baseMethod), HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = BaseMethod.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "PATCH update Base Class with methods",
            description = "Обновляет в базе данных базовый класс и его метоы из библиотеки DataFaker"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BaseMethodDto baseMethod) {
        return new ResponseEntity<>(methodService.update(id, baseMethod), HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = BaseMethod.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET get all classes and methods",
            description = "Получает все названия базовых классов библиотеки DataFaker и его методы из базы данных"
    )
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(methodService.getAll(), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = BaseMethod.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET class and methods by id",
            description = "Получает классы и его методы по id из базы данных"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(methodService.getById(id), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = BaseMethod.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET classes and method by name",
            description = "Получает классы и его метод по названию из базы данных"
    )
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return new ResponseEntity<>(methodService.getByName(name), HttpStatus.OK);
    }

}
