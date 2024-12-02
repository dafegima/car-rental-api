package com.car.rental.application.queries.rentals.getall;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.getall.GetAllRentsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllRentsQueryHandler implements RequestHandler<GetAllRentsQuery, List<GetAllRentsQueryResponse>> {

    private final GetAllRentsService getAllRentsService;

    public GetAllRentsQueryHandler(GetAllRentsService getAllRentsService) {
        this.getAllRentsService = getAllRentsService;
    }

    @Override
    public List<GetAllRentsQueryResponse> handle(GetAllRentsQuery request) {
        List<Rental> rentals = getAllRentsService.getAllRents();
        return mapDomainToDto(rentals);
    }

    private List<GetAllRentsQueryResponse> mapDomainToDto(List<Rental> rentals) {
        List<GetAllRentsQueryResponse> result = new ArrayList<>();
        rentals.forEach(r-> {
            result.add(GetAllRentsQueryResponse.builder()
                    .rentalId(r.getId())
                    .status(r.getStatus().toString())
                    .customerId(r.getCustomerId())
                    .customerName(r.getCustomerName())
                    .customerPhone(r.getCustomerPhone())
                    .customerEmail(r.getCustomerEmail())
                    .pickupDate(r.getPickupDate())
                    .returnDate(r.getReturnDate())
                    .licensePlate(r.getLicensePlate())

                    .build());
        });

        return result;
    }
}
