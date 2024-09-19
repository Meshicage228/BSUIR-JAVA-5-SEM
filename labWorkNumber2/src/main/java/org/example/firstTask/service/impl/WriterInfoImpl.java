package org.example.firstTask.service.impl;

import lombok.AllArgsConstructor;
import org.example.firstTask.model.Ward;
import org.example.firstTask.service.WriterInfo;

import java.util.ArrayList;

@AllArgsConstructor
public class WriterInfoImpl implements WriterInfo {
    private final ArrayList<Ward> wards;

    @Override
    public Integer countEmptyPlacesInWards() {
        return Math.toIntExact(wards.stream()
                .filter(ward -> ward.getHasEmptyPlaces() != null)
                .filter(Ward::getHasEmptyPlaces)
                .count());
    }

    @Override
    public Integer countTotalPlaces() {
        return wards.stream()
                .filter(ward -> ward.getCountOfPlaces() != null)
                .mapToInt(Ward::getCountOfPlaces)
                .sum();
    }
}
