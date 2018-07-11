package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMeal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;

    public int getUserId() {
        return userId;
    }

    public Meal(LocalDateTime dateTime, String description, int calories, int userId) {
        this(null, dateTime, description, calories, userId);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories, int userId) {

        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;

    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}
