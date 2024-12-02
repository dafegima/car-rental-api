package com.car.rental.repository.persistence;

import com.car.rental.domain.cars.enums.CarStatus;
import com.car.rental.domain.interfaces.repositories.CarsRepository;
import com.car.rental.domain.cars.models.Car;
import com.car.rental.repository.models.CarEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarsRepositoryImpl implements CarsRepository {

    private final List<CarEntity> carEntityList;

    public CarsRepositoryImpl() {
        carEntityList = new ArrayList<>();
    }

    @Override
    public Car add(Car car) {
        CarEntity carEntity = MapDomainToEntity(car);
        carEntityList.add(carEntity);
        car = MapEntityToDomain(carEntity);
        return car;
    }

    @Override
    public Optional<Car> getByLicensePlate(String licensePlate) {
        CarEntity carEntity = carEntityList.stream().filter(x-> x.getLicensePlate().equalsIgnoreCase(licensePlate)).findFirst().orElse(null);
        Car car = MapEntityToDomain(carEntity);
        return car == null ? Optional.empty() : Optional.of(car);
    }

    @Override
    public List<Car> getAll() {
        List<Car> carList = new ArrayList<>();
        carEntityList.forEach(car-> carList.add(MapEntityToDomain(car)));

        return carList;
    }

    @Override
    public Car disable(String licensePlate) {
        Optional<CarEntity> car = getEntityByLicensePlate(licensePlate);

        car.get().setStatus("DISABLED");
        carEntityList.removeIf(x -> x.getLicensePlate().equalsIgnoreCase(licensePlate));
        carEntityList.add(car.get());

        return MapEntityToDomain(car.get());
    }

    @Override
    public Car enable(String licensePlate) {
        Optional<CarEntity> car = getEntityByLicensePlate(licensePlate);

        car.get().setStatus("ENABLED");
        carEntityList.removeIf(x -> x.getLicensePlate().equalsIgnoreCase(licensePlate));
        carEntityList.add(car.get());

        return MapEntityToDomain(car.get());
    }

    @Override
    public Car update(Car car) {
        CarEntity carEntity = getEntityByLicensePlate(car.getLicensePlate()).orElse(null);
        if (carEntity != null) {
            carEntity.setBrand(car.getBrand());
            carEntity.setModel(car.getModel());
            carEntity.setYear(car.getYear());
            carEntity.setColor(car.getColor());
            carEntity.setGearType(car.getGearType());
            carEntity.setLicensePlate(car.getLicensePlate());
            carEntity.setType(car.getType());
            carEntityList.removeIf(x-> x.getLicensePlate().equalsIgnoreCase(car.getLicensePlate()));
            carEntityList.add(carEntity);
            return MapEntityToDomain(carEntity);
        }

        return car;
    }

    private Car MapEntityToDomain(CarEntity carEntity) {
        if (carEntity == null)
            return null;

        return Car.builder()
                .brand(carEntity.getBrand())
                .model(carEntity.getModel())
                .type(carEntity.getType())
                .year(carEntity.getYear())
                .color(carEntity.getColor())
                .licensePlate(carEntity.getLicensePlate())
                .gearType(carEntity.getGearType())
                .createdAt(carEntity.getCreatedAt())
                .status(carEntity.getStatus().equalsIgnoreCase("DISABLED") ? CarStatus.DISABLED : CarStatus.ENABLED)
                .build();
    }

    private CarEntity MapDomainToEntity(Car car){
        return CarEntity.builder()
                .brand(car.getBrand())
                .color(car.getColor())
                .model(car.getModel())
                .year(car.getYear())
                .licensePlate(car.getLicensePlate())
                .type(car.getType())
                .gearType(car.getGearType())
                .createdAt(car.getCreatedAt() != null ? car.getCreatedAt() : LocalDateTime.now())
                .status(car.getStatus().equals(CarStatus.ENABLED) ? "ENABLED" : "DISABLED")
                .build();
    }

    private Optional<CarEntity> getEntityByLicensePlate(String licensePlate) {
        return carEntityList.stream().filter(c -> c.getLicensePlate().equalsIgnoreCase(licensePlate)).findFirst();
    }
}
