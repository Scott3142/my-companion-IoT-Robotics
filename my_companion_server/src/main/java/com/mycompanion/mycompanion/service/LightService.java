package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.LightDTO;
import com.mycompanion.mycompanion.entity.Light;

public interface LightService {
    LightDTO saveLight(LightDTO newLight);
}
