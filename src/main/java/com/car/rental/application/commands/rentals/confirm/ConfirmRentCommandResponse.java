package com.car.rental.application.commands.rentals.confirm;

import com.car.rental.application.shared.dto.rentals.RentCarBaseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ConfirmRentCommandResponse extends RentCarBaseDTO {
    private final String rentalId;
    private final String status;
}
