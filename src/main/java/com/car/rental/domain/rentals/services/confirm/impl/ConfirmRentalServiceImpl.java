package com.car.rental.domain.rentals.services.confirm.impl;

import com.car.rental.domain.exceptions.DomainException;
import com.car.rental.domain.interfaces.repositories.RentalsRepository;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.confirm.ConfirmRentalService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ConfirmRentalServiceImpl implements ConfirmRentalService {

    private final RentalsRepository rentalsRepository;

    public ConfirmRentalServiceImpl(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @Override
    public Rental confirmRent(String rentalId) {
        Optional<Rental> rental = rentalsRepository.getById(rentalId);
        if (rental.isPresent()) {
            if (rental.get().isPendingToConfirm()) {
                rental.get().confirm();
                return rentalsRepository.updateRent(rental.get());
            }

            throw new DomainException(String.format("The rental with ID %s has a status of '%s', so it cannot be confirmed.", rental.get().getId(), rental.get().getStatus()));
        } else
            throw new NoSuchElementException(String.format("The rental with id %s does not exist", rentalId));
    }
}
