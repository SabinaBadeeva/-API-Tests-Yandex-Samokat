package org.example.Order;

public class OrderGenerator {
    public static Order getDefault(String[] color) {
        return new Order("Иван",
                "Иванов",
                "Московский,100",
                "4",
                "+79001002030",
                "3","2023-02-02",
                "Привезти к 12",
                color);
    }
}
