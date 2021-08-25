package org.Encheres.dal.JDBCImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.dal.CodesResultatDAL;
import org.Encheres.dal.DAO.DAOArticle;
import org.Encheres.dal.JDBCTools.ConnectionProvider;

public class ArticleDAOJdbcImpl implements DAOArticle {

	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, "
			+ "date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)"
			+ " VALUES(?,?,?,?,?,?,?,?)";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	public static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, prix_initial=?, date_debut_encheres = ?, "
			+ "date_fin_encheres = ?, no_categorie =?, prix_vente = ?  WHERE no_article = ?";
	public static final String SELECT_ALL = "SELECT u.no_utilisateur, nom_article, description, c.libelle as libelle, prix_vente, date_fin_encheres, pseudo, a.no_categorie, a.no_article as noArticle "
			+ "FROM ARTICLES_VENDUS a " + "INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie";
	public static final String SELECT_BY_CATEGORIE = "SELECT u.no_utilisateur, nom_article, prix_initial, date_fin_encheres, a.no_article, pseudo, c.libelle as libelle"
			+ "pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE no_categorie=?";
	public static final String SELECT_BY_ARTICLE = "SELECT u.no_utilisateur as noUser, description, nom_article, prix_initial, montant_enchere, date_debut_encheres, date_fin_encheres, c.libelle as libelle, "
			+ "u.pseudo, a.no_article, r.rue as rue, r.code_postal as cpo, r.ville as ville "
			+ "FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN ENCHERES e ON e.no_article = a.no_article "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie "
			+ "INNER JOIN RETRAITS r ON r.no_article = a.no_article" + " WHERE a.no_article=?";

	public static final String SELECT_ALL_ARTICLES = "SELECT u.no_utilisateur as noUser, description, nom_article, prix_initial, montant_enchere, date_debut_encheres, date_fin_encheres, c.libelle as libelle, "
			+ "u.pseudo, a.no_article, r.rue as rue, r.code_postal as cpo, r.ville as ville FROM ARTICLES_VENDUS INNER JOIN UTILISATEUR INNER JOIN ENCHERES WHERE date_fin_encheres > GETDATE() ORDER BY date_fin_encheres DESC";

	public static final String SELECT_ENCHERES_USER = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =? AND date_fin_encheres > GETDATE() ORDER BY date_fin_encheres DESC";

	public static final String SELECT_ENCHERES_USER_CLAIMED = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =? AND date_fin_encheres < GETDATE() ORDER BY date_fin_encheres DESC";

	public static final String SELECT_SALES_IN_PROGRESS = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =? AND date_fin_encheres > GETDATE() ORDER BY date_fin_encheres DESC";

	public static final String SELECT_SALES_NOT_STARTED = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =? AND date_debut_encheres < GETDATE() ORDER BY date_debut_encheres DESC";

	public static final String SELECT_SALES_SOLD = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =? AND date_fin_encheres < GETDATE() ORDER BY date_fin_encheres DESC";

	@Override
	public void insert(Article data) throws BusinessException {
		if (data == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'un article
				PreparedStatement prstms = cnx.prepareStatement(INSERT_ARTICLE,
						PreparedStatement.RETURN_GENERATED_KEYS);

				prstms.setString(1, data.getNomArticle());
				prstms.setString(2, data.getDescription());

				prstms.setDate(3, Date.valueOf(data.getDateDebutEncheres()));
				prstms.setDate(4, Date.valueOf(data.getDateFinEncheres()));

				prstms.setInt(5, data.getMiseAPrix());
				prstms.setInt(6, data.getMiseAPrix());

				prstms.setInt(7, data.getNoUtilisateur());
//				// Récupération du libelle categorie
//				CategorieDAOJdbcImpl categorie = new CategorieDAOJdbcImpl();
//				String libelle = data.getlibelle().toLowerCase();
//				int noCategorie = categorie.selectByLibelle(libelle);
				prstms.setInt(8, data.getNoCategorie());
				prstms.executeUpdate();
				ResultSet rs = prstms.getGeneratedKeys();
				if (rs.next()) {
					data.setNoArticle(rs.getInt(1));
				}
				rs.close();
				prstms.close();
				cnx.commit();
				cnx.close();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_FAIL);
			throw businessException;
		}
	}

	@Override
	public void delete(int noArticle) throws BusinessException {
		if (noArticle < 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'un article
				PreparedStatement prstms = cnx.prepareStatement(DELETE_ARTICLE);
				prstms.setInt(1, noArticle);
				prstms.executeUpdate();

				prstms.close();
				cnx.commit();

				cnx.close();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.DELETE_FAIL);
				throw businessException;
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_FAIL);
			throw businessException;
		}
	}

	@Override
	public void update(Article data) throws BusinessException {

		if (data == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Mise à jour article
				PreparedStatement prstms = cnx.prepareStatement(UPDATE_ARTICLE);
				if (LocalDate.now() != data.getDateDebutEncheres()) {
					prstms.setString(1, data.getNomArticle());
					prstms.setString(2, data.getDescription());
					prstms.setInt(3, data.getMiseAPrix());
					prstms.setDate(4, Date.valueOf(data.getDateDebutEncheres()));
					prstms.setDate(5, Date.valueOf(data.getDateFinEncheres()));
					// Récupération du libelle categorie
//					CategorieDAOJdbcImpl categorie = new CategorieDAOJdbcImpl();
//					String libelle = data.getlibelle().toLowerCase();
//					int noCategorie = categorie.selectByLibelle(libelle);
					prstms.setInt(6, data.getNoCategorie());
					prstms.setInt(7, data.getPrixVente());
					prstms.setInt(8, data.getNoArticle());

				}
				prstms.executeUpdate();
				prstms.close();
				cnx.commit();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();

			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_FAIL);
			throw businessException;
		}

	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		List<Article> list = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Mise à jour article
				PreparedStatement prstms = cnx.prepareStatement(SELECT_ALL);
				ResultSet rs = prstms.executeQuery();

				Article art = null;
				while (rs.next()) {
					art = new Article(rs.getInt("no_utilisateur"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_vente"),
							rs.getString("pseudo"));
					art.setNoArticle(rs.getInt("noArticle"));
					list.add(art);
				}
				prstms.close();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_FAIL);
			throw businessException;
		}

		return list;
	}

	@Override
	public List<Article> selectByCategorie(int noCategorie) throws BusinessException {
		List<Article> list = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Mise à jour article
				PreparedStatement prstms = cnx.prepareStatement(SELECT_BY_CATEGORIE);
				prstms.setInt(1, noCategorie);
				ResultSet rs = prstms.executeQuery();

				Article art = null;
				while (rs.next()) {
					art = new Article(rs.getInt("no_utilisateur"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_vente"),
							rs.getString("pseudo"));
					art.setNoArticle(rs.getInt("noArticle"));
					list.add(art);
				}
				prstms.close();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_FAIL);
			throw businessException;
		}

		return list;
	}

	@Override
	public Article selectByNoArticle(int noArticle) throws BusinessException {
		Article articleCourant = new Article();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				// Mise à jour article
				PreparedStatement prstms = cnx.prepareStatement(SELECT_BY_ARTICLE);
				prstms.setInt(1, noArticle);
				ResultSet rs = prstms.executeQuery();

				while (rs.next()) {
					articleCourant = new Article(rs.getInt("noUser"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("montant_enchere"),
							rs.getString("pseudo"));
					articleCourant.setMiseAPrix(rs.getInt("prix_initial"));
					articleCourant.setPrixVente(rs.getInt("montant_enchere"));
					articleCourant.setNoArticle(noArticle);
					articleCourant.setLieuRetrait(
							rs.getString("rue") + " " + rs.getString("ville") + " " + rs.getString("cpo"));
					articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				}
				prstms.close();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_FAIL);
			throw businessException;
		}

		return articleCourant;
	}
}
