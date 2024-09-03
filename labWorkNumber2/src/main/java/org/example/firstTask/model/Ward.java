package org.example.firstTask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
    private Integer countOfPlaces;
    private String nameOfDoctor;
    private Boolean hasEmptyPlaces;

    public Ward(Integer countOfPlaces, String nameOfDoctor) {
        this.countOfPlaces = countOfPlaces;
        this.nameOfDoctor = nameOfDoctor;
    }

    public Ward(Integer countOfPlaces, Boolean isEmpty) {
        this.countOfPlaces = countOfPlaces;
        this.hasEmptyPlaces = isEmpty;
    }

    public Ward(Boolean isEmpty) {
        this.hasEmptyPlaces = isEmpty;
    }

    public static Integer countEmptyPlacesInWards(ArrayList<Ward> wards){
        return Math.toIntExact(wards.stream()
                .filter(ward -> ward.hasEmptyPlaces != null)
                .filter(Ward::getHasEmptyPlaces)
                .count());
    }

    public static Integer countTotalPlaces(ArrayList<Ward> wards){
        return wards.stream()
                .filter(ward -> ward.countOfPlaces != null)
                .mapToInt(Ward::getCountOfPlaces)
                .sum();
    }

    @Override
    public String toString() {
        return "Ward{" +
                "countOfPlaces = " + countOfPlaces +
                ", nameOfDoctor = " + nameOfDoctor +
                ", isEmpty = " + hasEmptyPlaces +
                '}';
    }
}
