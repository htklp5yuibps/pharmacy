package no.comp.finalproject.service;

import java.util.List;

import no.comp.finalproject.domain.Entity;

public interface BaseService<T extends Entity> {

	Long create(T entity);
	List<T> read();
	Integer update(T entity);
	T delete(Long id);
	
}
