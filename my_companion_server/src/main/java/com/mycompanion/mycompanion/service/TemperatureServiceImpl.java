package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.DateTimeDTO;
import com.mycompanion.mycompanion.dto.TemperatureDTO;
import com.mycompanion.mycompanion.entity.Temperature;
import com.mycompanion.mycompanion.entity.User;
import com.mycompanion.mycompanion.repository.TemperatureRepository;
import com.mycompanion.mycompanion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    private TemperatureRepository temperatureRepository;
    private UserRepository userRepository;

    @Autowired
    public TemperatureServiceImpl(TemperatureRepository tRepo, UserRepository uRepo){
        temperatureRepository = tRepo;
        userRepository = uRepo;
    }

    @Override
    public TemperatureDTO saveTemperature(TemperatureDTO newTemp) {
        User user = userRepository.findByUuid(newTemp.getUuid());
        Temperature temperature = temperatureRepository.saveAndFlush(Temperature.convertFromDto(newTemp, user));
        return new TemperatureDTO(temperature.getId(), temperature.getUser().getUuid(), temperature.getName(),
                temperature.getTemperature(), temperature.getHumidity(), temperature.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
