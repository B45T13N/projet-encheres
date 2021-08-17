package org.Encheres.dal;

import org.Encheres.BusinessException;
import org.Encheres.bo.Utilisateur;

public interface DAOUtilisateur extends DAO<Utilisateur> {

	@Override
	public void insert(Utilisateur data) throws BusinessException;
	
	public void update(int noUtilisateur) throws BusinessException;
	
	public void delete(int noUtilisateur) throws BusinessException;
	
}
