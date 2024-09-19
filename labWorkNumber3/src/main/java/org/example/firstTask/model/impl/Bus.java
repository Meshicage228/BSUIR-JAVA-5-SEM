package org.example.firstTask.model.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.firstTask.enums.Brand;
import org.example.firstTask.enums.EngineType;
import org.example.firstTask.model.Engine;

@Getter
@Setter
@NoArgsConstructor
public class Bus extends Car {
    private Integer countOfPlaces;

    public Bus(Float carWeight, Float mileage, Double fuelConsumption, Integer accelerationTime, Integer countOfPlaces) {
        super(carWeight, mileage, fuelConsumption, accelerationTime);
        super.engine = new Engine(456, EngineType.DIESEL);

        this.countOfPlaces = countOfPlaces;
    }

    public Bus(Engine engine, Float carWeight, Float mileage, Double fuelConsumption, Integer countOfPlaces) {
        super(engine, carWeight, mileage, fuelConsumption);
        this.countOfPlaces = countOfPlaces;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "countOfPlaces=" + countOfPlaces +
                ", fuelConsumption=" + super.getFuelConsumption() +
                ", accelerationTime=" + super.getAccelerationTime() +
                ", countOfPlaces=" + countOfPlaces +
                ", engine=" + engine +
                ", carWeight=" + carWeight +
                ", mileage=" + mileage +
                ", brand=" + getBrand() +
                '}';
    }

    @Override
    public Brand getBrand() {
        return Brand.MAZ;
    }
}
