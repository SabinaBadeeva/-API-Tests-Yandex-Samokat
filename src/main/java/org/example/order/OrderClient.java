package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.example.BaseApi;


import static io.restassured.RestAssured.given;
import static org.example.courier.Constants.ORDER_CREATE;

public class OrderClient extends BaseApi {
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
