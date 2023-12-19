package org.example.courier;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Constants {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String LOGIN_COURIER = "/api/v1/courier/login";
    public static final String CREATE_COURIER = "/api/v1/courier";
    public static final String ORDER_CREATE = "/api/v1/orders";
    public static final String DELETE_COURIER = "/api/v1/courier/";



}


