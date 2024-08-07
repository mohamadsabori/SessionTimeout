package com.nl.mo.session;

public class Dish {

    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    private final String name;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }
}
