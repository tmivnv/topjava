package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();

        for (UserMealWithExceed mealWithExceed : list
                ) {

            System.out.println(mealWithExceed.toString());
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        //map of days with calories
        Map<LocalDate, Integer> datesWithCalories = new HashMap<>();
        //map of exceeded days
        Map<LocalDate, AtomicBoolean> flags = new HashMap<>();

        //building result list
        return mealList.stream()
                //creating list of UserMealWithExceed
                .map(e -> {

                    //writing to calories-by-date map
                    if (datesWithCalories.containsKey(e.getDateTime().toLocalDate()))
                        datesWithCalories.put(e.getDateTime().toLocalDate(), datesWithCalories.get(e.getDateTime().toLocalDate()) + e.getCalories());
                    else
                        datesWithCalories.put(e.getDateTime().toLocalDate(), e.getCalories());

                    //setting exceed-by-date map

                    if (!flags.containsKey(e.getDateTime().toLocalDate()))
                        flags.put(e.getDateTime().toLocalDate(), new AtomicBoolean(datesWithCalories.get(e.getDateTime().toLocalDate()) > caloriesPerDay));
                    else
                        flags.get(e.getDateTime().toLocalDate()).set(datesWithCalories.get(e.getDateTime().toLocalDate()) > caloriesPerDay);

                    return new UserMealWithExceed(e.getDateTime(),
                                    e.getDescription(),
                                    e.getCalories(),
                                    flags.get(e.getDateTime().toLocalDate()));


                })
                //filtering time
                .filter(e -> e.getDateTime().toLocalTime().isBefore(endTime) && e.getDateTime().toLocalTime().isAfter(startTime))
                .collect(Collectors.toList());


    }
}
