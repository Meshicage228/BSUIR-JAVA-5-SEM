package org.example.secondTask.service.impl;

import org.example.secondTask.model.Order;
import org.example.secondTask.service.PrintService;

public class PrintServiceImpl implements PrintService {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public void printOrder(Order order) {
        builder.append("Order #").append(Order.orderCount).append("\n");
        builder.append("-------------------------------\n");
        builder.append("Discs:\n");
        for (var disk : order.getAbstractDisks()) {
            builder.append(disk.getDiskType().getDiskDescription())
                    .append(" : (")
                    .append(disk.getDiskName()).append(") ")
                    .append(disk.getCount() * disk.getPrice()).append("$ :")
                    .append(" (").append(disk.getCount()).append(" pcs, for ").append(disk.getPrice()).append("$)\n");
        }
        builder.append("-------------------------------\n");
        builder.append("Total Cost: ").append(order.getTotalCost()).append("$\n");
        System.out.println(builder);
    }
}
