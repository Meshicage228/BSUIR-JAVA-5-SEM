package org.example.firstTask.model.abstr;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.firstTask.enums.Brand;
import org.example.firstTask.model.Engine;

@Data
@NoArgsConstructor
public abstract class Vehicle {
    protected Engine engine;
    protected Float carWeight;
    protected Float mileage;

    public Vehicle(Engine engine, Float carWeight, Float mileage) {
        this.engine = engine;
        this.carWeight = carWeight;
        this.mileage = mileage;
    }

    public Vehicle(Float carWeight, Float mileage) {
        this.carWeight = carWeight;
        this.mileage = mileage;
    }

    public abstract Brand getBrand();
}
