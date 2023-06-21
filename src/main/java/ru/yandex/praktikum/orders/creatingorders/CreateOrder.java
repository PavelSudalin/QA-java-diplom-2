package ru.yandex.praktikum.orders.creatingorders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.orders.Orders;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Endpoints.BASE_URL;
import static ru.yandex.praktikum.Endpoints.ORDERS;

public class CreateOrder {
    @Step("Создать заказ")
    public ValidatableResponse creatingOrder(Orders orders) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(orders)
                .when()
                .post(ORDERS)
                .then();
    }
}
