package com.car.rental.application.queries.cars.getall;

import com.car.rental.application.shared.dto.cars.CarBaseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class GetAllCarsQueryResponse extends CarBaseDTO {
    private final String status;
    private final LocalDateTime createdAt;
}
