package kursach.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kursach.entity.User;
import kursach.repository.UserRepository;
import kursach.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository uRepository;

    @Override
    public User read(Long id) {
        return uRepository.findById(id).get();
    }

    @Override
    public List<User> read() {
        return uRepository.findAll();
    }

    @Override
    public void save(User entity) {
        uRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        uRepository.deleteById(id);
    }

    @Override
    public void edit(User entity) {
        uRepository.save(entity);
    }

    @Override
    public List<User> readByName(String name) {
        return uRepository.findByName(name);
    }

    @Override
    public List<User> readByEmail(String email) {
        return uRepository.findByEmail(email);
    }
}