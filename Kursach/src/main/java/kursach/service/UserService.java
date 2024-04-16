package kursach.service;

import java.util.List;
import kursach.entity.User;

public interface UserService extends AbstractService<User> {
    List<User> readByName(String name);
    List<User> readByEmail(String email);
}
