package com.car.rental.application.shared.dto.rentals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@SuperBuilder
public class RentCarBaseDTO {
    private final String licensePlate;
    private final String customerId;
    private final String customerName;
    private final String customerEmail;
    private final String customerPhone;
    private final LocalDateTime pickupDate;
    private final LocalDateTime returnDate;
}
