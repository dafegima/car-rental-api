package com.car.rental.domain.rentals.services.getbycustomerid.impl;

import com.car.rental.domain.interfaces.repositories.RentalsRepository;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.getbycustomerid.GetRentalsByCustomerIdService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRentalsByCustomerIdServiceImpl implements GetRentalsByCustomerIdService {

    private final RentalsRepository rentalsRepository;

    public GetRentalsByCustomerIdServiceImpl(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @Override
    public List<Rental> getRentalByCustomerId(String customerId) {
        return rentalsRepository.getByCustomerId(customerId);
    }
}
