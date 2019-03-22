package com.mycompanion.mycompanion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateTimeDTO {
    private int dayOfMonth;
    private String dayOfWeek;
    private int dayOfYear;
    private String month;
    private int monthValue;
    private int year;
    private int hour;
    private int minute;
    private int nano;
    private int second;
    private String isoString;

    public DateTimeDTO(LocalDateTime ldt){
        this.dayOfMonth = ldt.getDayOfMonth();
        this.dayOfWeek = ldt.getDayOfWeek().toString();
        this.dayOfYear = ldt.getDayOfYear();
        this.month = ldt.getMonth().toString();
        this.monthValue = ldt.getMonthValue();
        this.year = ldt.getYear();
        this.hour = ldt.getHour();
        this.minute = ldt.getMinute();
        this.nano = ldt.getNano();
        this.second = ldt.getSecond();
        this.isoString = ldt.toString();
    }

}
