package kursach.service;

import java.util.List;

import kursach.entity.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {

	T read(Long id);

	List<T> read();

	void save(T entity);

	void delete(Long id);
	
	void edit (T entity);
}
