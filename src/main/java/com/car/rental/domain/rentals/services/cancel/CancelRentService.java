package com.car.rental.domain.rentals.services.cancel;

import com.car.rental.domain.rentals.models.Rental;

public interface CancelRentService {
    Rental cancelRent(String rentalId);
}
