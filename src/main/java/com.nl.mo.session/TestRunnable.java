package com.nl.mo.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class TestRunnable {
    public static void main(String[] args) {
        /*startProcess(() -> System.out.println("hello world!"));
        startProcess(() -> {});
        try {
            System.out.println(fetch().call());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        List<String> strings = Arrays.asList("Hello", "DDallo","kadrc","Coller");
        strings.sort(String::compareToIgnoreCase);
        strings.forEach(System.out::println);
        Function<String, Integer> stringToInt = Integer::parseInt;
        Function<String, Float> stringToFloat = Float::parseFloat;
        System.out.println(stringToInt.apply("1234").getClass());
        System.out.println(stringToFloat.apply("12"));
        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> containsTwo = List::contains;
        System.out.println(contains.test(strings, "Hello"));
        System.out.println(containsTwo.test(strings, "Hello"));

        List<Integer> weights = Arrays.asList(10, 20, 20, 30, 40);
        List<String> countries = Arrays.asList("Uganda", "Japan", "Iran", "Poland", "The Netherlands");
        List<Apple> apples = map(weights, countries, Apple::new);
        apples.forEach(System.out::println);
        apples.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getCountry));
        apples.forEach(System.out::println);

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println("Composing function value with and-then method for X(1) is :" + h.apply(1));
        System.out.println("Composing function value with compose method for X(1) is :" + f.compose(g).apply(1));
    }

    public static List<Apple> map(List<Integer> weights, List<String> countries, BiFunction<Integer, String, Apple> f){
        List<Apple> result = new ArrayList<>();
        for (int i = 0; i < weights.size(); i++){
            result.add(f.apply(weights.get(i), countries.get(i)));
        }
        return result;
    }

    private static void startProcess(Runnable runnable) {
        runnable.run();
    }

    private static Callable<String> fetch(){
        return () -> "Hello fetch!";
    }
}
