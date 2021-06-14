package ru.Korotaev.service;

import ru.Korotaev.Model.StandardData;
import ru.Korotaev.Model.UserDto;

public interface HospitalStandard {
    boolean checkStandard(StandardData standardData, UserDto userDto, int percent);
}
