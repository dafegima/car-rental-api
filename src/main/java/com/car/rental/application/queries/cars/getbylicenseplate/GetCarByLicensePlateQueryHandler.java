package com.car.rental.application.queries.cars.getbylicenseplate;

import com.car.rental.application.shared.RequestHandler;
import com.car.rental.domain.cars.enums.CarStatus;
import com.car.rental.domain.cars.models.Car;
import com.car.rental.domain.cars.services.getbylicenseplate.GetCarByLicensePlateService;
import org.springframework.stereotype.Service;

@Service
public class GetCarByLicensePlateQueryHandler implements RequestHandler<GetCarByLicensePlateQuery, GetCarByLicensePlateQueryResponse> {

    private final GetCarByLicensePlateService getCarByLicencePlateService;

    public GetCarByLicensePlateQueryHandler(GetCarByLicensePlateService getCarByLicencePlateService) {
        this.getCarByLicencePlateService = getCarByLicencePlateService;
    }

    @Override
    public GetCarByLicensePlateQueryResponse handle(GetCarByLicensePlateQuery request) {
        Car car = getCarByLicencePlateService.getByLicensePlate(request.getLicensePlate());
        return mapDomainToDto(car);
    }

    private GetCarByLicensePlateQueryResponse mapDomainToDto(Car car) {
        return GetCarByLicensePlateQueryResponse.builder()
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
