package com.car.rental.domain.interfaces.repositories;

import com.car.rental.domain.cars.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarsRepository {
    Car add(Car car);
    Optional<Car> getByLicensePlate(String licensePlate);
    List<Car> getAll();
    Car disable(String licensePlate);
    Car enable(String licensePlate);
    Car update(Car car);
}
