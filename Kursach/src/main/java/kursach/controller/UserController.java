package kursach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kursach.entity.User;
import kursach.service.UserService;

@RestController
@RequestMapping(value = "/api/Users", produces = "application/json")
public class UserController extends AbstractController<User> {
	@Autowired
	private UserService service;

	@Override
	public UserService getService() {
		return service;
	}

}
