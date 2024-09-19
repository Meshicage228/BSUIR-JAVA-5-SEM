package org.example.secondTask.model.discImpl;

import org.example.secondTask.enums.DiskType;
import org.example.secondTask.model.AbstractDisk;

import static org.example.secondTask.enums.DiskType.TUTORIAL;

public class TutorialDisk extends AbstractDisk {
    public TutorialDisk(String diskName, Integer count, Float price) {
        super(diskName, count, price);
    }

    @Override
    public DiskType getDiskType() {
        return TUTORIAL;
    }
}
