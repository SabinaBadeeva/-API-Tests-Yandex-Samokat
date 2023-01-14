package org.example.courier;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.BaseApi;

import static io.restassured.RestAssured.given;
import static org.example.courier.Constants.*;

public class CourierClient extends BaseApi  {
  @Step ("create courier")
  public  ValidatableResponse create(Courier courier){
        return given()
                .spec(getSpec())
                .log().all()
                .body(courier)
                .when()
                .post(CREATE_COURIER)
                .then()
                .log().all();}

   @Step ("login courier")
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
   @Step ("delete courier")
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
