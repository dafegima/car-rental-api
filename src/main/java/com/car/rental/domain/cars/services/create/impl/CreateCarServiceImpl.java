package com.car.rental.domain.cars.services.create.impl;

import com.car.rental.domain.cars.services.CarServiceBase;
import com.car.rental.domain.exceptions.ConflictException;
import com.car.rental.domain.interfaces.repositories.CarsRepository;
import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.create.CreateCarService;
import org.springframework.stereotype.Service;

@Service
public class CreateCarServiceImpl extends CarServiceBase implements CreateCarService {

    private final CarsRepository carsRepository;

    public CreateCarServiceImpl(CarsRepository carsRepository) {
        super(carsRepository);
        this.carsRepository = carsRepository;
    }

    @Override
    public Car createCar(Car car) {
        if (carExist(car.getLicensePlate()))
            throw new ConflictException(String.format("Car with license plate %s already exists", car.getLicensePlate()));

        car.enable();
        return carsRepository.add(car);
    }
}
