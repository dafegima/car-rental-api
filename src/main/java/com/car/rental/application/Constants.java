package com.car.rental.application;

public class Constants {
    public static final String API_PATH_RENTALS = "api/rentals";
    public static final String API_PATH_CARS = "api/cars";

    public static final String API_PATH_CARS_DISABLE_ENDPOINT = "/{licensePlate}/disable";
    public static final String API_PATH_CARS_ENABLE_ENDPOINT = "/{licensePlate}/enable";
    public static final String API_PATH_CARS_GET_BY_LICENSE_PLATE_ENDPOINT = "/{licensePlate}";

    public static final String API_PATH_RENTALS_CREATE_RENT_ENDPOINT = "/rent";
    public static final String API_PATH_RENTALS_CANCEL_ENDPOINT = "/{rentalId}/cancel";
    public static final String API_PATH_RENTALS_CONFIRM_ENDPOINT = "/{rentalId}/confirm";
    public static final String API_PATH_RENTALS_GET_BY_CUSTOMER_ID_ENDPOINT = "/customer/{customerId}";
}
