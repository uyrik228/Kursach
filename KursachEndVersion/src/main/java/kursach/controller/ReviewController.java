package kursach.controller;

import kursach.entity.Product;
import kursach.entity.UserForAuth;
import kursach.service.UserForAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kursach.entity.Review;
import kursach.service.ReviewService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/Reviews", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController extends AbstractController<Review>{
	@Autowired
	private ReviewService service;

	@Override
	public ReviewService getService() {
		return service;
	}

	@Autowired
	private UserForAuthService userService;


	@GetMapping("/UserReviewsWithNames")
	public ResponseEntity<List<Map<String, String>>> getUserReviewsWithNames() {
		List<Review> reviews = service.read();
		List<Map<String, String>> userReviews = new ArrayList<>();

		for (Review review : reviews) {
			UserForAuth user = review.getUser();
			Product product = review.getProduct();
			if (user != null && product != null) {
				Map<String, String> userReview = new HashMap<>();
				userReview.put("userName", user.getUsername());
				userReview.put("comment", review.getComment());
				userReview.put("productName", product.getName());
				userReviews.add(userReview);
			}
		}

		if(userReviews.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userReviews, HttpStatus.OK);
	}


	@GetMapping
	public ResponseEntity<List<Review>> get() {
		List<Review> entities = service.read();
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Review> getById(@PathVariable long id) {
		Review entity = service.read(id);
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/UserReviews/{userId}")
	public ResponseEntity<List<Review>> getReviewByUserId(@PathVariable  long userId) {
		List<Review> reviews = service.readByUserId(userId);
		if(reviews.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/ProductReviews/{productId}")
	public ResponseEntity<List<Review>> getReviewByProductId(@PathVariable  long productId) {
		List<Review> reviews = service.readByProductId(productId);
		if(reviews.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/rating/{rating}")
	public ResponseEntity<List<Review>> getReviewByRating(@PathVariable  Integer rating) {
		List<Review> reviews = service.readByRating(rating);
		if(reviews.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

}
