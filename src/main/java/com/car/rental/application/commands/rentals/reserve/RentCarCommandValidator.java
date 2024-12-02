package com.car.rental.application.commands.rentals.reserve;

import br.com.fluentvalidator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static java.util.function.Predicate.not;

@Component
public class RentCarCommandValidator extends AbstractValidator<RentCarCommand> {

    private final String LICENSE_PLATE_REGEX = "^[A-Za-z]{3}\\d{3}$";
    private final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    @Override
    public void rules() {
        ruleFor(RentCarCommand::getCustomerName)
                .must(not(stringEmptyOrNull()))
                .withMessage("customer cannot be empty")
                .withFieldName("customerName");

        ruleFor(RentCarCommand::getLicensePlate)
                .must(not(stringEmptyOrNull()))
                .withMessage("licensePlate cannot be empty")
                .must(x -> x.matches(LICENSE_PLATE_REGEX))
                .withMessage("licensePlate must follow the format ABC123")
                .withFieldName("licensePlate");

        ruleFor(RentCarCommand::getCustomerId)
                .must(not(stringEmptyOrNull()))
                .withMessage("customerId cannot be empty")
                .withFieldName("customerId");

        ruleFor(RentCarCommand::getCustomerEmail)
                .must(not(stringEmptyOrNull()))
                .withMessage("customerEmail cannot be empty")
                .withFieldName("customerEmail")
                .must(x -> x.matches(EMAIL_REGEX))
                .withMessage("customerEmail has invalid format")
                .withFieldName("customerEmail");

        ruleFor(RentCarCommand::getCustomerPhone)
                .must(not(stringEmptyOrNull()))
                .withMessage("customerPhone cannot be empty")
                .withFieldName("customerPhone");

        ruleFor(rentCarCommand -> rentCarCommand)
                .must(rentCar -> rentCar.getPickupDate().isBefore(rentCar.getReturnDate()))
                .withMessage("pickUpDate must be less than returnDate")
                .withFieldName("pickUpDate")
                .must(rentCar -> rentCar.getPickupDate().isAfter(LocalDateTime.now()))
                .withMessage("pickUpDate must be greater than current date")
                .withFieldName("pickUpDate");


        ruleFor(rentCarCommand -> rentCarCommand)
                .must(rentCar -> rentCar.getReturnDate().isAfter(rentCar.getPickupDate()))
                .withMessage("returnDate must be greater than pickUpDate")
                .withFieldName("returnDate");
    }
}