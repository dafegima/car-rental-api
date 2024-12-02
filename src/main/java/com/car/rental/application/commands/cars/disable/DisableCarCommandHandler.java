package com.car.rental.application.commands.cars.disable;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.cars.enums.CarStatus;
import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.disable.DisableCarService;
import org.springframework.stereotype.Service;

@Service
public class DisableCarCommandHandler implements RequestHandler<DisableCarCommand, DisableCarCommandResponse> {

    private final DisableCarService disableCarService;

    public DisableCarCommandHandler(DisableCarService disableCarService) {
        this.disableCarService = disableCarService;
    }

    @Override
    public DisableCarCommandResponse handle(DisableCarCommand request) {
        Car car = disableCarService.disableCar(request.getLicensePlate());
        return mapDomainToEntity(car);
    }

    private DisableCarCommandResponse mapDomainToEntity(Car car) {
        return DisableCarCommandResponse.builder()
                .type(car.getType())
                .licensePlate(car.getLicensePlate())
                .brand(car.getBrand())
                .color(car.getColor())
                .createdAt(car.getCreatedAt())
                .gearType(car.getGearType())
                .status(car.getStatus().equals(CarStatus.DISABLED) ? "DISABLED" : "ENABLED")
                .model(car.getModel())
                .year(car.getYear())
                .build();
    }
}
