package kursach.controller;

import kursach.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kursach.entity.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/Products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends AbstractController<Product>{
	@Autowired
	private ProductService service;

	@Override
	public ProductService getService() {
		return service;
	}

	@GetMapping
	public ResponseEntity<List<Product>> get() {
		List<Product> entities = service.read();
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping("/productFinalCost")
	public ResponseEntity<List<Map<String, Object>>> getProductFinalCost() {
		List<Product> products = service.read();
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<Map<String, Object>> productFinalCosts = new ArrayList<>();
		for(Product product : products) {
			Map<String, Object> finalCost = new HashMap<>();
			finalCost.put("name", product.getName());
			finalCost.put("finalCost", product.getPrice().multiply(new BigDecimal(product.getQuantity())));
			productFinalCosts.add(finalCost);
		}

		return new ResponseEntity<>(productFinalCosts, HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable long id) {
		Product entity = service.read(id);
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable String name) {
		Product product = service.readByName(name);
		if(product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/description/{description}")
	public ResponseEntity<Product> getProductByDescription(@PathVariable String description) {
		Product product = service.readByDescription(description);
		if(product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/price/{price}")
	public ResponseEntity<List<Product>> getProductByPrice(@PathVariable BigDecimal price) {
		List<Product> products = service.readByPrice(price);
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

}
