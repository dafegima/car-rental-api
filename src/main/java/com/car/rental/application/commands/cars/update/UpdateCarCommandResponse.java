package com.car.rental.application.commands.cars.update;

import com.car.rental.application.shared.dto.cars.CarBaseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class UpdateCarCommandResponse extends CarBaseDTO {
    private final String status;
    private final LocalDateTime createdAt;
}
