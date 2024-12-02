package com.car.rental.application.queries.rentals.getbycustomerid;

import com.car.rental.application.shared.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetRentsByCustomerIdQuery implements Request<List<GetRentsByCustomerIdQueryResponse>>{
    private String customerId;
}
