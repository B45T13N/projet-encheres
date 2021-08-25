package org.Encheres.dal.DAO;

import org.Encheres.BusinessException;
import org.Encheres.bo.Utilisateur;

public interface DAOUtilisateur extends DAO<Utilisateur> {

	@Override
	public void insert(Utilisateur data) throws BusinessException;

	public void update(Utilisateur data) throws BusinessException;

	public void delete(int noUtilisateur) throws BusinessException;

	public Utilisateur selectByNoUtilisateur(int noUtilisateur) throws BusinessException;

	public Utilisateur selectUtilisateurCourant(String login, String password) throws BusinessException;

	public Utilisateur selectUtilisateurByEmail(String email) throws BusinessException;

	public Utilisateur updatePasswordByEmail(String motDePasse, String email) throws BusinessException;

}
