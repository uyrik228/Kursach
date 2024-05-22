package kursach.service;

import kursach.entity.Review;
import kursach.entity.UserForAuth;
import kursach.enums.Role;
import kursach.repository.ReviewRepository;
import kursach.repository.UserForAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserForAuthService {
    private final UserForAuthRepository uRepository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public UserForAuth save(UserForAuth user) {
        return uRepository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public UserForAuth create(UserForAuth user) {
        if (uRepository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (uRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public UserForAuth getByUsername(String username) {
        return uRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserForAuth getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }


    @Autowired
    private ReviewRepository reviewRepository;

    public UserForAuth read(Long id) {
        return uRepository.findById(id).get();
    }

    public List<UserForAuth> read() {
        return uRepository.findAll();
    }



    public void delete(Long id) {
        List<Review> userReviews = reviewRepository.findByUserId(id);
        for (Review review : userReviews) {
            reviewRepository.delete(review);
        }
        uRepository.deleteById(id);
    }


    public Optional<UserForAuth> readByName(String username) {
        return uRepository.findByUsername(username);
    }

    public Optional<UserForAuth> readByEmail(String email) {
        return uRepository.findByEmail(email);
    }
}
