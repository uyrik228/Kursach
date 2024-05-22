package kursach.service.impl;

import java.math.BigDecimal;
import java.util.List;

import kursach.entity.Review;
import kursach.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kursach.entity.Product;
import kursach.repository.ProductRepository;
import kursach.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository pRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Product read(Long id) {
        return pRepository.findById(id).get();
    }

    @Override
    public List<Product> read() {
        return pRepository.findAll();
    }

    @Override
    public void save(Product entity) {
        pRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        List<Review> productReviews = reviewRepository.findByProductId(id);
        for (Review review : productReviews) {
            reviewRepository.delete(review);
        }
        pRepository.deleteById(id);
    }

    @Override
    public void edit(Product entity) {
        pRepository.save(entity);
    }

    @Override
    public Product readByName(String name) {
        return pRepository.findByName(name);
    }

    @Override
    public Product readByDescription(String description) {
        return pRepository.findByDescription(description);
    }

    @Override
    public List<Product> readByPrice(BigDecimal price) {
        return pRepository.findByPrice(price);
    }
}