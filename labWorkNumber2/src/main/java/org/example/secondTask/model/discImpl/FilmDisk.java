package org.example.secondTask.model.discImpl;

import org.example.secondTask.enums.DiskType;
import org.example.secondTask.model.AbstractDisk;

import static org.example.secondTask.enums.DiskType.FILM;

public class FilmDisk extends AbstractDisk {
    public FilmDisk(String diskName, Integer count, Float price) {
        super(diskName, count, price);
    }

    @Override
    public DiskType getDiskType() {
        return FILM;
    }
}
