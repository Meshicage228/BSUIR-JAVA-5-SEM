package org.example.firstTask;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FirstTaskFunctional {
    private static final List<Predicate<Integer>> handler = new ArrayList<>(){{
        add(integer -> integer % 2 == 0);
    }};

    private static void handle(Integer integer){
        handler.stream()
                .filter(entrySet -> entrySet.test(integer))
                .forEach(entrySet -> System.out.println(integer));
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(20, 39)
                .forEach(FirstTaskFunctional::handle);
    }
}
