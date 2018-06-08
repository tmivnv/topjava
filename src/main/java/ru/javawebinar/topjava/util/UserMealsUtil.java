package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();

        for (UserMealWithExceed mealWithExceed: list
             ) {

            System.out.println(mealWithExceed.toString());
        }
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        //map of days with exceeded calories
        Map<LocalDate, Integer> datesWithExceededCalories = mealList.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)))
                .entrySet().stream()
                .filter(e -> e.getValue() > caloriesPerDay)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        //building result list
        return mealList.stream()
                //filtering time
                .filter(e->e.getDateTime().toLocalTime().isBefore(endTime)&&e.getDateTime().toLocalTime().isAfter(startTime))
                //creating list of UserMealWithExceed
                .map(e -> {
                        UserMealWithExceed mealWithExceed =
                        new UserMealWithExceed(e.getDateTime(),
                                e.getDescription(),
                                e.getCalories(),
                                datesWithExceededCalories.containsKey(e.getDateTime().toLocalDate()) ? true : false);
                                        return mealWithExceed;

                                })
                .collect(Collectors.toList());




    }
}
