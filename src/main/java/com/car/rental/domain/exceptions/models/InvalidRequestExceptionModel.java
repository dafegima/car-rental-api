package com.car.rental.domain.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class InvalidRequestExceptionModel implements Serializable {
    private String property;
    private String message;
}
