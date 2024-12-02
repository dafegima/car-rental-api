package com.car.rental.application.commands.cars.disable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.car.rental.application.Constants.API_PATH_CARS;
import static com.car.rental.application.Constants.API_PATH_CARS_DISABLE_ENDPOINT;

@RestController
@RequestMapping(API_PATH_CARS)
public class DisableCarEndpoint {

    private final DisableCarCommandHandler disableCarCommandHandler;

    public DisableCarEndpoint(DisableCarCommandHandler disableCarCommandHandler) {
        this.disableCarCommandHandler = disableCarCommandHandler;
    }

    @PatchMapping(API_PATH_CARS_DISABLE_ENDPOINT)
    public ResponseEntity<DisableCarCommandResponse> deleteCar(@PathVariable String licensePlate) {
        DisableCarCommandResponse carCommandResponse = disableCarCommandHandler.handle(new DisableCarCommand(licensePlate));
        return ResponseEntity.ok(carCommandResponse);
    }
}
