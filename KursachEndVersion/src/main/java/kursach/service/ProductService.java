package kursach.service;

import java.math.BigDecimal;
import java.util.List;
import kursach.entity.Product;

public interface ProductService extends AbstractService<Product> {
    Product readByName(String name);
    Product readByDescription(String description);
    List<Product> readByPrice(BigDecimal price);
}