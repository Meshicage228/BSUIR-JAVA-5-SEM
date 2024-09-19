package org.example.firstTask;

import java.util.stream.IntStream;

public class FirstTaskFori {
    public static void main(String[] args) {
        for (int i = 20; i < 39; i++) {
            if(i % 2 == 0){
                System.out.println(i);
            }
        }

        IntStream.rangeClosed(20, 39)
                .filter(value -> value % 2 == 0)
                .forEach(System.out::println);
    }
}
