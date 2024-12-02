package com.car.rental.application.commands.cars.create;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.cars.enums.CarStatus;
import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.create.CreateCarService;
import org.springframework.stereotype.Service;


@Service
public class CreateCarCommandHandler implements RequestHandler<CreateCarCommand, CreateCarCommandResponse> {

    private final CreateCarService createCarService;

    public CreateCarCommandHandler(CreateCarService createCarService) {
        this.createCarService = createCarService;
    }

    @Override
    public CreateCarCommandResponse handle(CreateCarCommand createCarCommand) {
        Car carCreated = createCarService.createCar(mapDTOToDomain(createCarCommand));
        return mapDomainToDTO(carCreated);
    }

    private Car mapDTOToDomain(CreateCarCommand createCarCommand){
        return Car.builder()
                .type(createCarCommand.getType())
                .year(createCarCommand.getYear())
                .model(createCarCommand.getModel())
                .brand(createCarCommand.getBrand())
                .color(createCarCommand.getColor())
                .gearType(createCarCommand.getGearType())
                .licensePlate(createCarCommand.getLicensePlate())
                .build();
    }

    private CreateCarCommandResponse mapDomainToDTO(Car car){
        return CreateCarCommandResponse.builder()
                .createdAt(car.getCreatedAt())
                .type(car.getType())
                .brand(car.getBrand())
                .status(car.getStatus() == CarStatus.DISABLED ? "DISABLED" : "ENABLED")
                .year(car.getYear())
                .color(car.getColor())
                .gearType(car.getGearType())
                .model(car.getModel())
                .licensePlate(car.getLicensePlate())
                .build();
    }
}
