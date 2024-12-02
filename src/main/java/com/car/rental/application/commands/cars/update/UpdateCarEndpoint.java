package com.car.rental.application.commands.cars.update;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.car.rental.application.Constants.API_PATH_CARS;

@RestController
@RequestMapping(API_PATH_CARS)
public class UpdateCarEndpoint {

    private final UpdateCarCommandHandler updateCarCommandHandler;

    public UpdateCarEndpoint(UpdateCarCommandHandler updateCarCommandHandler) {
        this.updateCarCommandHandler = updateCarCommandHandler;
    }

    @PutMapping()
    public ResponseEntity<UpdateCarCommandResponse> updateCar(@RequestBody UpdateCarCommand command) {
        UpdateCarCommandResponse response = updateCarCommandHandler.handle(command);
        return ResponseEntity.ok(response);
    }
}
