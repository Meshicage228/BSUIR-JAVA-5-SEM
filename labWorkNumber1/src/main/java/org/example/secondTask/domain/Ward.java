package org.example.secondTask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

import static java.util.Objects.isNull;

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
        Optional<Integer> count = Optional.ofNullable(countOfPlaces);
        return count.map(integer -> "Current count of places : " + integer).orElse("No places specified");
    }

    public String getDoctorsNameMessage() {
        if (isNull(nameOfDoctor)) {
            return "Doctor is not specified!";
        } else if (nameOfDoctor.isEmpty()) {
            return "Doctor name is blank!";
        }
        return nameOfDoctor + " is current doctor";
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
