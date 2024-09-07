package org.example.secondTask.service;

import org.example.secondTask.model.Order;
import java.util.HashMap;

public interface OrderService {
    Order createOrder(HashMap<String, Integer> disks);
}
