package org.example.secondTask.model.discImpl;

import org.example.secondTask.enums.DiskType;
import org.example.secondTask.model.AbstractDisk;

import static org.example.secondTask.enums.DiskType.SOFT;

public class SoftDisk extends AbstractDisk {
    public SoftDisk(String diskName, Integer count, Float price) {
        super(diskName, count, price);
    }

    @Override
    public DiskType getDiskType() {
        return SOFT;
    }
}
