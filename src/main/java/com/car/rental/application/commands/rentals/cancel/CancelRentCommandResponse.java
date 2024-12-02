package com.car.rental.application.commands.rentals.cancel;

import com.car.rental.application.shared.dto.rentals.RentCarBaseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CancelRentCommandResponse extends RentCarBaseDTO {
    private final String status;
    private final String rentalId;
}
