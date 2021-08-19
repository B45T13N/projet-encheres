package org.Encheres.bll;

import java.time.LocalDate;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Utilisateur;
import org.Encheres.dal.JDBCImpl.EnchereDAOJdbcImpl;

public class EnchereManager {

	private EnchereDAOJdbcImpl enchereDAO;

	public EnchereManager() {
		this.enchereDAO = new EnchereDAOJdbcImpl();
	}

	private void updateEnchere(Article article, Utilisateur utilisateur, int montantEnchere) throws BusinessException {
		BusinessException businessException = new BusinessException();

		this.validerMontantEnchere(article.getPrixVente(), montantEnchere, businessException);
		if (!businessException.hasError()) {
			enchereDAO.update(article.getNoArticle(), utilisateur.getNoUtilisateur(), montantEnchere, LocalDate.now());
		} else {
			throw businessException;
		}
	}

	private void validerMontantEnchere(int montantInitEnchere, int montantEnchere, BusinessException exception) {
		if (montantInitEnchere > montantEnchere || montantInitEnchere == montantEnchere) {
			exception.ajouterErreur(20003);
		}

	}

}