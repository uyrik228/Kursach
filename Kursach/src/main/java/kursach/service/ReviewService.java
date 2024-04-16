package kursach.service;

import java.util.Date;
import java.util.List;

import kursach.entity.Product;
import kursach.entity.Review;
import kursach.entity.User;

public interface ReviewService extends AbstractService<Review> {
    List<Review> readByUser(User user);
    List<Review> readByProduct(Product product);
    List<Review> readByRating(Integer rating);
    List<Review> readByComment(String comment);
    List<Review> readByDate(Date date);
}
