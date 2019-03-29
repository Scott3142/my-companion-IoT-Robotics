package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.LightDTO;
import com.mycompanion.mycompanion.dto.TemperatureDTO;
import com.mycompanion.mycompanion.entity.Light;
import com.mycompanion.mycompanion.entity.Temperature;
import com.mycompanion.mycompanion.entity.User;
import com.mycompanion.mycompanion.repository.LightRepository;
import com.mycompanion.mycompanion.repository.TemperatureRepository;
import com.mycompanion.mycompanion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class LightServiceImpl implements LightService {
    private LightRepository lightRepository;
    private UserRepository userRepository;

    @Autowired
    public LightServiceImpl(LightRepository lRepo, UserRepository uRepo){
        lightRepository = lRepo;
        userRepository = uRepo;
    }

    @Override
    public LightDTO saveLight(LightDTO newLight) {
        User user = userRepository.findByUuid(newLight.getUuid());
        Light light = lightRepository.saveAndFlush(Light.convertFromDto(newLight, user));
        return new LightDTO(light.getId(), light.getUser().getUuid(), light.getName(),
                light.getLight(), light.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
