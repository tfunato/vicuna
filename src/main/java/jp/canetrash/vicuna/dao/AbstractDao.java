package jp.canetrash.vicuna.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Dao base
 * 
 * @author tfunato
 *
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractDao<T, ID extends Serializable> implements
		Dao<T, ID> {

	@Override
	public T save(T entity) {

		throw new UnsupportedOperationException("not implemented yet.");
	}

	@Override
	public T findOne(ID primaryKey) {
		throw new UnsupportedOperationException("not implemented yet.");
	}

	@Override
	public List<T> findAll() {
		throw new UnsupportedOperationException("not implemented yet.");

	}

	@Override
	public Long count() {
		throw new UnsupportedOperationException("not implemented yet.");

	}

	@Override
	public void delete(T entity) {
		throw new UnsupportedOperationException("not implemented yet.");

	}

	@Override
	public boolean exists(ID primaryKey) {
		throw new UnsupportedOperationException("not implemented yet.");

	}

}
