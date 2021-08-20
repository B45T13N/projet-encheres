package org.Encheres.dal.DAO;

import java.time.LocalDate;

import org.Encheres.BusinessException;
import org.Encheres.bo.Enchere;

public interface DAOEnchere extends DAO<Enchere> {

	@Override
	public void insert(Enchere data) throws BusinessException;

	public void delete(int noArticle) throws BusinessException;

	public void update(int noArticle, int noUtilisateur, int montantEnchere, LocalDate date) throws BusinessException;

	public int selectBuyer(int noArticle) throws BusinessException;
}
