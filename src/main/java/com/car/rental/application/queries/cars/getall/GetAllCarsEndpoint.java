package com.car.rental.application.queries.cars.getall;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.car.rental.application.Constants.API_PATH_CARS;

@RestController
@RequestMapping(API_PATH_CARS)
public class GetAllCarsEndpoint {

    private final GetAllCarsQueryHandler getAllCarsQueryHandler;

    public GetAllCarsEndpoint(GetAllCarsQueryHandler getAllCarsQueryHandler) {
        this.getAllCarsQueryHandler = getAllCarsQueryHandler;
    }

    @GetMapping()
    public ResponseEntity<List<GetAllCarsQueryResponse>> getAllCars() {
        List<GetAllCarsQueryResponse> response = getAllCarsQueryHandler.handle(new GetAllCarsQuery());
        if (response.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(response);
    }
}
