package com.car.rental.domain.interfaces.repositories;

import com.car.rental.domain.rentals.models.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalsRepository {
    List<Rental> getByLicensePlate(String licensePlate);
    Optional<Rental> getById(String rentalId);
    List<Rental> getByCustomerId(String customerId);
    List<Rental> getAllRentals();
    Rental cancelRent(Rental rental);
    Rental createRent(Rental rental);
    Rental updateRent(Rental rental);
}
