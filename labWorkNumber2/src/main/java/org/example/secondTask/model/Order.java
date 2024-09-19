package org.example.secondTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Order {
    public static Integer orderCount = 0;
    private Float totalCost;
    private ArrayList<AbstractDisk> abstractDisks;

    public Order() {
        abstractDisks = new ArrayList<>();
    }

    public void addDiskToOrder(AbstractDisk disk){
        abstractDisks.add(disk);
    }
}
