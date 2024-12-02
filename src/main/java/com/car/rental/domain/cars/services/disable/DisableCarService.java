package com.car.rental.domain.cars.services.disable;

import com.car.rental.domain.cars.models.Car;

public interface DisableCarService {
    Car disableCar(String licensePlate);
}
