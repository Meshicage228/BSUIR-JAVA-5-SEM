package org.example.firstTask;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FirstTaskFunctional {
    private static HashMap<Predicate<Integer>, Consumer<Integer>> handler = new HashMap<>(){{
        put(integer -> integer % 2 == 0, System.out::println);
    }};

    private static void handle(Integer integer){
        handler.entrySet().stream()
                .filter(entry -> entry.getKey().test(integer))
                .peek(entry -> entry.getValue().accept(integer))
                .findFirst();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(20, 39).forEach(FirstTaskFunctional::handle);
    }
}
