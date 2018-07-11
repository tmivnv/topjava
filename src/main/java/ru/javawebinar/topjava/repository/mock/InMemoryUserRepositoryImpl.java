package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);


    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USER_LIST.forEach(this::save);
    }



    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        if (!repository.containsKey(id)) return false;
        else {
            repository.remove(id);
            return true;
        }
    }

    @Override
    public User get(int id) {
        if (!repository.containsKey(id)) return null;
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        if (repository.values().stream().noneMatch(u->u.getEmail().equals(email))) return null;
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(u->u.getEmail().equals(email)).collect(Collectors.toList()).get(0);
    }
}
