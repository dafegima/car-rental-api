package com.car.rental.application.queries.cars.getbylicenseplate;

import com.car.rental.application.shared.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCarByLicensePlateQuery implements Request<GetCarByLicensePlateQueryResponse> {
    private String licensePlate;
}
