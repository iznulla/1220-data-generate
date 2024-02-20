package com.uzinfo.datagenerate.web.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AppError {
    private final UUID id;
    private final String message;

    public AppError(UUID id, String message) {
        this.id = id;
        this.message = message;
    }
}
