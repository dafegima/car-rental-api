package com.car.rental.domain.rentals.services.getall.impl;

import com.car.rental.domain.interfaces.repositories.RentalsRepository;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.getall.GetAllRentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllRentsServiceImpl implements GetAllRentsService {

    private final RentalsRepository rentalsRepository;

    public GetAllRentsServiceImpl(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @Override
    public List<Rental> getAllRents() {
        return rentalsRepository.getAllRentals();
    }
}
