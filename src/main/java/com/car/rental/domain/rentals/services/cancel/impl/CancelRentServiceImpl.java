package com.car.rental.domain.rentals.services.cancel.impl;

import com.car.rental.domain.exceptions.DomainException;
import com.car.rental.domain.interfaces.repositories.RentalsRepository;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.cancel.CancelRentService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CancelRentServiceImpl implements CancelRentService {

    private final RentalsRepository rentalsRepository;

    public CancelRentServiceImpl(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @Override
    public Rental cancelRent(String rentalId) {
        Optional<Rental> rental = rentalsRepository.getById(rentalId);
        if (rental.isPresent()) {
            if (!rental.get().isCanceled()){
                if (rental.get().canBeCanceled()){
                    rental.get().cancel();
                    rentalsRepository.updateRent(rental.get());
                    return rental.get();
                }

                throw new DomainException(String.format("Rental with id %s cannot be canceled because it has already occurred.", rentalId));
            }

            throw new DomainException(String.format("Rental with id %s cannot be canceled, because it has already been canceled.", rentalId));
        }

        throw new NoSuchElementException(String.format("Rental with id %s not found", rentalId));
    }
}
