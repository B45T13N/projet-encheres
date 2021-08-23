package org.Encheres.bll;

import java.time.LocalDate;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Enchere;
import org.Encheres.bo.Retrait;
import org.Encheres.dal.DAO.DAOArticle;
import org.Encheres.dal.DAO.DAOFactory;
import org.Encheres.dal.JDBCImpl.CategorieDAOJdbcImpl;

public class ArticleManager {

	private DAOArticle articleDAO;
	private CategorieDAOJdbcImpl categorieDAO;
	private EnchereManager enchereManager;
	private RetraitManager retraitManager;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
		this.categorieDAO = new CategorieDAOJdbcImpl();
		this.enchereManager = new EnchereManager();
		this.retraitManager = new RetraitManager();
	}

	public Article addArticle(Article newArticle, String rue, String ville, String codePostal)
			throws BusinessException {
		BusinessException businessException = new BusinessException();

		// Test des infos à enregistrer
		this.validerDateHeure(newArticle.getDateDebutEncheres(), newArticle.getDateFinEncheres(), businessException);
		this.validerDescription(newArticle.getDescription(), businessException);
		this.validerNomArticle(newArticle.getNomArticle(), businessException);
		Article article = null;
		Enchere enchere = null;
		Retrait retrait = null;
		// insertion des donnees
		if (!businessException.hasError()) {
			article = new Article();
			enchere = new Enchere();
			retrait = new Retrait();
			// Création de mon article
			article.setDateDebutEncheres(newArticle.getDateDebutEncheres());
			article.setDateFinEncheres(newArticle.getDateFinEncheres());
			article.setDescription(newArticle.getDescription());
			article.setNomArticle(newArticle.getNomArticle());
			article.setMiseAPrix(newArticle.getMiseAPrix());
			if (newArticle.getPrixVente() != 0) {
				article.setPrixVente(newArticle.getPrixVente());
			} else {
				article.setPrixVente(newArticle.getMiseAPrix());
			}
			int noCategorie = 0;
			noCategorie = categorieDAO.selectByLibelle(newArticle.getlibelle());
			article.setNoCategorie(noCategorie);
			article.setNoUtilisateur(newArticle.getNoUtilisateur());
			// Insert de mon article
			this.articleDAO.insert(article);
			// Creation de la première enchere
			enchere = enchereBuilder(article.getDateDebutEncheres(), article.getMiseAPrix(), article.getNoArticle(),
					article.getNoUtilisateur());
			this.enchereManager.addEnchere(enchere);
			// Création du retrait
			retrait = retraitBuilder(rue, ville, codePostal, article.getNoArticle());
			this.retraitManager.addRetrait(retrait);
		} else {
			throw businessException;
		}

		return article;
	}

	private Retrait retraitBuilder(String rue, String ville, String codePostal, int idArticle) {

		Retrait retrait = new Retrait();
		retrait.setCodePostal(codePostal);
		retrait.setRue(rue);
		retrait.setId(idArticle);
		retrait.setVille(ville);

		return retrait;
	}

	private Enchere enchereBuilder(LocalDate dateDebut, int montantMiseAPrix, int idArticle, int idUtilisateur) {

		Enchere enchere = new Enchere();
		enchere.setDateEnchere(dateDebut);
		enchere.setMontantEnchere(montantMiseAPrix);
		enchere.setNoArticle(idArticle);
		enchere.setNoUtilisateur(idUtilisateur);

		return enchere;
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

	public Article updateVenteArticle(Article article, int idutilisateur, int montantEnchere) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Article articleAModif = article;

		// Test des infos à enregistrer
		this.validerArticle(articleAModif, businessException);
		this.validerDateHeure(LocalDate.now(), article.getDateFinEncheres(), businessException);
		if (!businessException.hasError()) {
			articleAModif.setPrixVente(montantEnchere);
			articleDAO.update(articleAModif);
			enchereManager.updateEnchere(article, idutilisateur, montantEnchere);

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

	public List<Article> selectAllArticle() throws BusinessException {
		return this.articleDAO.selectAll();
	}

	public List<Article> selectArticleByCategorie(String libelle) throws BusinessException {
		int noCategorie = categorieDAO.selectByLibelle(libelle);

		return this.articleDAO.selectByCategorie(noCategorie);
	}

}
