package com.car.rental.domain.cars.services.getbylicenseplate;

import com.car.rental.domain.cars.models.Car;

public interface GetCarByLicensePlateService {
    Car getByLicensePlate(String licensePlate);
}
