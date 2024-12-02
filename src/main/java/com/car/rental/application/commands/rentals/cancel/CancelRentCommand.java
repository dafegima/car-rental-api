package com.car.rental.application.commands.rentals.cancel;

import com.car.rental.application.shared.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CancelRentCommand implements Request<CancelRentCommandResponse> {
    private String rentalId;
}
