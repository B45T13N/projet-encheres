package org.Encheres.dal;

import org.Encheres.BusinessException;

public interface DAO<T> {

	public void insert(T data) throws BusinessException;

}
