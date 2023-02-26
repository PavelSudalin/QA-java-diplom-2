package ru.yandex.praktikum.registrations;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.yandex.praktikum.auth.Authentication;
import ru.yandex.praktikum.auth.user.AuthUsers;
import ru.yandex.praktikum.helper.UserData;
import ru.yandex.praktikum.registrations.user.UsersRegistration;

@DisplayName("Проверка создания пользователя")
public class RegistrationUserTest {
    private final UserData userData = new UserData();
    private final UsersRegistration usersRegistration = new UsersRegistration();
    private final AssertsRegistrations assertsRegistrations = new AssertsRegistrations();
    private final AuthUsers authUsers = new AuthUsers();
    private ValidatableResponse authRandomUser;
    private ValidatableResponse creatingUser;
    private ValidatableResponse creatingBaseUser;
    private Authentication authUserData;
    private String userToken;
    private String randomUserEmail;

    @Test
    @DisplayName("Регистрация уникального пользователя")
    @Description("Создать уникального пользователя")
    public void creatingUniqueUser() {
        creatingUser = usersRegistration.userRegistration(userData.randomUser());
        assertsRegistrations.successfulCreation(creatingUser);

    }

    @Test
    @DisplayName("Повторная регистрация пользователя")
    @Description("Создать пользователя, который уже зарегистрирован")
    public void createUserAlreadyExists() {
        creatingUser = usersRegistration.userRegistration(userData.baseUser());
        creatingBaseUser = usersRegistration.userRegistration(userData.baseUser());
        assertsRegistrations.creatingExistingAccount(creatingBaseUser);
    }

    @Test
    @DisplayName("Регистрация пользователя с пустым email")
    @Description("создать пользователя и не заполнить одно из обязательных полей.")
    public void createUserEmptyEmail() {
        creatingUser = usersRegistration.userRegistration(userData.userWithEmptyLogin());
        assertsRegistrations.failedCreation(creatingUser);
    }

    @Test
    @DisplayName("Регистрация пользователя с пустым password")
    @Description("создать пользователя и не заполнить одно из обязательных полей.")
    public void createUserEmptyPassword() {
        creatingUser = usersRegistration.userRegistration(userData.userWithEmptyPassword());
        assertsRegistrations.failedCreation(creatingUser);
    }

    @Test
    @DisplayName("Регистрация пользователя с пустым name")
    @Description("создать пользователя и не заполнить одно из обязательных полей.")
    public void createUserEmptyName() {
        creatingUser = usersRegistration.userRegistration(userData.userWithEmptyName());
        assertsRegistrations.failedCreation(creatingUser);
    }

    @After
    public void deleteUser() {
        if (creatingUser.extract().path("user.email") != null) {
            randomUserEmail = creatingUser.extract().path("user.email");
            authUserData = new Authentication(randomUserEmail, "12345678");
            authRandomUser = authUsers.authenticationUser(authUserData);
            userToken = authRandomUser.extract().path("accessToken");
            usersRegistration.deleteUser(userToken);
        }
    }

}
