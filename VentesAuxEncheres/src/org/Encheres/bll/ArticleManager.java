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

public class ArticleManager {

	private DAOArticle articleDAO;
	private CategorieDAOJdbcImpl categorieDAO;
	private EnchereManager enchereManager;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
		this.categorieDAO = new CategorieDAOJdbcImpl();
		this.enchereManager = new EnchereManager();
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
			this.enchereManager.addEnchere(enchere);
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
			exception.ajouterErreur(CodesResultatBLL.ARTICLE_ERROR_NOM);
		}
	}

	private void validerDescription(String description, BusinessException exception) {
		if (description == null || description.length() > 300) {
			exception.ajouterErreur(CodesResultatBLL.ARTICLE_ERROR_DESCRIPTION);
		}
	}

	private void validerArticle(Article article, BusinessException exception) {
		if (article == null) {
			exception.ajouterErreur(CodesResultatBLL.INSERT_NULL);
		}
		this.validerDateHeure(article.getDateDebutEncheres(), article.getDateFinEncheres(), exception);
		if (article.getDescription().trim().length() == 0 || article.getDescription() == null) {
			exception.ajouterErreur(CodesResultatBLL.ARTICLE_ERROR_DESCRIPTION);
		}
		if (article.getNomArticle().trim().length() == 0 || article.getNomArticle() == null) {
			exception.ajouterErreur(CodesResultatBLL.ARTICLE_ERROR_NOM);
		}
	}

	public void updateArticle(Article updateArticle) throws BusinessException {
		BusinessException exception = new BusinessException();

		this.validerArticle(updateArticle, exception);
		if (!exception.hasError()) {
			articleDAO.update(updateArticle);
		} else {
			throw exception;
		}

	}

	public Article updateVenteArticle(Article article, Utilisateur utilisateurCourant, int montantEnchere)
			throws BusinessException {
		BusinessException businessException = new BusinessException();
		Article articleAModif = article;

		// Test des infos à enregistrer
		this.validerArticle(articleAModif, businessException);
		this.validerDateHeure(LocalDate.now(), article.getDateFinEncheres(), businessException);
		if (!businessException.hasError()) {
			articleAModif.setPrixVente(montantEnchere);
			articleDAO.update(articleAModif);
			enchereManager.updateEnchere(article, utilisateurCourant, montantEnchere);

		} else {
			throw businessException;
		}

		return articleAModif;

	}

	public void deleteArticle(Article article) throws BusinessException {
		BusinessException businessException = new BusinessException();

		// test des infos à supprimer
		this.validerArticle(article, businessException);

		if (!businessException.hasError()) {
			articleDAO.delete(article.getNoArticle());
		}
	}

	public List<String> selectAllArticle() throws BusinessException {
		return this.articleDAO.selectAll();
	}

	public List<String> selectArticleByCategorie(String libelle) throws BusinessException {
		int noCategorie = categorieDAO.selectByLibelle(libelle);

		return this.articleDAO.selectByCategorie(noCategorie);
	}

}
