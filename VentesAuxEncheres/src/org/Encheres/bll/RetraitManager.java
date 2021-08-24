package org.Encheres.bll;

import org.Encheres.BusinessException;
import org.Encheres.bo.Retrait;
import org.Encheres.dal.DAO.DAORetrait;
import org.Encheres.dal.JDBCImpl.RetraitDAOJdbcImpl;

public class RetraitManager {

	private DAORetrait retraitDAO;

	public RetraitManager() {
		this.retraitDAO = new RetraitDAOJdbcImpl();
	}

	public Retrait addRetrait(Retrait retrait) throws BusinessException {
		BusinessException exception = new BusinessException();
		this.validerRetrait(retrait, exception);

		if (!exception.hasError()) {
			retraitDAO.insert(retrait);
		} else {
			throw exception;
		}
		return retrait;
	}

	public void deleteRetrait(int noArticle) throws BusinessException {
		retraitDAO.delete(noArticle);

	}

	public void validerRetrait(Retrait retrait, BusinessException exception) {
		if (retrait.getRue().trim().length() == 0 || retrait.getRue() == null) {
			exception.ajouterErreur(CodesResultatBLL.ARTICLE_ERROR_DESCRIPTION);
		}
		if (retrait.getCodePostal().length() == 0 || retrait.getCodePostal() == null) {
			exception.ajouterErreur(CodesResultatBLL.ARTICLE_ERROR_NOM);
		}
		if (retrait.getVille().length() == 0 || retrait.getVille() == null) {
			exception.ajouterErreur(CodesResultatBLL.ARTICLE_ERROR_NOM);
		}
	}

}
