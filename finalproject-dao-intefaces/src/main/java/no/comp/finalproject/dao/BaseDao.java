package no.comp.finalproject.dao;

import java.util.List;

import no.comp.finalproject.entity.Entity;

public interface BaseDao<T extends Entity> {
	
	Long create(T entity);
	List<T> read();
	Integer update(T entity);
	T delete(Long id);

}
