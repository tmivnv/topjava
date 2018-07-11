package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;


    public void create(Meal meal)
    {
        service.create(meal, SecurityUtil.authUserId());
    }

    public void update(Meal meal)
    {
        service.update(meal, SecurityUtil.authUserId());
    }

    public List<MealWithExceed> getAll()
    {
        return MealsUtil.getWithExceeded(service.getAll(SecurityUtil.authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime)
    {
        return MealsUtil.getWithExceeded(service.getAll(startDate, startTime, endDate, endTime, SecurityUtil.authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}