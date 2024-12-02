package com.car.rental.application.queries.rentals.getbycustomerid;

import com.car.rental.application.shared.dto.rentals.RentCarBaseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GetRentsByCustomerIdQueryResponse extends RentCarBaseDTO {
    private final String status;
    private final String rentalId;
}
