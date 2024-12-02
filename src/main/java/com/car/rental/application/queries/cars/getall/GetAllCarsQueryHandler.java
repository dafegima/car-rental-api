package com.car.rental.application.queries.cars.getall;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.cars.enums.CarStatus;
import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.getall.GetAllCarsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllCarsQueryHandler implements RequestHandler<GetAllCarsQuery, List<GetAllCarsQueryResponse>> {

    private final GetAllCarsService getAllCarsService;

    public GetAllCarsQueryHandler(GetAllCarsService getAllCarsService) {
        this.getAllCarsService = getAllCarsService;
    }

    @Override
    public List<GetAllCarsQueryResponse> handle(GetAllCarsQuery request) {
        return mapDomainToDto(getAllCarsService.getAllCars());
    }

    private List<GetAllCarsQueryResponse> mapDomainToDto(List<Car> cars) {
        List<GetAllCarsQueryResponse> result = new ArrayList<>();
        cars.forEach(car -> {
            result.add(GetAllCarsQueryResponse.builder()
                    .type(car.getType())
                    .licensePlate(car.getLicensePlate())
                    .brand(car.getBrand())
                    .color(car.getColor())
                    .createdAt(car.getCreatedAt())
                    .gearType(car.getGearType())
                    .status(car.getStatus().equals(CarStatus.DISABLED) ? "DISABLED" : "ENABLED")
                    .model(car.getModel())
                    .year(car.getYear())
                    .build());
        });

        return result;
    }
}
