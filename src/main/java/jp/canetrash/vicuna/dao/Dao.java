package jp.canetrash.vicuna.dao;

import java.io.Serializable;
import java.util.List;

/**
 * JPA Like interface
 * 
 * @author tfunato
 *
 */
public interface Dao<T, ID extends Serializable> {

	T save(T entity);

	T findOne(ID primaryKey);

	List<T> findAll();

	Long count();

	void delete(T entity);

	boolean exists(ID primaryKey);

}
