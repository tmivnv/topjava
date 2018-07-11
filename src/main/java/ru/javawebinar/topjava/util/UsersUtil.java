package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USER_LIST = Arrays.asList(
            new User(1, "test user 1", "test@user.ru", "testpass", Role.ROLE_ADMIN),
            new User(1, "test user 2", "test2@user.ru", "testpass", Role.ROLE_USER),
            new User(1, "test user 3", "test3@user.ru", "testpass", Role.ROLE_USER),
            new User(1, "test user 4", "test4@user.ru", "testpass", Role.ROLE_ADMIN),
            new User(1, "test user 5", "test5@user.ru", "testpass", Role.ROLE_USER)
    );
}
