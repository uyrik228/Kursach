package kursach.service.impl;

import java.util.Date;
import java.util.List;

import kursach.entity.UserForAuth;
import kursach.repository.ProductRepository;
import kursach.repository.UserForAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kursach.entity.Product;
import kursach.entity.Review;
import kursach.repository.ReviewRepository;
import kursach.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository rRepository;

    @Autowired
    private ProductRepository pRepository;

    @Autowired
    private UserForAuthRepository uRepository;

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
        Product product = entity.getProduct();
        UserForAuth user = entity.getUser();
        Long pId = product.getId();
        Long uId = user.getId();
        product =  pRepository.findById(pId).orElseThrow(IllegalArgumentException::new);
        user =  uRepository.findById(uId).orElseThrow(IllegalArgumentException::new);
        entity.setProduct(product);
        entity.setUser(user);
        rRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        rRepository.deleteById(id);
    }

    
    public void deleteAll() {
        rRepository.deleteAll();
    }

    @Override
    public void edit(Review entity) {
        rRepository.save(entity);
    }

    @Override
    public List<Review> readByUserId(long userId) {
        return rRepository.findByUserId(userId);
    }

    @Override
    public List<Review> readByProductId(long productId) {
        return rRepository.findByProductId(productId);
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
