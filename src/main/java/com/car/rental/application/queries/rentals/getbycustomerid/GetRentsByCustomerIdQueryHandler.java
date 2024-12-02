package com.car.rental.application.queries.rentals.getbycustomerid;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.getbycustomerid.GetRentalsByCustomerIdService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetRentsByCustomerIdQueryHandler implements RequestHandler<GetRentsByCustomerIdQuery, List<GetRentsByCustomerIdQueryResponse>> {

    private final GetRentalsByCustomerIdService getRentalByCustomerIdService;

    public GetRentsByCustomerIdQueryHandler(GetRentalsByCustomerIdService getRentalByCustomerIdService) {
        this.getRentalByCustomerIdService = getRentalByCustomerIdService;
    }

    @Override
    public List<GetRentsByCustomerIdQueryResponse> handle(GetRentsByCustomerIdQuery request) {
        List<Rental> rentals = getRentalByCustomerIdService.getRentalByCustomerId(request.getCustomerId());
        return mapDomainToDto(rentals);
    }

    private List<GetRentsByCustomerIdQueryResponse> mapDomainToDto(List<Rental> rentals) {
        List<GetRentsByCustomerIdQueryResponse> result = new ArrayList<>();
        rentals.forEach(rental -> {
            result.add(GetRentsByCustomerIdQueryResponse.builder()
                    .rentalId(rental.getId())
                    .status(rental.getStatus().toString())
                    .customerId(rental.getCustomerId())
                    .customerName(rental.getCustomerName())
                    .customerEmail(rental.getCustomerEmail())
                    .customerPhone(rental.getCustomerPhone())
                    .pickupDate(rental.getPickupDate())
                    .returnDate(rental.getReturnDate())
                    .licensePlate(rental.getLicensePlate())
                    .build());
        });

        return result;
    }


}
