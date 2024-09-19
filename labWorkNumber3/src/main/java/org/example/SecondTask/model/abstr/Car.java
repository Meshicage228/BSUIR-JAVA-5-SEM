package org.example.SecondTask.model.abstr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.SecondTask.service.Object;
import org.example.SecondTask.service.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Car implements Vehicle, Object {
    protected Double fuelConsumption;
    protected Integer accelerationTime;
}
