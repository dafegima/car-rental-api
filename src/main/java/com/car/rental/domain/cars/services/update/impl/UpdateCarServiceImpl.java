package com.car.rental.domain.cars.services.update.impl;

import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.CarServiceBase;
import com.car.rental.domain.cars.services.update.UpdateCarService;
import com.car.rental.domain.exceptions.DomainException;
import com.car.rental.domain.interfaces.repositories.CarsRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UpdateCarServiceImpl extends CarServiceBase implements UpdateCarService {

    private final CarsRepository carsRepository;

    public UpdateCarServiceImpl(CarsRepository carsRepository) {
        super(carsRepository);
        this.carsRepository = carsRepository;
    }

    @Override
    public Car updateCar(Car carToUpdate) {
        Optional<Car> car = getCarByLicensePlate(carToUpdate.getLicensePlate());
        if(car.isPresent()) {
            if (car.get().isEnable())
                return carsRepository.update(carToUpdate);

            throw new DomainException(String.format("Car with license plate %s is currently disabled.", carToUpdate.getLicensePlate()));
        }

        throw new NoSuchElementException(String.format("It is not possible to update the vehicle with license plate %s because it does not exist", carToUpdate.getLicensePlate()));
    }
}
