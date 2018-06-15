package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserMealWithExceed {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    //Added getter
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    //Changed to pointer class
    private final AtomicBoolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, AtomicBoolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return dateTime+" "+dateTime.toLocalTime()+" "+description+" "+calories+" "+exceed.toString();
    }
}
