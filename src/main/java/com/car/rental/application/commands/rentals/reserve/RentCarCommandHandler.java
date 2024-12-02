package com.car.rental.application.commands.rentals.reserve;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.rentals.enums.RentalStatus;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.create.CreateRentalService;
import org.springframework.stereotype.Service;

@Service
public class RentCarCommandHandler implements RequestHandler<RentCarCommand, RentCarCommandResponse>{

    private final CreateRentalService createRentalService;

    public RentCarCommandHandler(CreateRentalService createRentalService) {
        this.createRentalService = createRentalService;
    }

    @Override
    public RentCarCommandResponse handle(RentCarCommand rentCarCommand) {
        Rental rentCreated = createRentalService.createRental(mapDTOToDomain(rentCarCommand));
        return mapDomainToDTO(rentCreated);
    }

    private RentCarCommandResponse mapDomainToDTO(Rental rentCreated) {
        return RentCarCommandResponse.builder()
                .rentalId(rentCreated.getId())
                .status(rentCreated.getStatus().equals(RentalStatus.CREATED) ? "CREATED" : rentCreated.getStatus().equals(RentalStatus.CONFIRMED) ? "CONFIRMED" : "CANCELED")
                .licensePlate(rentCreated.getLicensePlate())
                .customerEmail(rentCreated.getCustomerEmail())
                .customerId(rentCreated.getCustomerId())
                .customerName(rentCreated.getCustomerName())
                .customerPhone(rentCreated.getCustomerPhone())
                .pickupDate(rentCreated.getPickupDate())
                .returnDate(rentCreated.getReturnDate())
                .build();
    }

    private Rental mapDTOToDomain(RentCarCommand rentCarCommand) {
        return Rental.builder()
                .licensePlate(rentCarCommand.getLicensePlate())
                .customerId(rentCarCommand.getCustomerId())
                .customerPhone(rentCarCommand.getCustomerPhone())
                .customerName(rentCarCommand.getCustomerName())
                .customerEmail(rentCarCommand.getCustomerEmail())
                .returnDate(rentCarCommand.getReturnDate())
                .pickupDate(rentCarCommand.getPickupDate())
                .build();
    }
}
