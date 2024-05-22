package kursach.controller;


import kursach.entity.UserForAuth;
import kursach.service.UserForAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "api/Users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	@Autowired
	private UserForAuthService service;


	@GetMapping
	public ResponseEntity<List<UserForAuth>> get() {
		List<UserForAuth> entities = service.read();
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserForAuth> getById(@PathVariable long id) {
		UserForAuth entity = service.read(id);
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<UserForAuth> getUserByName(@PathVariable String name) {
		Optional<UserForAuth> accountUser = service.readByName(name);
		if(!accountUser.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(accountUser.get(), HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UserForAuth> getUserByEmail(@PathVariable String email) {
		Optional<UserForAuth> accountUser = service.readByEmail(email);
		if(!accountUser.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(accountUser.get(), HttpStatus.OK);
	}


	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
