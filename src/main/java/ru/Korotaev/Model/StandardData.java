package ru.Korotaev.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class StandardData {
    private String normPressure;
    private float normPulse;
    private float normTemperature;
    private int standardDeviation;

    //значения по умолчанию
    public  StandardData(){
        normPulse = 90;
        standardDeviation = 15;
        normPressure= "120/80";
        normTemperature = 36.6f;
    }
}
