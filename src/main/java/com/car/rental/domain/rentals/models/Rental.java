package com.car.rental.domain.rentals.models;

import com.car.rental.domain.rentals.enums.RentalStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Rental {
    private String id;
    private RentalStatus status;
    private String licensePlate;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDateTime pickupDate;
    private LocalDateTime returnDate;

    public boolean isPendingToConfirm() {
        return this.status.equals(RentalStatus.CREATED);
    }

    public void confirm() {
        this.status = RentalStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = RentalStatus.CANCELED;
    }

    public void create() {
        this.status = RentalStatus.CREATED;
    }

    public boolean canBeCanceled() {
        return !this.status.equals(RentalStatus.CANCELED) && this.pickupDate.isAfter(LocalDateTime.now());
    }

    public boolean isCanceled() {
        return this.status.equals(RentalStatus.CANCELED);
    }
}
