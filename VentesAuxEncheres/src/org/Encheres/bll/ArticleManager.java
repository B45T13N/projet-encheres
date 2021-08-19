package org.Encheres.bll;

import java.time.LocalDate;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.dal.DAO.DAO;
import org.Encheres.dal.DAO.DAOFactory;

public class ArticleManager {

	private DAO<Article> articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	public Article addArticle(LocalDate dateDebutEnchere, LocalDate dateFinEnchere, String description,
			String nomArticle, int miseAPrix, String lieuRetrait, int noCategorie) throws BusinessException {
		BusinessException businessException = new BusinessException();

		// Test des infos à enregistrer
		this.validerDateHeure(dateDebutEnchere, dateFinEnchere, businessException);
		this.validerDescription(description, businessException);
		this.validerNomArticle(nomArticle, businessException);
		Article article = null;
		// insertion des donnees
		if (!businessException.hasError()) {
			article = new Article();
			article.setDateDebutEncheres(dateDebutEnchere);
			article.setDateFinEncheres(dateFinEnchere);
			article.setDescription(description);
			article.setNomArticle(nomArticle);
			article.setMiseAPrix(miseAPrix);
			article.setPrixVente(miseAPrix);
			if (dateDebutEnchere.isEqual(LocalDate.now())) {
				article.setEtatVente("en cours");
			} else {
				article.setEtatVente("en attente");
			}

		}

		return null;
	}

	private void validerDateHeure(LocalDate dateDebutEnchere, LocalDate dateFinEnchere, BusinessException exception) {
		if (dateDebutEnchere == null || dateDebutEnchere.isBefore(LocalDate.now()) && dateFinEnchere == null
				|| dateFinEnchere.isBefore(dateDebutEnchere) || dateFinEnchere.isEqual(dateDebutEnchere)) {
			exception.ajouterErreur(20000);
		}

	}

	private void validerNomArticle(String nomArticle, BusinessException exception) {
		if (nomArticle == null || nomArticle.length() > 30) {
			exception.ajouterErreur(20001);
		}
	}

	private void validerDescription(String description, BusinessException exception) {
		if (description == null || description.length() > 300) {
			exception.ajouterErreur(20001);
		}
	}

}
