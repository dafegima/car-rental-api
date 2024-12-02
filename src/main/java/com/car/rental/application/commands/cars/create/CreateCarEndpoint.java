package com.car.rental.application.commands.cars.create;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.car.rental.application.Constants.API_PATH_CARS;

@RestController
@RequestMapping(API_PATH_CARS)
public class CreateCarEndpoint {

    private final CreateCarCommandHandler createCarCommandHandler;

    public CreateCarEndpoint(CreateCarCommandHandler createCarCommandHandler) {
        this.createCarCommandHandler = createCarCommandHandler;
    }

    @PostMapping()
    public ResponseEntity<CreateCarCommandResponse> createCar(@RequestBody CreateCarCommand carRequest){
        CreateCarCommandResponse response = createCarCommandHandler.handle(carRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
