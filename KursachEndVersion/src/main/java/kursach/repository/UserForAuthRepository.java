package kursach.repository;

import kursach.entity.UserForAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserForAuthRepository extends JpaRepository<UserForAuth, Long> {
    Optional<UserForAuth> findByUsername(String username);
    Optional<UserForAuth> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
