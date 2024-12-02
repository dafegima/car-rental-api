package com.car.rental.application.queries.rentals.getall;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.car.rental.application.Constants.API_PATH_RENTALS;

@RestController
@RequestMapping(API_PATH_RENTALS)
public class GetAllRentsEndpoint {

    private final GetAllRentsQueryHandler getAllRentsQueryHandler;

    public GetAllRentsEndpoint(GetAllRentsQueryHandler getAllRentsQueryHandler) {
        this.getAllRentsQueryHandler = getAllRentsQueryHandler;
    }

    @GetMapping()
    public ResponseEntity<List<GetAllRentsQueryResponse>> getAllRents() {
        List<GetAllRentsQueryResponse> response = getAllRentsQueryHandler.handle(new GetAllRentsQuery());
        if(response.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(response);
    }
}
