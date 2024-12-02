package com.car.rental.repository.models;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RentalEntity {
    private final String id;
    private final String status;
    private final String licensePlate;
    private final String customerId;
    private final String customerName;
    private final String customerEmail;
    private final String customerPhone;
    private final LocalDateTime pickupDate;
    private final LocalDateTime returnDate;
}
