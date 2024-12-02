package com.car.rental.application.commands.rentals.reserve;

import com.car.rental.application.shared.Request;
import com.car.rental.application.shared.dto.rentals.RentCarBaseDTO;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
public class RentCarCommand extends RentCarBaseDTO implements Request<RentCarCommandResponse> {

    public RentCarCommand(String licensePlate, String customerId, String customerName, String customerEmail, String customerPhone, LocalDateTime pickupDate, LocalDateTime returnDate) {
        super(licensePlate, customerId, customerName, customerEmail, customerPhone, pickupDate, returnDate);
    }
}
