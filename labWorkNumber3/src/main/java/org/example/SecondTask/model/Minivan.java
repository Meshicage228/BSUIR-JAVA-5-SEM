package org.example.SecondTask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.SecondTask.model.abstr.Car;
import org.example.firstTask.enums.Brand;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Minivan extends Car {
    private Integer back;

    public Minivan(Double fuelConsumption, Integer accelerationTime, Integer back) {
        super(fuelConsumption, accelerationTime);
        this.back = back;
    }

    @Override
    public String printInfo() {
        return "Minivan{" +
                "back=" + back +
                ", fuelConsumption=" + fuelConsumption +
                ", brand=" + getBrand() +
                ", accelerationTime=" + accelerationTime +
                '}';
    }

    @Override
    public Brand getBrand() {
        return Brand.HONDA;
    }
}
