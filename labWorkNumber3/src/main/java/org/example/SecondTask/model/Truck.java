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
public class Truck implements Vehicle, Object {
    private Double payload;
    private String bodyType;

    @Override
    public Brand getBrand() {
        return Brand.MAN;
    }

    @Override
    public String printInfo() {
        return "Truck{" +
                "payload=" + payload +
                ", brand=" + getBrand() +
                ", bodyType='" + bodyType + '\'' +
                '}';
    }
}
