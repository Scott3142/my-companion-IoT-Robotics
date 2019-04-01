package com.mycompanion.mycompanion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    private Long id;
    private Long uuid;
    private String activityName;
    private String activityDate;
    private Boolean isCompleted;
    private Long ratingBefore;
    private Long ratingAfter;

    public ActivityDTO(Long uuid, String activityName, String activityDate, Boolean isCompleted, Long ratingBefore, Long ratingAfter){
        this.uuid = uuid;
        this.activityName = activityName;
        this.activityDate = activityDate;
        this.isCompleted = isCompleted;
        this.ratingBefore = ratingBefore;
        this.ratingAfter = ratingAfter;
    }
}
