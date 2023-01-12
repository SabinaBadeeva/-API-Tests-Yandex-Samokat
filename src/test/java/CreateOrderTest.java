import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.Order.Order;
import org.example.Order.OrderClient;
import org.example.Order.OrderGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.example.Courier.Constants.BASE_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private Order order;
    private OrderClient orderClient;
    private final String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {index}")
    public static Object[][] getColors() {
        return new Object[][]{
                {new String[]{"GRAY", "BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK"}},
                {new String[]{}}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        order = OrderGenerator.getDefault(color);
        orderClient  = new OrderClient();
    }

    @Test
    @DisplayName("Проверка создания заказа и получение track-номера")
    @Description("Можно указать один из цветов — BLACK или GREY, можно указать оба цвета, можно совсем не указывать цвет")
    public void orderCanBeCreatedTest() {
        //создаем заказ
        ValidatableResponse response = orderClient.createOrder(order);
        //запрос возвращает код ответа
        int statusCode = response.extract().statusCode();
        // проверяем статус код
        assertEquals("Соответствует 201", SC_CREATED, statusCode);
        //получаем track
        int trackNumber = response.extract().path("track");
        //проверяем,что track не равен null
        assertThat("Expected track number", trackNumber, notNullValue());
    }
}
