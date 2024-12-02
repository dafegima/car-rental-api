package com.car.rental.domain.cars.services.enable.impl;

import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.enable.EnableCarService;
import com.car.rental.domain.exceptions.DomainException;
import com.car.rental.domain.interfaces.repositories.CarsRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EnableCarServiceImpl implements EnableCarService {

    private final CarsRepository carsRepository;

    public EnableCarServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public Car enableCar(String licensePlate) {
        Optional<Car> car = carsRepository.getByLicensePlate(licensePlate);
        if (car.isPresent()) {
            if (car.get().isDisable()){
                car.get().enable();
                carsRepository.enable(licensePlate);
                return car.get();
            }

            throw new DomainException(String.format("Car with license plate %s has already been enabled.", licensePlate));
        }

        throw new NoSuchElementException(String.format("Car with license plate %s does not exist", licensePlate));
    }
}
