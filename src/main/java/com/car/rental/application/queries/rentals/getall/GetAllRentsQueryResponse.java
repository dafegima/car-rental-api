package com.car.rental.application.queries.rentals.getall;

import com.car.rental.application.shared.dto.rentals.RentCarBaseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GetAllRentsQueryResponse extends RentCarBaseDTO {
    private final String rentalId;
    private final String status;
}
