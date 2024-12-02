package com.car.rental.domain.cars.services.getbylicenseplate.impl;

import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.getbylicenseplate.GetCarByLicensePlateService;
import com.car.rental.domain.interfaces.repositories.CarsRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GetCarByLicensePlateServiceImpl implements GetCarByLicensePlateService {

    private final CarsRepository carsRepository;

    public GetCarByLicensePlateServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public Car getByLicensePlate(String licensePlate) {
        Optional<Car> car = carsRepository.getByLicensePlate(licensePlate);
        if (car.isPresent())
            return car.get();

        throw new NoSuchElementException(String.format("Vehicle with license plate %s does not exist", licensePlate));
    }
}
