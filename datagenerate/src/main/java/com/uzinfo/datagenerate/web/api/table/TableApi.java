package com.uzinfo.datagenerate.web.api.table;

import com.uzinfo.datagenerate.web.dto.table.TableDto;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import com.uzinfo.datagenerate.web.model.TableModel;
import com.uzinfo.datagenerate.web.service.table.TableService;
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
@RequestMapping("/api/v1/table")
@CrossOrigin
public class TableApi {

    private final TableService tableService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TableDto tableDto) {
        tableService.create(tableDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

        @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = TableModel.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResourceNotFoundException.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Operation(
            summary = "GET get All Tables and Fields from current database",
            description = "Получает все таблицы и полей из базы данных"
    )
    @GetMapping("/api/v1/tables")
    public ResponseEntity<?> getTables() {
            return new ResponseEntity<>(tableService.getTables(), HttpStatus.OK);
    }

}
