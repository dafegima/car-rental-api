package com.car.rental.application.commands.rentals.confirm;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.confirm.ConfirmRentalService;
import org.springframework.stereotype.Service;

@Service
public class ConfirmRentCommandHandler implements RequestHandler<ConfirmRentCommand, ConfirmRentCommandResponse> {

    private final ConfirmRentalService confirmRentalService;

    public ConfirmRentCommandHandler(ConfirmRentalService confirmRentalService) {
        this.confirmRentalService = confirmRentalService;
    }

    @Override
    public ConfirmRentCommandResponse handle(ConfirmRentCommand request) {
        Rental rental = confirmRentalService.confirmRent(request.getRentalId());
        return mapDomainToDto(rental);
    }

    private ConfirmRentCommandResponse mapDomainToDto(Rental rental) {
        return ConfirmRentCommandResponse.builder()
                .rentalId(rental.getId())
                .status(rental.getStatus().toString())
                .customerId(rental.getCustomerId())
                .customerName(rental.getCustomerName())
                .customerPhone(rental.getCustomerPhone())
                .customerEmail(rental.getCustomerEmail())
                .licensePlate(rental.getLicensePlate())
                .pickupDate(rental.getPickupDate())
                .returnDate(rental.getReturnDate())
                .build();
    }
}
