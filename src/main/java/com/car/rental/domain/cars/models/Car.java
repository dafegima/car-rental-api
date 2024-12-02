package com.car.rental.domain.cars.models;

import com.car.rental.domain.cars.enums.CarStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Car {
    private String licensePlate;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private String gearType;
    private String type;
    private CarStatus status;
    private LocalDateTime createdAt;

    public boolean isEnable(){
        return status.equals(CarStatus.ENABLED);
    }

    public boolean isDisable() {
        return status.equals(CarStatus.DISABLED);
    }

    public void disable() {
        this.status = CarStatus.DISABLED;
    }

    public void enable() {
        this.status = CarStatus.ENABLED;
    }
}
