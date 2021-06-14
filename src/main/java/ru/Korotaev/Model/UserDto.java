package ru.Korotaev.Model;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class UserDto {
    private String  date;
    private String pressure;
    private float pulse;
    private float temperature;
    private Long patientId;
    private Long hospitalId;
}