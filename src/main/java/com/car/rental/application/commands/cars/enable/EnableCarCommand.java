package com.car.rental.application.commands.cars.enable;

import com.car.rental.application.shared.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnableCarCommand implements Request<EnableCarCommandResponse> {
    private String licensePlate;
}
