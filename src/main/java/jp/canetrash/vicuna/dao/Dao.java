package jp.canetrash.vicuna.dao;

import java.io.Serializable;

/**
 * JPA Like interface
 * 
 * @author tfunato
 *
 */
public interface Dao<T, ID extends Serializable> {

	T save(T entity);

	T findOne(ID primaryKey);

	Iterable<T> findAll();

	Long count();

	void delete(T entity);

	boolean exists(ID primaryKey);

}
