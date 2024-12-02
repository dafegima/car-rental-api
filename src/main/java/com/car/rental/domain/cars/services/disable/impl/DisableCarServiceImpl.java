package com.car.rental.domain.cars.services.disable.impl;

import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.CarServiceBase;
import com.car.rental.domain.cars.services.disable.DisableCarService;
import com.car.rental.domain.exceptions.DomainException;
import com.car.rental.domain.interfaces.repositories.CarsRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DisableCarServiceImpl extends CarServiceBase implements DisableCarService {

    private final CarsRepository carsRepository;

    public DisableCarServiceImpl(CarsRepository carsRepository) {
        super(carsRepository);
        this.carsRepository = carsRepository;
    }

    @Override
    public Car disableCar(String licensePlate) {
        if (!carExist(licensePlate))
            throw new NoSuchElementException(String.format("Car with license plate %s does not exist", licensePlate));

        Optional<Car> car = carsRepository.getByLicensePlate(licensePlate);
        if(car.isPresent() && car.get().isEnable()){
            car.get().disable();
            this.carsRepository.disable(licensePlate);
            return car.get();
        }

        throw new DomainException(String.format("Car with license plate %s has already been disabled.", licensePlate));
    }
}
