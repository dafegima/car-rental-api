package com.car.rental.application.commands.rentals.reserve;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.car.rental.application.Constants.API_PATH_RENTALS;
import static com.car.rental.application.Constants.API_PATH_RENTALS_CREATE_RENT_ENDPOINT;

@RestController
@RequestMapping(API_PATH_RENTALS)
public class RentCarEndpoint {

    private final RentCarCommandHandler rentCarCommandHandler;

    public RentCarEndpoint(RentCarCommandHandler rentCarCommandHandler) {
        this.rentCarCommandHandler = rentCarCommandHandler;
    }

    @PostMapping(API_PATH_RENTALS_CREATE_RENT_ENDPOINT)
    public ResponseEntity<RentCarCommandResponse> rentCar(@RequestBody RentCarCommand rentCarCommand) {
        RentCarCommandResponse carRental = rentCarCommandHandler.handle(rentCarCommand);
        return ResponseEntity.ok(carRental);
    }
}
