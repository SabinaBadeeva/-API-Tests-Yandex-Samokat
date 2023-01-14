package org.example.Courier;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends Constants {
    public  ValidatableResponse createCourier(Courier courier){
        return given()
                .spec(getSpec())
                .log().all()
                .body(courier)
                .when()
                .post(CREATE_COURIER)
                .then()
                .log().all();}

    public  ValidatableResponse login(CourierCredentials courierCredentials) {
        return given()
                .spec(getSpec())
                .log().all()
                .body(courierCredentials)
                .when()
                .post(LOGIN_COURIER)
                .then()
                .log().all();
    }
    public ValidatableResponse deleteCourier(int idCourier) {
        return given()
                .spec(getSpec())
                .when()
                .delete(DELETE_COURIER + idCourier)
                .then()
                .log().all();
    }
    public int loginUserGetID(CourierCredentials courierCredentials) {
        return login(courierCredentials).extract().path("id");
    }
}
