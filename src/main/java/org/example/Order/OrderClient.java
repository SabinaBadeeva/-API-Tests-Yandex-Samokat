package org.example.Order;

import io.restassured.response.ValidatableResponse;
import org.example.Courier.Constants;

import static io.restassured.RestAssured.given;

public class OrderClient extends Constants {
    public ValidatableResponse createOrder (Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_CREATE)
                .then();
    }
    public ValidatableResponse listOrders(){
        return given()
                .spec(getSpec())
                .get(ORDER_CREATE)
                .then();
    }
}
