package com.car.rental.domain.rentals.services.create.impl;

import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.exceptions.InvalidRequestException;
import com.car.rental.domain.exceptions.models.InvalidRequestExceptionModel;
import com.car.rental.domain.interfaces.repositories.CarsRepository;
import com.car.rental.domain.interfaces.repositories.RentalsRepository;
import com.car.rental.domain.rentals.models.Rental;
import com.car.rental.domain.rentals.services.create.CreateRentalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateRentalServiceImpl implements CreateRentalService {

    private final RentalsRepository rentalsRepository;
    private final CarsRepository carsRepository;
    private final String VEHICLE_NOT_EXIST = "The vehicle with license plate %s does not exist.";
    private final String VEHICLE_NOT_AVAILABLE ="The vehicle with license plate %s is not available for the chosen date.";
    private final String VEHICLE_NOT_ENABLED = "The vehicle with license plate %s is not enabled.";

    public CreateRentalServiceImpl(RentalsRepository rentalsRepository, CarsRepository carsRepository) {
        this.rentalsRepository = rentalsRepository;
        this.carsRepository = carsRepository;
    }

    @Override
    public Rental createRental(Rental rental) {
        Optional<Car> car = carsRepository.getByLicensePlate(rental.getLicensePlate());
        if (car.isPresent()) {
            if(car.get().isEnable()){
                if (carIsAvailable(rental)){
                    rental.create();
                    rental = rentalsRepository.createRent(rental);
                }
                else
                    throwError(VEHICLE_NOT_AVAILABLE, "licensePlate", rental.getLicensePlate());
            }
            else
                throwError(VEHICLE_NOT_ENABLED, "licensePlate", rental.getLicensePlate());
        }
        else
            throwError(VEHICLE_NOT_EXIST, "licensePlate", rental.getLicensePlate());

        return rental;
    }

    private void throwError(String errorMessage, String property, String propertyValue) {
        List<InvalidRequestExceptionModel> messages = new ArrayList<>();
        messages.add(new InvalidRequestExceptionModel(property, String.format(errorMessage, propertyValue)));
        throw new InvalidRequestException(messages);
    }

    private boolean carIsAvailable(Rental rental) {
        List<Rental> rentals = rentalsRepository.getByLicensePlate(rental.getLicensePlate());
        rentals = rentals.stream().filter(r-> pickUpDateIsNotAvailable(rental.getPickupDate(), rental.getReturnDate(), r.getPickupDate(), r.getReturnDate())).toList();
        return rentals.isEmpty();
    }

    private boolean pickUpDateIsNotAvailable(LocalDateTime pickupDate, LocalDateTime returnDate, LocalDateTime pickUpDateStored, LocalDateTime returnDateStored) {
       boolean pickUpDateIsInRentDate = pickupDate.isAfter(pickUpDateStored) && pickupDate.isBefore(returnDateStored);
       boolean returnDateIsInRentDate = returnDate.isAfter(pickUpDateStored) && returnDate.isBefore(returnDateStored);
       boolean rentDateIsOverlap = (pickupDate.equals(pickUpDateStored)|| pickupDate.equals(returnDateStored) || returnDate.equals(pickUpDateStored) || returnDate.equals(returnDateStored)) || (pickupDate.isBefore(pickUpDateStored) && returnDate.isAfter(returnDateStored));

       return  pickUpDateIsInRentDate || returnDateIsInRentDate || rentDateIsOverlap;
    }
}
