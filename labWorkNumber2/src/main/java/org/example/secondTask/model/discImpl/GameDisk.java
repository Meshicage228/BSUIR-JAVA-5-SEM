package org.example.secondTask.model.discImpl;

import org.example.secondTask.enums.DiskType;
import org.example.secondTask.model.AbstractDisk;

import static org.example.secondTask.enums.DiskType.GAME;

public class GameDisk extends AbstractDisk {
    public GameDisk(String diskName, Integer count, Float price) {
        super(diskName, count, price);
    }

    @Override
    public DiskType getDiskType() {
        return GAME;
    }
}
