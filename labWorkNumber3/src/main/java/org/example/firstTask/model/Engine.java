package org.example.firstTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.firstTask.enums.EngineType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Engine {
    protected Integer enginePower;
    protected EngineType engineType;

    public Engine(Integer enginePower) {
        this.enginePower = enginePower;
    }


}
