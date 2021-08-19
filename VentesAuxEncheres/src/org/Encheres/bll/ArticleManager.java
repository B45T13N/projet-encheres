package org.Encheres.bll;

import java.time.LocalDate;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Enchere;
import org.Encheres.bo.Utilisateur;
import org.Encheres.dal.DAO.DAOArticle;
import org.Encheres.dal.DAO.DAOFactory;
import org.Encheres.dal.JDBCImpl.CategorieDAOJdbcImpl;
import org.Encheres.dal.JDBCImpl.EnchereDAOJdbcImpl;

public class ArticleManager {

	private DAOArticle articleDAO;
	private CategorieDAOJdbcImpl categorieDAO;
	private EnchereDAOJdbcImpl enchereDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
		this.categorieDAO = new CategorieDAOJdbcImpl();
		this.enchereDAO = new EnchereDAOJdbcImpl();
	}

	public Article addArticle(LocalDate dateDebutEnchere, LocalDate dateFinEnchere, String description,
			String nomArticle, int miseAPrix, String lieuRetrait, String libelle) throws BusinessException {
		BusinessException businessException = new BusinessException();

		// Test des infos à enregistrer
		this.validerDateHeure(dateDebutEnchere, dateFinEnchere, businessException);
		this.validerDescription(description, businessException);
		this.validerNomArticle(nomArticle, businessException);
		Article article = null;
		Enchere enchere = null;
		// insertion des donnees
		if (!businessException.hasError()) {
			article = new Article();
			enchere = new Enchere();
			// Création de mon article
			article.setDateDebutEncheres(dateDebutEnchere);
			article.setDateFinEncheres(dateFinEnchere);
			article.setDescription(description);
			article.setNomArticle(nomArticle);
			article.setMiseAPrix(miseAPrix);
			article.setPrixVente(miseAPrix);
			int noCategorie = 0;
			noCategorie = categorieDAO.selectByLibelle(libelle);
			article.setNoCategorie(noCategorie);
			article.setLieuRetrait(lieuRetrait);
			// Insert de mon article
			this.articleDAO.insert(article);
			// Creation de la première enchere
			enchere.setDateEnchere(dateDebutEnchere);
			enchere.setMontantEnchere(miseAPrix);
			enchere.setNoArticle(article.getNoArticle());
			enchere.setNoUtilisateur(article.getNoUtilisateur());
			this.enchereDAO.insert(enchere);
		} else {
			throw businessException;
		}

		return article;
	}

	private void validerDateHeure(LocalDate dateEnchere, LocalDate dateFinEnchere, BusinessException exception) {
		if (dateEnchere == null || dateEnchere.isBefore(LocalDate.now()) && dateFinEnchere == null
				|| dateFinEnchere.isBefore(dateEnchere) || dateFinEnchere.isEqual(dateEnchere)) {
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

	private Article updateVenteArticle(Article article, Utilisateur utilisateurCourant, int montantEnchere)
			throws BusinessException {
		BusinessException businessException = new BusinessException();
		Article articleAModif = article;

		// Test des infos à enregistrer
		this.validerDateHeure(LocalDate.now(), article.getDateFinEncheres(), businessException);
		if (!businessException.hasError()) {
			articleAModif.setPrixVente(montantEnchere);
			articleDAO.update(articleAModif);
			enchereDAO.update(article.getNoArticle(), utilisateurCourant.getNoUtilisateur(), montantEnchere,
					LocalDate.now());

		} else {
			throw businessException;
		}

		return articleAModif;

	}

	private List<String> selectAllArticle() throws BusinessException {
		return this.articleDAO.selectAll();
	}

	private List<String> selectArticleByCategorie(String libelle) throws BusinessException {
		int noCategorie = categorieDAO.selectByLibelle(libelle);

		return this.articleDAO.selectByCategorie(noCategorie);
	}

}
