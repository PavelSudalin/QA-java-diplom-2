package ru.yandex.praktikum.helper;

import ru.yandex.praktikum.auth.UpdateUser;
import ru.yandex.praktikum.registrations.Registration;

public class UserData {
    public static Registration baseUser() {
        return new Registration("ExampleTest@test.ru", "12345678", "TestExample");
    }
    public static Registration randomUser() {
        double max = 9999999;
        double min = 0000000;
        int randomInt = (int) (Math.random() * (max - min));
        return new Registration("test" + randomInt + "@test.ru", "12345678", "Test" + randomInt);
    }

    public static Registration userWithEmptyLogin() {
        return new Registration(null, "12345678", "TestExample");
    }

    public static Registration userWithEmptyPassword() {
        return new Registration("ExampleTest@test.ru", null, "TestExample");
    }
    public static Registration userWithEmptyName() {
        return new Registration("ExampleTest@test.ru", "12345678", null);
    }

    public static UpdateUser updateUserName(String email) {
        double max = 999;
        double min = 000;
        int randomInt = (int) (Math.random() * (max - min));
        return new UpdateUser("TestExample" + randomInt, email);
    }
    public static UpdateUser updateUserEmail(String name) {
        double max = 999;
        double min = 000;
        int randomInt = (int) (Math.random() * (max - min));
        return new UpdateUser(name,"test" + randomInt + "@test.ru");
    }
}
