package com.car.rental.application.commands.cars.update;

import com.car.rental.application.shared.Request;
import com.car.rental.application.shared.dto.cars.CarBaseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UpdateCarCommand extends CarBaseDTO implements Request<UpdateCarCommandResponse> {
    public UpdateCarCommand(String licensePlate, String brand, String model, Integer year, String color, String gearType, String type) {
        super(licensePlate, brand, model, year, color, gearType, type);
    }
}
