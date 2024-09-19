package org.example.secondTask;

import org.example.secondTask.model.Order;
import org.example.secondTask.service.OrderService;
import org.example.secondTask.service.PrintService;
import org.example.secondTask.util.TaskFactory;

import java.util.HashMap;

public class SecondTask {
    private static final HashMap<String, Integer> discNames = new HashMap<>(){{
        put("Titanic", 2);
        put("Team Fortress Orange", 12);
        put("Windows Activator", 3);
        put("Survival Tutorial", 4);
        put("Mortal Combat 9", 1);
        put("The Whale", 5);
    }};

    public static void main(String[] args) {
        OrderService orderService = TaskFactory.gerOrderServiceImpl();
        PrintService printService = TaskFactory.getPrintService();
        Order order = orderService.createOrder(discNames);

        printService.printOrder(order);
    }
}
