package org.example.secondTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.secondTask.enums.DiskType;

@Data
@AllArgsConstructor
public abstract class AbstractDisk {
    protected String diskName;
    protected Integer count;
    protected Float price;

    public abstract DiskType getDiskType();
}
