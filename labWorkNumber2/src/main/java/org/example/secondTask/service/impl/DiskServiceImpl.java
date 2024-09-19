package org.example.secondTask.service.impl;

import lombok.AllArgsConstructor;
import org.example.secondTask.db.DiskStorage;
import org.example.secondTask.exception.OutOfStockException;
import org.example.secondTask.model.AbstractDisk;
import org.example.secondTask.service.DiskService;

@AllArgsConstructor
public class DiskServiceImpl implements DiskService {
    private final DiskStorage storage;

    public AbstractDisk putDiskToCart(String name, Integer countToBy){
        AbstractDisk abstractDisk = storage.getDiskByName(name);

        if(abstractDisk.getCount() < countToBy) {
            throw new OutOfStockException();
        }

        abstractDisk.setCount(countToBy);

        return abstractDisk;
    }
}
