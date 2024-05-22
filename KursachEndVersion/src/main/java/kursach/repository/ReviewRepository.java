package kursach.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import kursach.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(long userId);
    List<Review> findByProductId(long productId);
    List<Review> findByRating(Integer rating);
    List<Review> findByComment(String comment);
    List<Review> findByDate(Date date);
}