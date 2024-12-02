package com.car.rental.application.queries.rentals.getbycustomerid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.car.rental.application.Constants.API_PATH_RENTALS;
import static com.car.rental.application.Constants.API_PATH_RENTALS_GET_BY_CUSTOMER_ID_ENDPOINT;

@RestController
@RequestMapping(API_PATH_RENTALS)
public class GetRentsByCustomerIdEndpoint {

    private final GetRentsByCustomerIdQueryHandler getRentByCustomerIdQueryHandler;

    public GetRentsByCustomerIdEndpoint(GetRentsByCustomerIdQueryHandler getRentByCustomerIdQueryHandler) {
        this.getRentByCustomerIdQueryHandler = getRentByCustomerIdQueryHandler;
    }

    @GetMapping(API_PATH_RENTALS_GET_BY_CUSTOMER_ID_ENDPOINT)
    public ResponseEntity<List<GetRentsByCustomerIdQueryResponse>> getRentByCustomerId(@PathVariable String customerId) {
        List<GetRentsByCustomerIdQueryResponse> response = getRentByCustomerIdQueryHandler.handle(new GetRentsByCustomerIdQuery(customerId));
        return ResponseEntity.ok(response);
    }
}
