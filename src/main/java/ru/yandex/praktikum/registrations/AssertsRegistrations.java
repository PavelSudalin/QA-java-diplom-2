package ru.yandex.praktikum.registrations;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class AssertsRegistrations {

    public void successfulCreation(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true));
    }

    public void failedCreation(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }

    public void creatingExistingAccount(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .body("message", equalTo("User already exists"));
    }
}
