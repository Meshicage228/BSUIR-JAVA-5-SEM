package org.example.SecondTask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.SecondTask.service.Object;
import org.example.SecondTask.service.Vehicle;
import org.example.firstTask.enums.Brand;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus implements Vehicle, Object {
    private Integer countOfPlaces;

    @Override
    public String printInfo() {
        return "Bus{" +
                "countOfPlaces=" + countOfPlaces +
                ", brand=" + getBrand() +
                '}';
    }

    @Override
    public Brand getBrand() {
        return Brand.MAZ;
    }
}
