package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.TemperatureDTO;

public interface TemperatureService {
    TemperatureDTO saveTemperature(TemperatureDTO newTemp);
}
