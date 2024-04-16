package kursach.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kursach.entity.Product;
import kursach.entity.Review;
import kursach.entity.User;
import kursach.repository.ReviewRepository;
import kursach.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository rRepository;

    @Override
    public Review read(Long id) {
        return rRepository.findById(id).get();
    }

    @Override
    public List<Review> read() {
        return rRepository.findAll();
    }

    @Override
    public void save(Review entity) {
        rRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        rRepository.deleteById(id);
    }

    @Override
    public void edit(Review entity) {
        rRepository.save(entity);
    }

    @Override
    public List<Review> readByUser(User user) {
        return rRepository.findByUser(user);
    }

    @Override
    public List<Review> readByProduct(Product product) {
        return rRepository.findByProduct(product);
    }

    @Override
    public List<Review> readByRating(Integer rating) {
        return rRepository.findByRating(rating);
    }

    @Override
    public List<Review> readByComment(String comment) {
        return rRepository.findByComment(comment);
    }

    @Override
    public List<Review> readByDate(Date date) {
        return rRepository.findByDate(date);
    }
}