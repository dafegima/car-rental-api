package com.car.rental.repository.persistence;

import com.car.rental.domain.interfaces.repositories.RentalsRepository;
import com.car.rental.domain.rentals.enums.RentalStatus;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.repository.models.RentalEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RentalsRepositoryImpl implements RentalsRepository {

    private final List<RentalEntity> rentalEntityList;

    public RentalsRepositoryImpl() {
        rentalEntityList = new ArrayList<>();
    }

    @Override
    public List<Rental> getByLicensePlate(String licensePlate) {
        List<Rental> rentals = new ArrayList<>();
        List<RentalEntity> rentalEntities = rentalEntityList.stream().filter(r-> r.getLicensePlate().equals(licensePlate)).toList();
        rentalEntities.forEach(rent-> rentals.add(mapEntityToDomain(rent)));

        return rentals;
    }

    @Override
    public Optional<Rental> getById(String rentalId) {
        Optional<RentalEntity> rentalEntity = getEntityByRentalId(rentalId);
        return rentalEntity.map(this::mapEntityToDomain);

    }

    @Override
    public List<Rental> getByCustomerId(String customerId) {
        List<Rental> rentals = new ArrayList<>();
        List<RentalEntity> rentalEntities = rentalEntityList.stream().filter(rent-> rent.getCustomerId().equalsIgnoreCase(customerId)).toList();
        rentalEntities.forEach(rent-> rentals.add(mapEntityToDomain(rent)));
        return rentals;
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalEntityList.stream().map(this::mapEntityToDomain).toList();
    }

    @Override
    public Rental cancelRent(Rental rental) {
        rentalEntityList.removeIf(rent-> rent.getCustomerId().equalsIgnoreCase(rental.getCustomerId()));
        rentalEntityList.add(mapDomainToEntity(rental, rental.getId()));
        return rental;
    }

    @Override
    public Rental createRent(Rental rental) {
        RentalEntity rentalEntity  = mapDomainToEntity(rental, UUID.randomUUID().toString());
        rentalEntityList.add(rentalEntity);
        rental = mapEntityToDomain(rentalEntity);
        return rental;
    }

    @Override
    public Rental updateRent(Rental rental) {
        rentalEntityList.removeIf(rent-> rent.getId().equalsIgnoreCase(rental.getId()));
        rentalEntityList.add(mapDomainToEntity(rental, rental.getId()));
        return rental;
    }

    private Rental mapEntityToDomain(RentalEntity rent) {
        if (rent == null)
            return null;

        return Rental.builder()
                .id(rent.getId())
                .status(rent.getStatus().equalsIgnoreCase("CREATED") ? RentalStatus.CREATED : rent.getStatus().equalsIgnoreCase("CONFIRMED") ? RentalStatus.CONFIRMED : RentalStatus.CANCELED)
                .customerEmail(rent.getCustomerEmail())
                .licensePlate(rent.getLicensePlate())
                .customerName(rent.getCustomerName())
                .customerPhone(rent.getCustomerPhone())
                .pickupDate(rent.getPickupDate())
                .returnDate(rent.getReturnDate())
                .customerId(rent.getCustomerId())
                .build();
    }

    private RentalEntity mapDomainToEntity(Rental rent, String rentalId) {
        return RentalEntity.builder()
                .id(rentalId)
                .status(rent.getStatus().equals(RentalStatus.CREATED) ? "CREATED" : rent.getStatus().equals(RentalStatus.CONFIRMED) ? "CONFIRMED" : "CANCELED")
                .customerEmail(rent.getCustomerEmail())
                .licensePlate(rent.getLicensePlate())
                .customerName(rent.getCustomerName())
                .customerPhone(rent.getCustomerPhone())
                .pickupDate(rent.getPickupDate())
                .returnDate(rent.getReturnDate())
                .customerId(rent.getCustomerId())
                .build();
    }

    private Optional<RentalEntity> getEntityByRentalId(String rentalId) {
        return rentalEntityList.stream().filter(rent-> rent.getId().equalsIgnoreCase(rentalId)).findFirst();
    }
}
