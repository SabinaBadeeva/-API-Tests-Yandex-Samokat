import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.order.OrderClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.apache.http.HttpStatus.SC_OK;
import static org.example.courier.Constants.BASE_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListOrderTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        orderClient = new OrderClient();
    }
    @Test
    @DisplayName ("Вернуть список заказов")
    @Description("Проверить, что в тело ответа возвращается список заказов")
    public void getReturnedOrderList() {
        // запрос списка заказов
        ValidatableResponse response =orderClient.listOrders();
        //запрос возвращает код ответа
        int statusCode = response.extract().statusCode();
        // проверяем статус код
        assertEquals("Статус 200",SC_OK,statusCode);
        //Проверить, что в тело ответа возвращается список заказов
        ArrayList actualOrderList = response.extract().path("orders");
        boolean order =  !actualOrderList.isEmpty() && actualOrderList !=null;
        //проверяем, что список больше нуля
        assertTrue("Список не пустой и больше нуля",order);
    }
}
