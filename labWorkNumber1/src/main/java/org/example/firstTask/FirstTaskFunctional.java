package org.example.firstTask;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FirstTaskFunctional {
    private static HashMap<Predicate<Integer>, Function<Integer, String>> handler = new HashMap<>(){{
        put(integer -> integer % 2 == 0, String::valueOf);
        put(integer -> integer % 3 == 0, String::valueOf);
    }};

    private static void handle(Integer integer){
        handler.entrySet().stream()
                .filter(entrySet -> entrySet.getKey().test(integer))
                .forEach(entrySet -> System.out.println(entrySet.getValue().apply(integer)));
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(20, 39)
                .forEach(FirstTaskFunctional::handle);
    }
}
