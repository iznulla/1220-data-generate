package com.uzinfo.datagenerate.dataapp.exceptions;

import lombok.Getter;

@Getter
public class InvalidFakerResources extends ReflectiveOperationException {
    public InvalidFakerResources(String message) {
        super(message);
    }
}
