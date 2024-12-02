package com.car.rental.application.commands.cars.enable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.car.rental.application.Constants.API_PATH_CARS;
import static com.car.rental.application.Constants.API_PATH_CARS_ENABLE_ENDPOINT;

@RestController
@RequestMapping(API_PATH_CARS)
public class EnableCarEndpoint {

    private final EnableCarCommandHandler enableCarCommandHandler;

    public EnableCarEndpoint(EnableCarCommandHandler enableCarCommandHandler) {
        this.enableCarCommandHandler = enableCarCommandHandler;
    }

    @PatchMapping(API_PATH_CARS_ENABLE_ENDPOINT)
    public ResponseEntity<EnableCarCommandResponse> deleteCar(@PathVariable String licensePlate) {
        EnableCarCommandResponse carCommandResponse = enableCarCommandHandler.handle(new EnableCarCommand(licensePlate));
        return ResponseEntity.ok(carCommandResponse);
    }
}
