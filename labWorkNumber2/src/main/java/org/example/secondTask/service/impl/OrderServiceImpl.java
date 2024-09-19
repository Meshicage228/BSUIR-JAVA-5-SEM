package org.example.secondTask.service.impl;
import lombok.RequiredArgsConstructor;
import org.example.secondTask.model.Order;
import org.example.secondTask.service.DiskService;
import org.example.secondTask.service.OrderService;

import java.util.HashMap;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final DiskService diskService;

    @Override
    public Order createOrder(HashMap<String, Integer> disks) {
        Order order = new Order();

        float sum = (float) disks.entrySet().stream()
                .map(enty -> diskService.putDiskToCart(enty.getKey(), enty.getValue()))
                .peek(order::addDiskToOrder)
                .mapToDouble(disk -> disk.getPrice() * disk.getCount())
                .sum();

        order.setTotalCost(sum);
        Order.orderCount++;

        return order;
    }

}
