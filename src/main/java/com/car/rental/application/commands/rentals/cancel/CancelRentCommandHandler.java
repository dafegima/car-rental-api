package com.car.rental.application.commands.rentals.cancel;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.cancel.CancelRentService;
import org.springframework.stereotype.Service;

@Service
public class CancelRentCommandHandler implements RequestHandler<CancelRentCommand, CancelRentCommandResponse> {

    private final CancelRentService cancelRentService;

    public CancelRentCommandHandler(CancelRentService cancelRentService) {
        this.cancelRentService = cancelRentService;
    }

    @Override
    public CancelRentCommandResponse handle(CancelRentCommand request) {
        Rental rental = cancelRentService.cancelRent(request.getRentalId());
        return mapDomainToDto(rental);
    }

    private CancelRentCommandResponse mapDomainToDto(Rental rental) {
        return CancelRentCommandResponse.builder()
                .customerId(rental.getCustomerId())
                .customerName(rental.getCustomerName())
                .customerEmail(rental.getCustomerEmail())
                .customerPhone(rental.getCustomerPhone())
                .licensePlate(rental.getLicensePlate())
                .pickupDate(rental.getPickupDate())
                .returnDate(rental.getReturnDate())
                .status(rental.getStatus().toString())
                .rentalId(rental.getId())
                .build();
    }
}
