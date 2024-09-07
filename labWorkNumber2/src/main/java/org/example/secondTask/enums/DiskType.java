package org.example.secondTask.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DiskType {
    FILM ("Film disk"),
    GAME ("Game disk"),
    SOFT ("Soft disk"),
    TUTORIAL ("Tutorial disk");

    private final String diskDescription;
}
