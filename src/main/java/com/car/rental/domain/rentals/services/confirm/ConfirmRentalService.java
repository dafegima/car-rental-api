package com.car.rental.domain.rentals.services.confirm;

import com.car.rental.domain.rentals.models.Rental;

public interface ConfirmRentalService {
    Rental confirmRent(String rentalId);
}