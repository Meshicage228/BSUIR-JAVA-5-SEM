package org.example.firstTask;

import org.example.firstTask.enums.EngineType;
import org.example.firstTask.model.Engine;
import org.example.firstTask.model.abstr.Vehicle;
import org.example.firstTask.model.impl.Car;
import org.example.firstTask.model.impl.Bus;
import org.example.firstTask.model.impl.Truck;

import java.util.ArrayList;

public class FirstTask {
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>(){{
        add(new Bus(1400f, 1000f, 150d, 6000, 28));
        add(new Bus(new Engine(123, EngineType.DIESEL), 1200f, 525f, 5233d, 1245));
        add(new Truck(1240f, 100f, 34d, "OPENED"));
        add(new Car(130f, 120f, 30d, 100));
    }};

    public static void main(String[] args) {
        vehicles.forEach(System.out::println);
    }
}