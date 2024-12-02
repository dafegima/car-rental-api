package com.car.rental.application.commands.rentals.confirm;

import com.car.rental.application.shared.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmRentCommand implements Request<ConfirmRentCommandResponse> {
    private String rentalId;
}
