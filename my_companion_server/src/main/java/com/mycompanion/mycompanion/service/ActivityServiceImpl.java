package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.ActivityDTO;
import com.mycompanion.mycompanion.entity.Activity;
import com.mycompanion.mycompanion.entity.User;
import com.mycompanion.mycompanion.repository.ActivityRepository;
import com.mycompanion.mycompanion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;
    private UserRepository userRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository aRepo, UserRepository uRepo){
        activityRepository = aRepo;
        userRepository = uRepo;
    }

    @Override
    public ActivityDTO saveActivity(ActivityDTO newActivity) {
        User user = userRepository.findByUuid(newActivity.getUuid());
        Activity activity = activityRepository.saveAndFlush(Activity.convertFromDto(newActivity, user));
        return new ActivityDTO(activity.getId(), activity.getUser().getUuid(), activity.getActivityName(),
                activity.getActivityDate().format(DateTimeFormatter.ISO_DATE_TIME), activity.getIsCompleted(), activity.getRatingBefore(), activity.getRatingAfter());
    }
}
