package com.car.rental.application.shared.dto.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@SuperBuilder
public class CarBaseDTO implements Serializable {
    private final String licensePlate;
    private final String brand;
    private final String model;
    private final Integer year;
    private final String color;
    private final String gearType;
    private final String type;
}
