package ru.yandex.praktikum.auth.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.auth.UpdateUser;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Endpoints.BASE_URL;
import static ru.yandex.praktikum.Endpoints.UPDATE_USER;

public class UpdateUsers {
    @Step("Изменение данных пользователя")
    public ValidatableResponse ChangingDataUser(String token, UpdateUser user) {
        return given()
                .header("Authorization", token)
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .patch(UPDATE_USER)
                .then();
    }
}
