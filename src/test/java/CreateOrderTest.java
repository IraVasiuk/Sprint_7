import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import serial.CreateOrder;
import steps.OrderSteps;

import java.util.List;

import static constants.Url.URL;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final List<String> color;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {List.of("")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
    }

    @Test
    @DisplayName("Success creating order")
    @Description(value = "Check creating order with different list of color.")
    public void creatingOrderSuccess() {
        OrderSteps orderStep = new OrderSteps();
        CreateOrder order = new CreateOrder("Василий", "Пупкин", "Москва", "Лубянка", "+79224222222", 1, "2024-05-09", "Самый быстрый!", color);
        Response createOrderResponse = orderStep.createOrder(order);
        orderStep.checkOrderTrackNotNullNew(createOrderResponse);
    }

}