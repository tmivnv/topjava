package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal create(Meal meal, int userId) {
        repository.save(meal, userId);
        return meal;
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        if (!repository.delete(id, userId)) throw new NotFoundException("Meal not found");
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        Meal meal = repository.get(id, userId);
        if (meal==null) throw new NotFoundException("Meal not found");
        return meal;
    }

    @Override
    public void update(Meal meal, int userId) {
        repository.save(meal, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return new ArrayList<>(repository.getAll(userId));
    }

    @Override
    public List<Meal> getAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int userId) {
        return repository.getAll(userId).stream().filter(m->DateTimeUtil.timeIsBetween(m.getTime(), startTime, endTime)&&DateTimeUtil.dateIsBetween(m.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }
}