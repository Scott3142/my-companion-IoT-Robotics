package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.MotionDTO;
import com.mycompanion.mycompanion.entity.Motion;
import com.mycompanion.mycompanion.entity.User;
import com.mycompanion.mycompanion.repository.MotionRepository;
import com.mycompanion.mycompanion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class MotionServiceImpl implements MotionService {
    private MotionRepository motionRepository;
    private UserRepository userRepository;

    @Autowired
    public MotionServiceImpl(MotionRepository mRepo, UserRepository uRepo){
        motionRepository = mRepo;
        userRepository = uRepo;
    }

    @Override
    public MotionDTO saveMotion(MotionDTO newMotion) {
        User user = userRepository.findByUuid(newMotion.getUuid());
        Motion motion = motionRepository.saveAndFlush(Motion.convertFromDTO(newMotion, user));
        return new MotionDTO(motion.getId(), motion.getUser().getUuid(), motion.getName(),
                motion.getMotion(), motion.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
