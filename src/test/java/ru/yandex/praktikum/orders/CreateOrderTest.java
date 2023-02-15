package ru.yandex.praktikum.orders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.auth.Authentication;
import ru.yandex.praktikum.auth.user.AuthUsers;
import ru.yandex.praktikum.helper.OrdersData;
import ru.yandex.praktikum.helper.UserData;
import ru.yandex.praktikum.orders.creatingOrders.CreateOrder;
import ru.yandex.praktikum.registrations.user.UsersRegistration;

@DisplayName("Проверка создания заказов")
public class CreateOrderTest {
    CreateOrder createOrder = new CreateOrder();
    AssertsOrders assertsOrders = new AssertsOrders();
    OrdersData ordersData = new OrdersData();
    AuthUsers authUsers = new AuthUsers();

    UsersRegistration usersRegistration = new UsersRegistration();
    UserData userData = new UserData();
    ValidatableResponse creatingUser;
    ValidatableResponse creatingOrderUser;
    ValidatableResponse authRandomUser;

    Authentication authUserData;
    String userToken;
    String randomUserEmail;


    @Before
    public void creatingTestUser() {
        creatingUser = usersRegistration.userRegistration(userData.randomUser());
        randomUserEmail = creatingUser.extract().path("user.email");
    }

    @Test
    @DisplayName("Создание заказа c авторизацией")
    @Description("Создание заказа c авторизацией")
    public void creatingOrderWithAuthorization() {
        authUserData = new Authentication(randomUserEmail, "12345678");
        authRandomUser = authUsers.authenticationUser(authUserData);
        userToken = authRandomUser.extract().path("accessToken");
        creatingOrderUser = createOrder.creatingOrder(ordersData.orderBunFluorescent());
        assertsOrders.creatingOrderWithAuthorized(creatingOrderUser);
    }

    @Test
    @DisplayName("Создание заказа без авторизацией")
    @Description("Создание заказа без авторизацией")
    public void creatingOrderWithoutAuthorization() {
        creatingOrderUser = createOrder.creatingOrder(ordersData.orderBunCrater());
        assertsOrders.creatingOrderWithoutAuthorized(creatingOrderUser);
    }

    @Test
    @DisplayName("Создание заказа с ингредиентами")
    @Description("Создание заказа с ингредиентами")
    public void creatingOrderWithIngredients() {
        creatingOrderUser = createOrder.creatingOrder(ordersData.orderBunWithIngredientsImmortalBun());
        assertsOrders.creatingOrderWithIngredientsImmortalBun(creatingOrderUser);
    }
    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиентов")
    @Description("Создание заказа с неверным хешем ингредиентов")
    public void creatingOrderWithIncorrectHash() {
        creatingOrderUser = createOrder.creatingOrder(ordersData.incorrectOrderBun());
        assertsOrders.creatingOrderWithIncorrectHash(creatingOrderUser);
    }
    @After
    public void deleteUser() {
        authUserData = new Authentication(randomUserEmail, "12345678");
        authRandomUser = authUsers.authenticationUser(authUserData);
        userToken = authRandomUser.extract().path("accessToken");
        usersRegistration.deleteUser(userToken);
    }
}
