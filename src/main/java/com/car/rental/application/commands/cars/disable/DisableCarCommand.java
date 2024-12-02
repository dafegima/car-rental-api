package com.car.rental.application.commands.cars.disable;

import com.car.rental.application.shared.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DisableCarCommand implements Request<DisableCarCommandResponse> {
    private String licensePlate;
}