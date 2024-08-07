package com.nl.mo.session;

import java.util.Arrays;
import java.util.List;

public class MenuService {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french frise", true, 540, Type.OTHERS),
            new Dish("rice", true, 350, Type.OTHERS),
            new Dish("session fruit", true, 120, Type.OTHERS),
            new Dish("pizza", false, 550, Type.OTHERS),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salomon", false, 450, Type.FISH)
    );
}
