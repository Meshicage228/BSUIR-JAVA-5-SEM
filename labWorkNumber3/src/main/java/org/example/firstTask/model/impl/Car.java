package org.example.firstTask.model.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.firstTask.enums.Brand;
import org.example.firstTask.enums.EngineType;
import org.example.firstTask.model.Engine;
import org.example.firstTask.model.abstr.Vehicle;

@Getter
@Setter
@NoArgsConstructor
public class Car extends Vehicle {
    private Double fuelConsumption;
    private Integer accelerationTime;

    public Car(Float carWeight, Float mileage, Double fuelConsumption, Integer accelerationTime) {
        super(carWeight, mileage);
        super.engine = new Engine(100, EngineType.CARBURETOR);
        this.fuelConsumption = fuelConsumption;
        this.accelerationTime = accelerationTime;
    }

    public Car(Engine engine, Float carWeight, Float mileage, Double fuelConsumption) {
        super(engine, carWeight, mileage);
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return "Car{" +
                "fuelConsumption=" + fuelConsumption +
                ", accelerationTime=" + accelerationTime +
                ", engine=" + engine +
                ", carWeight=" + carWeight +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public Brand getBrand() {
        return Brand.MERCEDES;
    }
}
