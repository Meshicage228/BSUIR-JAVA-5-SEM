package org.example.secondTask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
    private Integer countOfPlaces;
    private String nameOfDoctor;
    private Boolean isEmpty;

    public Ward(Integer countOfPlaces, String nameOfDoctor) {
        this.countOfPlaces = countOfPlaces;
        this.nameOfDoctor = nameOfDoctor;
    }

    public Ward(Integer countOfPlaces, Boolean isEmpty) {
        this.countOfPlaces = countOfPlaces;
        this.isEmpty = isEmpty;
    }

    public Ward(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String showCountOfPlaces() {
        return Optional.ofNullable(countOfPlaces)
                .map(integer -> "Current count of places : " + integer)
                .orElse("No places specified");
    }

    public String getDoctorsNameMessage() {
        return "Current doctor : " + Optional.ofNullable(nameOfDoctor)
                .filter(nameOfDoctor -> !nameOfDoctor.isBlank())
                .orElse("is not specified!");
    }

    @Override
    public String toString() {
        return "Ward{" +
                "countOfPlaces = " + countOfPlaces +
                ", nameOfDoctor = " + nameOfDoctor +
                ", isEmpty = " + isEmpty +
                '}';
    }
}
