package ru.yandex.praktikum.auth;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.auth.user.AuthUsers;
import ru.yandex.praktikum.auth.user.UpdateUsers;
import ru.yandex.praktikum.helper.UserData;
import ru.yandex.praktikum.registrations.user.UsersRegistration;

@DisplayName("Проверка изменение данных пользователя")
public class ChangingUserDataTest {
    private final UpdateUsers updateUsers = new UpdateUsers();
    private final AuthUsers authUsers = new AuthUsers();
    private final UsersRegistration usersRegistration = new UsersRegistration();
    private final AssertsAuth assertsAuth = new AssertsAuth();
    private final UserData userData = new UserData();
    private ValidatableResponse creatingUser;
    private ValidatableResponse updateUserData;
    private Authentication authUserData;
    private ValidatableResponse authRandomUser;
    private String userToken;
    private String randomUserEmail;

    @Before
    public void creatingTestUser() {
        creatingUser = usersRegistration.userRegistration(userData.randomUser());
        randomUserEmail = creatingUser.extract().path("user.email");
    }

    @Test
    @DisplayName("Изменение имени пользователя с авторизацией")
    @Description("Изменение имени пользователя с авторизацией")
    public void ChangingUserNameWithAuthorization() {
        authUserData = new Authentication(randomUserEmail, "12345678");
        authRandomUser = authUsers.authenticationUser(authUserData);
        userToken = authRandomUser.extract().path("accessToken");
        updateUserData = updateUsers.ChangingDataUser(userToken, userData.updateUserName(randomUserEmail));
        assertsAuth.successfulUpdateUser(updateUserData);

    }

    @Test
    @DisplayName("Изменение электронного адреса пользователя с авторизацией")
    @Description("Изменение электронного адреса пользователя с авторизацией")
    public void ChangingUserEmailWithAuthorization() {
        authUserData = new Authentication(randomUserEmail, "12345678");
        authRandomUser = authUsers.authenticationUser(authUserData);
        userToken = authRandomUser.extract().path("accessToken");
        updateUserData = updateUsers.ChangingDataUser(userToken, userData.updateUserEmail("TestExample"));
        randomUserEmail = updateUserData.extract().path("user.email");
        assertsAuth.successfulUpdateUser(updateUserData);

    }

    @Test
    @DisplayName("Изменение имени пользователя без авторизации")
    @Description("Изменение имени пользователя без авторизации")
    public void ChangingUserNameWithoutAuthorization() {
        updateUserData = updateUsers.ChangingDataUser("", userData.updateUserName("TestExample"));
        assertsAuth.failedUpdateUser(updateUserData);
    }

    @Test
    @DisplayName("Изменение электронного адреса пользователя без авторизации")
    @Description("Изменение электронного адреса пользователя без авторизации")
    public void ChangingUserEmailWithoutAuthorization() {
        updateUserData = updateUsers.ChangingDataUser("", userData.updateUserEmail("TestExample@TestExample.com"));
        assertsAuth.failedUpdateUser(updateUserData);
    }

    @After
    public void deleteUser() {
            authUserData = new Authentication(randomUserEmail, "12345678");
            authRandomUser = authUsers.authenticationUser(authUserData);
            userToken = authRandomUser.extract().path("accessToken");
            usersRegistration.deleteUser(userToken);
    }
}
