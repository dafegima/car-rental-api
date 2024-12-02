package com.car.rental.repository.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CarEntity {
    private String licensePlate;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private String gearType;
    private String type;
    private String status;
    private LocalDateTime createdAt;
}
