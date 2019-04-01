package com.mycompanion.mycompanion.entity;

import com.mycompanion.mycompanion.dto.ActivityDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity")
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String activityName;
    private LocalDateTime activityDate;
    private Boolean isCompleted;
    private Long ratingBefore;
    private Long ratingAfter;

    public static Activity convertFromDto(ActivityDTO dto, User user) {
        Activity activity = new Activity();
        activity.setActivityName(dto.getActivityName());
        activity.setActivityDate(LocalDateTime.parse(dto.getActivityDate()));
        activity.setIsCompleted(dto.getIsCompleted());
        activity.setRatingBefore(dto.getRatingBefore());
        activity.setRatingAfter(dto.getRatingAfter());
        activity.setUser(user);
        return activity;

    }
}
