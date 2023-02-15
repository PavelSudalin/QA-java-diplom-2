package ru.yandex.praktikum.auth.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.auth.Authentication;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Endpoints.*;


public class AuthUsers {
    @Step("Аутентификация пользователя")
    public ValidatableResponse authenticationUser(Authentication user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("Выход пользователем")
    public ValidatableResponse logoutUser(Authentication token) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(token)
                .when()
                .post(LOGOUT)
                .then();
    }

}
