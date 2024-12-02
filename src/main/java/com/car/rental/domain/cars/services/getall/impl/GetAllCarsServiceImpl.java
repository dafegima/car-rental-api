package com.car.rental.domain.cars.services.getall.impl;

import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.getall.GetAllCarsService;
import com.car.rental.domain.interfaces.repositories.CarsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCarsServiceImpl implements GetAllCarsService {
    private final CarsRepository carsRepository;

    public GetAllCarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carsRepository.getAll();
    }
}
