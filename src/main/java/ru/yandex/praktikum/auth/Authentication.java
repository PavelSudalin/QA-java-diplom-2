package ru.yandex.praktikum.auth;

import ru.yandex.praktikum.registrations.Registration;

public class Authentication {
    private String email;
    private String password;

    public static Authentication fromRegistrationUser(Registration user){
        return new Authentication(user.getEmail(), user.getPassword());
    }

    public Authentication(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
