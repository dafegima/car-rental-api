package com.car.rental.application.queries.cars.getbylicenseplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.car.rental.application.Constants.API_PATH_CARS;
import static com.car.rental.application.Constants.API_PATH_CARS_GET_BY_LICENSE_PLATE_ENDPOINT;

@RestController
@RequestMapping(API_PATH_CARS)
public class GetCarByLicensePlateEndpoint {

    private final GetCarByLicensePlateQueryHandler getCarByLicensePlateQueryHandler;

    public GetCarByLicensePlateEndpoint(GetCarByLicensePlateQueryHandler getCarByLicensePlateQueryHandler) {
        this.getCarByLicensePlateQueryHandler = getCarByLicensePlateQueryHandler;
    }

    @GetMapping(API_PATH_CARS_GET_BY_LICENSE_PLATE_ENDPOINT)
    public ResponseEntity<GetCarByLicensePlateQueryResponse> getCarByLicensePlate(@PathVariable("licensePlate") String licensePlate) {
        GetCarByLicensePlateQueryResponse response = getCarByLicensePlateQueryHandler.handle(new GetCarByLicensePlateQuery(licensePlate));
        return ResponseEntity.ok(response);
    }
}
