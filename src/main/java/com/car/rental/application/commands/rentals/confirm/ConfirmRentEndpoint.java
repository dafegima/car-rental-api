package com.car.rental.application.commands.rentals.confirm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.car.rental.application.Constants.API_PATH_RENTALS;
import static com.car.rental.application.Constants.API_PATH_RENTALS_CONFIRM_ENDPOINT;

@RestController
@RequestMapping(API_PATH_RENTALS)
public class ConfirmRentEndpoint {

    private final ConfirmRentCommandHandler confirmRentCommandHandler;

    public ConfirmRentEndpoint(ConfirmRentCommandHandler confirmRentCommandHandler) {
        this.confirmRentCommandHandler = confirmRentCommandHandler;
    }

    @PostMapping(API_PATH_RENTALS_CONFIRM_ENDPOINT)
    public ResponseEntity<ConfirmRentCommandResponse> confirmRent(@PathVariable("rentalId") String rentalId) {
        ConfirmRentCommand confirmRentCommand = new ConfirmRentCommand(rentalId);
        ConfirmRentCommandResponse response = confirmRentCommandHandler.handle(confirmRentCommand);
        return ResponseEntity.ok(response);
    }
}
