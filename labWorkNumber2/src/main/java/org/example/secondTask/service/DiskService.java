package org.example.secondTask.service;

import org.example.secondTask.model.AbstractDisk;

public interface DiskService {
    AbstractDisk putDiskToCart(String name, Integer countToBy);
}
