package org.Encheres.dal;

import org.Encheres.BusinessException;
import org.Encheres.bo.Categorie;

public interface DAOCategorie extends DAO<Categorie> {

	@Override
	public void insert(Categorie data) throws BusinessException;

	public int selectByLibelle(String libelle) throws BusinessException;

}
