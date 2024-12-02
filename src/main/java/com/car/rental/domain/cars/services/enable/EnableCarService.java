package com.car.rental.domain.cars.services.enable;

import com.car.rental.domain.cars.models.Car;

public interface EnableCarService {
    Car enableCar(String licensePlate);
}
