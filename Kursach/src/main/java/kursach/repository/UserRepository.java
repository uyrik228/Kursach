package kursach.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import kursach.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);
}