package ru.Korotaev.service;

import ru.Korotaev.Model.StandardData;
import ru.Korotaev.Model.UserDto;

public class CheckStandard implements HospitalStandard{


    @Override
    public boolean checkStandard(StandardData standardData , UserDto userDto, int standardDeviation) {
    int differenceStandardAndPatientDataPulse = Math.round(standardData.getNormPulse()/ userDto.getPulse());
    int differenceStandardAndPatientDataTemperature = Math.round(standardData.getNormTemperature()/ userDto.getTemperature());
    int differenceStandardAndPatientDataHighPressure = Math.round(
            Integer.parseInt(standardData.getNormPressure().split("/")[0])/ Integer.parseInt(userDto.getPressure().split("/")[0]));
        int differenceStandardAndPatientDataLowPressure = Math.round(
                Integer.parseInt(standardData.getNormPressure().split("/")[1])/ Integer.parseInt(userDto.getPressure().split("/")[1]));

        if(differenceStandardAndPatientDataPulse<(standardDeviation*0.01) || differenceStandardAndPatientDataPulse > (standardDeviation*0.01) ||
            differenceStandardAndPatientDataTemperature<(standardDeviation*0.01) || differenceStandardAndPatientDataTemperature>(standardDeviation*0.01)||
            differenceStandardAndPatientDataHighPressure<(standardDeviation*0.01) || differenceStandardAndPatientDataHighPressure>(standardDeviation*0.01)||
            differenceStandardAndPatientDataLowPressure<(standardDeviation*0.01)|| differenceStandardAndPatientDataLowPressure>(standardDeviation*0.01)
        ) return false;
        return true;
    }
}
