package com.car.rental.application.commands.cars.create;

import br.com.fluentvalidator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringInCollection;
import static java.util.function.Predicate.not;

@Component
public class CreateCarCommandValidator extends AbstractValidator<CreateCarCommand> {

    private final List<String> allowedCarTypes = Arrays.asList("Sedan","Hatchback","Pickup","SUV");
    private final List<String> allowedGearTypes = Arrays.asList("Manual","Automatic");
    private final String LICENSE_PLATE_REGEX = "^[A-Za-z]{3}\\d{3}$";

    @Override
    public void rules() {
        ruleFor(CreateCarCommand::getBrand)
                .must(not(stringEmptyOrNull()))
                .withMessage("brand cannot be empty")
                .withFieldName("brand");

        ruleFor(CreateCarCommand::getModel)
                .must(not(stringEmptyOrNull()))
                .withMessage("model cannot be empty")
                .withFieldName("model");

        ruleFor(CreateCarCommand::getColor)
                .must(not(stringEmptyOrNull()))
                .withMessage("color cannot be empty")
                .withFieldName("color");

        ruleFor(CreateCarCommand::getType)
                .must(type -> allowedCarTypes.stream().anyMatch(acType-> acType.equalsIgnoreCase(type)))
                .withMessage("type must be a value between Sedan, SUV, Pickup, Hatchback")
                .withFieldName("type");

        ruleFor(CreateCarCommand::getGearType)
                .must(gt-> allowedGearTypes.stream().anyMatch(agt-> agt.equalsIgnoreCase(gt)))
                .withMessage("gearType must be a value between Manual, Automatic.")
                .withFieldName("gearType");

        ruleFor(CreateCarCommand::getYear)
                .must(x-> x > 2015)
                .withMessage("year must be a value greater than 2015")
                .withFieldName("year");

        ruleFor(CreateCarCommand::getLicensePlate)
                .must(not(stringEmptyOrNull()))
                .withMessage("licensePlate cannot be empty")
                .withFieldName("licensePlate")
                .must(x-> x.matches(LICENSE_PLATE_REGEX))
                .withMessage("licensePlate must follow the format ABC123")
                .withFieldName("licensePlate");
    }
}
