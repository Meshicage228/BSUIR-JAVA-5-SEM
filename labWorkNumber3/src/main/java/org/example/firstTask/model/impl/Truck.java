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
public class Truck extends Vehicle {
    private Double payload;
    private String bodyType;

    public Truck(Float carWeight, Float mileage, Double payload, String bodyType) {
        super(carWeight, mileage);
        super.engine = new Engine(870, EngineType.CARBURETOR);

        this.payload = payload;
        this.bodyType = bodyType;
    }

    public Truck(Engine engine, Float carWeight, Float mileage, Double payload, String bodyType) {
        super(engine, carWeight, mileage);
        this.payload = payload;
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "payload=" + payload +
                ", bodyType='" + bodyType + '\'' +
                ", engine=" + engine +
                ", carWeight=" + carWeight +
                ", mileage=" + mileage +
                ", brand=" + getBrand() +
                '}';
    }

    @Override
    public Brand getBrand() {
        return Brand.MAN;
    }
}
