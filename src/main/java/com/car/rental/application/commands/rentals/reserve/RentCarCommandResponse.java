package com.car.rental.application.commands.rentals.reserve;

import com.car.rental.application.shared.dto.rentals.RentCarBaseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RentCarCommandResponse extends RentCarBaseDTO {
    private final String rentalId;
    private final String status;
}
