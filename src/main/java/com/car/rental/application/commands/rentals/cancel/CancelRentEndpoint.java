package com.car.rental.application.commands.rentals.cancel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.car.rental.application.Constants.API_PATH_RENTALS;
import static com.car.rental.application.Constants.API_PATH_RENTALS_CANCEL_ENDPOINT;

@RestController
@RequestMapping(API_PATH_RENTALS)
public class CancelRentEndpoint {

    private final CancelRentCommandHandler cancelRentCommandHandler;

    public CancelRentEndpoint(CancelRentCommandHandler cancelRentCommandHandler) {
        this.cancelRentCommandHandler = cancelRentCommandHandler;
    }

    @PostMapping(API_PATH_RENTALS_CANCEL_ENDPOINT)
    public ResponseEntity<CancelRentCommandResponse> cancelRent(@PathVariable("rentalId") String rentalId) {
        CancelRentCommandResponse response = cancelRentCommandHandler.handle(new CancelRentCommand(rentalId));
        return ResponseEntity.ok(response);
    }
}
