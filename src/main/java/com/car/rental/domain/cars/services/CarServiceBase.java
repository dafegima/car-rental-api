package com.car.rental.domain.cars.services;

import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.interfaces.repositories.CarsRepository;

import java.util.Optional;

public class CarServiceBase {

    private final CarsRepository carsRepository;

    public CarServiceBase(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public Optional<Car> getCarByLicensePlate(String licensePlate) {
        return carsRepository.getByLicensePlate(licensePlate);
    }

    public boolean carExist(String licensePlate) {
        return carsRepository.getByLicensePlate(licensePlate).isPresent();
    }
}
