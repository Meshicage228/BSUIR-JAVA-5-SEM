package org.example.secondTask.util;

import org.example.secondTask.db.DiskStorage;
import org.example.secondTask.service.DiskService;
import org.example.secondTask.service.OrderService;
import org.example.secondTask.service.PrintService;
import org.example.secondTask.service.impl.DiskServiceImpl;
import org.example.secondTask.service.impl.OrderServiceImpl;
import org.example.secondTask.service.impl.PrintServiceImpl;

public class TaskFactory {
    public static DiskStorage getDiskStorage(){
        return new DiskStorage();
    }

    public static DiskService getDiskServiceImpl(){
        return new DiskServiceImpl(getDiskStorage());
    }

    public static OrderService gerOrderServiceImpl(){
        return new OrderServiceImpl(getDiskServiceImpl());
    }

    public static PrintService getPrintService(){
        return new PrintServiceImpl();
    }
}
