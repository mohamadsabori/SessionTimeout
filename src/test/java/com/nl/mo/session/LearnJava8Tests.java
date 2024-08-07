import com.nl.mo.session.Dish;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static org.junit.Assert.assertEquals;

// Assuming there is an Apple class with a constructor and getWeight method.
class Apple {
    private int weight;

    public Apple(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return weight == apple.weight;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(weight);
    }
}

public class LearnJava8Tests {

    @Test
    public void reverseOrderingByChainingOperators() {
        List<Integer> weights = Arrays.asList(7, 3, 10, 5);
        List<Apple> apples = weights.stream()
                .map(Apple::new)
                .collect(Collectors.toList());
        apples.sort(comparing(Apple::getWeight).reversed());
        apples.forEach(System.out::println);

        // Creating the expected list
        List<Apple> expectedApples = Arrays.asList(new Apple(10), new Apple(7), new Apple(5), new Apple(3));

        assertEquals(expectedApples, apples);
    }

    @Test
    public void composingFunctionsWithAndThen(){
        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(2);
        assertEquals(result,6);
    }

    @Test
    public void composingFunctionWithCompose(){
        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x * 2;
        Function<Integer, Integer> h = f.compose(g);
        int result = h.apply(1);
        assertEquals(result, 3);
    }

    @Test
    public void getLowCaloriesWithJava7(){
        List<Dish> menu = new ArrayList<>();
        List<Dish> lowCaloricDishes = new ArrayList<>();
    }
}
