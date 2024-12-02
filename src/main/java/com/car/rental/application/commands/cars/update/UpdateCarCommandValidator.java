package com.car.rental.application.commands.cars.update;

import br.com.fluentvalidator.AbstractValidator;

import java.util.Arrays;
import java.util.List;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringInCollection;
import static java.util.function.Predicate.not;

public class UpdateCarCommandValidator extends AbstractValidator<UpdateCarCommand> {

    private final List<String> allowedCarTypes = Arrays.asList("Sedan","Hatchback","Pickup","Suv");
    private final List<String> allowedGearTypes = Arrays.asList("Manual","Automatic");
    private final String LICENSE_PLATE_REGEX = "^[A-Za-z]{3}\\d{3}$";

    @Override
    public void rules() {
        ruleFor(UpdateCarCommand::getBrand)
                .must(not(stringEmptyOrNull()))
                .withMessage("brand cannot be empty")
                .withFieldName("brand");

        ruleFor(UpdateCarCommand::getModel)
                .must(not(stringEmptyOrNull()))
                .withMessage("model cannot be empty")
                .withFieldName("model");

        ruleFor(UpdateCarCommand::getColor)
                .must(not(stringEmptyOrNull()))
                .withMessage("color cannot be empty")
                .withFieldName("color");

        ruleFor(UpdateCarCommand::getType)
                .must(stringInCollection(allowedCarTypes))
                .withMessage("type must be a value between Sedan, SUV, Pick up, Hatchback")
                .withFieldName("type");

        ruleFor(UpdateCarCommand::getGearType)
                .must(stringInCollection(allowedGearTypes))
                .withMessage("gearType must be a value between Manual, Automatic.")
                .withFieldName("gearType");

        ruleFor(UpdateCarCommand::getYear)
                .must(x-> x > 2015)
                .withMessage("year must be a value greater than 2015")
                .withFieldName("year");

        ruleFor(UpdateCarCommand::getLicensePlate)
                .must(not(stringEmptyOrNull()))
                .withMessage("licensePlate cannot be empty")
                .withFieldName("licensePlate")
                .must(x-> x.matches(LICENSE_PLATE_REGEX))
                .withMessage("licensePlate must follow the format ABC123")
                .withFieldName("licensePlate");
    }
}
