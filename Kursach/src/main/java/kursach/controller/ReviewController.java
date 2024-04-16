package kursach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kursach.entity.Review;
import kursach.service.ReviewService;

@RestController
@RequestMapping("api/Reviews")
public class ReviewController  extends AbstractController<Review>{
	@Autowired
	private ReviewService service;
	
	@Override
	public ReviewService getService() {
		return service;
	}
}
