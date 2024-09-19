package org.example.SecondTask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.SecondTask.model.abstr.Car;
import org.example.firstTask.enums.Brand;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pickap extends Car {
    private String bodyType;

    public Pickap(Double fuelConsumption, Integer accelerationTime, String bodyType) {
        super(fuelConsumption, accelerationTime);
        this.bodyType = bodyType;
    }

    @Override
    public String printInfo() {
        return "Pickap{" +
                "bodyType='" + bodyType + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", brand=" + getBrand() +
                ", accelerationTime=" + accelerationTime +
                '}';
    }

    @Override
    public Brand getBrand() {
        return Brand.MERCEDES;
    }
}
