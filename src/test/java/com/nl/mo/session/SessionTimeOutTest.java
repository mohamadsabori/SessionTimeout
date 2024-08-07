package com.nl.mo.session;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class SessionTimeOutTest extends TestCase {

    @Test
    public void testHi(){
        SessionTimeOut.A timeOut = new SessionTimeOut.A();
        timeOut.addSession("NORMAL");
        timeOut.timeOut("NORMAL");
    }

    @Test
    public void testSomeExpression(){
        Predicate<String> predicate = s -> s.length() > 3;
        Function<String, Boolean> function = predicate::test;
        assertEquals(function.apply("test").booleanValue(), true);
    }

    @Test
    public void testConstructorReference(){
        List<Integer> weights = Arrays.asList(7 , 3 , 10, 5);
        List<Apple> apples = map(weights, Apple::new);
        assertSame(apples,  weights.stream().map(Apple::new).collect(Collectors.toList()));
    }

    @Test
    public void testConstructorReferenceWithBiFunction(){
        List<Integer> weights = Arrays.asList(7 , 3 , 10, 5);
        BiFunction<Integer, String, Apple> referenceFunction = Apple::new;
        TripleFunction<String, String, String, Color> referenceTripleFunction = Color::new;
        assertEquals(referenceTripleFunction.apply("Red","Blue", "Black"), new Color("Red","Blue", "Black"));
    }

    @Test
    public void reverseOrderingByChainingOperators() {
        List<Integer> weights = Arrays.asList(7 , 3 , 10, 5);
        List<Apple> apples = map(weights, Apple::new);
        apples.sort(comparing(Apple::getWeight).reversed());
        apples.forEach(System.out::println);
        List<Apple> expectedApples = Arrays.asList(new Apple(10), new Apple(7), new Apple(5), new Apple(3));
        assertEquals(apples, expectedApples);
    }

    private List<Apple> map(List<Integer> weights, Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for (Integer weight : weights) {
            result.add(f.apply(weight));
        }
        return result;

    }
}

class Color{
    public Color(String a, String b, String c){

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Color){
            Color otherColor = (Color)obj;
        }
        return super.equals(obj);
    }
}

interface TripleFunction<R, S, T, D>{
    D apply(R r, S s, T t);
}