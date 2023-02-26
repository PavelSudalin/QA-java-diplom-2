package ru.yandex.praktikum.helper;

import ru.yandex.praktikum.orders.Orders;
import java.util.Arrays;

public class OrdersData {
    public static Orders orderBunFluorescent() {
        return new Orders(Arrays.asList("61c0c5a71d1f82001bdaaa6d"));
    }
    public static Orders orderBunCrater() {
        return new Orders(Arrays.asList("61c0c5a71d1f82001bdaaa6c"));
    }
    public static Orders orderBunWithIngredientsImmortalBun() {
        return new Orders(Arrays.asList("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f",
                "61c0c5a71d1f82001bdaaa72", "61c0c5a71d1f82001bdaaa77", "61c0c5a71d1f82001bdaaa79"));
    }
    public static Orders incorrectOrderBun() {
        return new Orders(Arrays.asList("77c0c5a71d1f82001bdaaa6d"));
    }
}
