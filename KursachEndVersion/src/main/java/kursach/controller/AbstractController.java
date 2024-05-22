package kursach.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import kursach.entity.AbstractEntity;
import kursach.service.AbstractService;

@RestController
@RequestMapping
public abstract class AbstractController<T extends AbstractEntity> {


	@GetMapping
	public ResponseEntity<List<T>> get() {
		List<T> entities = getService().read();
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable long id) {
		T entity = getService().read(id);
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update")
	public ResponseEntity<String> put(@RequestBody T entity) {
		getService().edit(entity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<String> post(@RequestBody T entity) {
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		getService().delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public abstract AbstractService<T> getService();
}
