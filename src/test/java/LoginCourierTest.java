import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierCredentials;
import org.example.courier.CourierGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.example.courier.Constants.BASE_URL;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class LoginCourierTest {

    private CourierClient courierClient;
    private Courier courierLogged;
    private Courier courierNonExistent;
    private Courier  courierWithoutLogin;
    private int courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        courierClient = new CourierClient();
        courierLogged = CourierGenerator.getDataRegisteredCourier();
        courierNonExistent = CourierGenerator.getNoRegisteredCourier();
        courierWithoutLogin = CourierGenerator.getLoggedWithoutLogin();
    }



    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("Для авторизации нужно передать все обязательные поля; успешный запрос возвращает id")
    public void loginCourierDefaultTest() {
        //авторизация со всеми полями
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courierLogged));
        //запрос возвращает  код ответа
        int codeResponse = response.extract().statusCode();
        assertEquals("Соответствует 200", SC_OK, codeResponse);
        //успешная авторизация возвращает id
        courierId = response.extract().path("id");
        //проверяем, что id  не пустой
        response.assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Если авторизоваться под несуществующим пользователем, если неправильно указать логин или пароль, запрос возвращает ошибку")
    public void loggedCourierNonExistentTest() {
        //аавторизация под несуществующими логином и паролем
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courierNonExistent));
        //запрос возвращает код ответа
        int codeResponse = response.extract().statusCode();
        // проверяем статус код
        assertEquals("Соответствует 404",SC_NOT_FOUND,codeResponse);
        // ответ соответствует Body Message
        String message = response.extract().path("message");
        assertEquals("",message,"Учетная запись не найдена");
    }

    @Test
    @DisplayName("Авторизация без логина")
    @Description("если какого-то поля нет, запрос возвращает ошибку")
    public void loggedCourierWithoutLoginTest() {
        //авторизация без логина
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courierWithoutLogin));
        //запрос возвращает  код ответа
        int codeResponse = response.extract().statusCode();
        // проверяем статус код
        assertEquals("Соответствует 400",SC_BAD_REQUEST,codeResponse);
        // ответ соответствует Body Message
        String message = response.extract().path("message");
        assertEquals("Авторизация невозможна",message,"Недостаточно данных для входа");
    }
}
