package ru.yandex.praktikum.registrations.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.registrations.Registration;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Endpoints.*;

public class UsersRegistration {
    @Step("Зарегистрировать профиль пользователя")
    public ValidatableResponse userRegistration(Registration user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(REGISTER)
                .then();
    }

    @Step("Удалить профиль пользователя")
    public ValidatableResponse deleteUser(String token) {
        return given()
                .header("Authorization", token)
                .baseUri(BASE_URL)
                .when()
                .delete(DELETE_USER)
                .then();
    }
}
