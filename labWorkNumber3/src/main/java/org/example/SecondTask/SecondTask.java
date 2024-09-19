package org.example.SecondTask;

import org.example.SecondTask.model.Bus;
import org.example.SecondTask.model.Minivan;
import org.example.SecondTask.model.Pickap;
import org.example.SecondTask.model.Truck;
import org.example.SecondTask.model.abstr.Car;

public class SecondTask {
    public static void main(String[] args) {
        Bus bus = new Bus(12);
        System.out.println(bus.printInfo());

        Car minivan = new Minivan(120d, 43, 3);
        System.out.println(minivan.printInfo());

        Car opened = new Pickap(120d, 543, "OPENED");
        System.out.println(opened.printInfo());

        Truck truck = new Truck(100d, "CLOSED");
        System.out.println(truck.printInfo());
    }
}
