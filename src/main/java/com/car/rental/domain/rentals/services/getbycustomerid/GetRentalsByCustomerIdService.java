package com.car.rental.domain.rentals.services.getbycustomerid;

import com.car.rental.domain.rentals.models.Rental;

import java.util.List;

public interface GetRentalsByCustomerIdService {
    List<Rental> getRentalByCustomerId(String customerId);
}
