package org.Encheres.dal;

import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Utilisateur;

public interface DAOUtilisateur extends DAO<Utilisateur> {

	@Override
	public void insert(Utilisateur data) throws BusinessException;
	
	public void update(Utilisateur data) throws BusinessException;
	
	public void delete(int noUtilisateur) throws BusinessException;
	
	public List<Utilisateur> selectByNoUtilisateur(int noUtilisateur) throws BusinessException;
	
}
