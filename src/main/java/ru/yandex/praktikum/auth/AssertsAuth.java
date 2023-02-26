package ru.yandex.praktikum.auth;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class AssertsAuth {
    public void successfulAuthentication(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true));
    }

    public void failedAuthentication(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("message", equalTo("email or password are incorrect"));
    }

    public void successfulUpdateUser(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true));
    }

    public void failedUpdateUser(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("success", is(false));
    }
}
