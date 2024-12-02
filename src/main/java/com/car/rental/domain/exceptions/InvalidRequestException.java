package com.car.rental.domain.exceptions;

import com.car.rental.domain.exceptions.models.InvalidRequestExceptionModel;
import lombok.Data;

import java.util.List;

@Data
public class InvalidRequestException extends RuntimeException {
    private List<InvalidRequestExceptionModel> errors;

    public InvalidRequestException(List<InvalidRequestExceptionModel> messages) {
        this.errors = messages;
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
