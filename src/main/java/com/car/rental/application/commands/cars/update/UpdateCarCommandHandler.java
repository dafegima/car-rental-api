package com.car.rental.application.commands.cars.update;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.update.UpdateCarService;
import org.springframework.stereotype.Service;

@Service
public class UpdateCarCommandHandler implements RequestHandler<UpdateCarCommand, UpdateCarCommandResponse> {

    private final UpdateCarService updateCarService;

    public UpdateCarCommandHandler(UpdateCarService updateCarService) {
        this.updateCarService = updateCarService;
    }

    @Override
    public UpdateCarCommandResponse handle(UpdateCarCommand request) {
        Car car = mapDtoToDomain(request);
        car = updateCarService.updateCar(car);
        return mapDomainToDto(car);
    }

    private UpdateCarCommandResponse mapDomainToDto(Car car) {
        return UpdateCarCommandResponse.builder()
                .type(car.getType())
                .licensePlate(car.getLicensePlate())
                .brand(car.getBrand())
                .color(car.getColor())
                .createdAt(car.getCreatedAt())
                .gearType(car.getGearType())
                .status(car.getStatus().toString())
                .model(car.getModel())
                .year(car.getYear())
                .build();
    }

    private Car mapDtoToDomain(UpdateCarCommand command) {
        return Car.builder()
                .type(command.getType())
                .licensePlate(command.getLicensePlate())
                .brand(command.getBrand())
                .color(command.getColor())
                .gearType(command.getGearType())
                .model(command.getModel())
                .year(command.getYear())
                .build();
    }
}
