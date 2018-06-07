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

        List<UserMealWithExceed> mealWithExceedList = new ArrayList<>();

        mealList.stream()

                //grouping by date
                .collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate()))
                //iterating every entry
                .forEach((k,v)->v
                        //filtering by time
                        .stream().filter(m->m.getDateTime().toLocalTime().isAfter(startTime)&&m.getDateTime().toLocalTime().isBefore(endTime))
                        //adding
                        .forEach(
                                e-> mealWithExceedList.add(new UserMealWithExceed(e.getDateTime(), e.getDescription(), e.getCalories(), v.stream().mapToInt(UserMeal::getCalories).sum() > caloriesPerDay))
                        )
                );


        return mealWithExceedList;
    }
}
