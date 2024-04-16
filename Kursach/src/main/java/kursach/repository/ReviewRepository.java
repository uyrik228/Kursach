package kursach.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import kursach.entity.Product;
import kursach.entity.Review;
import kursach.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
    List<Review> findByProduct(Product product);
    List<Review> findByRating(Integer rating);
    List<Review> findByComment(String comment);
    List<Review> findByDate(Date date);
}