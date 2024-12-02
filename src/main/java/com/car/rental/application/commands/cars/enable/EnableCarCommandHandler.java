package com.car.rental.application.commands.cars.enable;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.enable.EnableCarService;
import org.springframework.stereotype.Service;

@Service
public class EnableCarCommandHandler implements RequestHandler<EnableCarCommand, EnableCarCommandResponse> {

    private final EnableCarService enableCarService;

    public EnableCarCommandHandler(EnableCarService enableCarService) {
        this.enableCarService = enableCarService;
    }

    @Override
    public EnableCarCommandResponse handle(EnableCarCommand request) {
        Car car = enableCarService.enableCar(request.getLicensePlate());
        return mapDomainToDTO(car);
    }

    private EnableCarCommandResponse mapDomainToDTO(Car car) {
        return EnableCarCommandResponse.builder()
                .type(car.getType())
                .licensePlate(car.getLicensePlate())
                .brand(car.getBrand())
                .color(car.getColor())
                .createdAt(car.getCreatedAt())
                .gearType(car.getGearType())
                .model(car.getModel())
                .year(car.getYear())
                .status(car.getStatus().toString())
                .createdAt(car.getCreatedAt())
                .build();
    }
}
