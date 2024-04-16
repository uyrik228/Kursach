package kursach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kursach.entity.Product;
import kursach.service.ProductService;

@RestController
@RequestMapping("api/Products")
public class ProductController extends AbstractController<Product>{
	@Autowired
	private ProductService service;
	
	@Override
	public ProductService getService() {
		return service;
	}
	
}
