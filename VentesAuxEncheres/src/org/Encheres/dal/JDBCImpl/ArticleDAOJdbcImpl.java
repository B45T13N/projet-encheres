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
import org.Encheres.dal.DAO.DAOArticle;
import org.Encheres.dal.JDBCTools.ConnectionProvider;

public class ArticleDAOJdbcImpl implements DAOArticle {

	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, "
			+ "date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)"
			+ " VALUES(?,?,?,?,?,?,?)";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	public static final String UPDATE_ARTICLE = "UPDATE SET nom_article = ?, description = ?, prix_initial=?, date_debut_encheres = ?, date_fin_encheres = ?, no_categorie =? FROM ARTICLES_VENDUS WHERE no_article = ?";
	public static final String SELECT_ALL = "SELECT nom_article, prix_initial, date_fin_encheres, pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON";

	@Override
	public void insert(Article data) throws BusinessException {
		if (data == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
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

				prstms.setInt(6, data.getNoUtilisateur());
				// Récupération du libelle categorie
				CategorieDAOJdbcImpl categorie = new CategorieDAOJdbcImpl();
				String libelle = data.getlibelle().toLowerCase();
				int noCategorie = categorie.selectByLibelle(libelle);
				prstms.setInt(7, noCategorie);
				prstms.executeUpdate();
				ResultSet rs = prstms.getGeneratedKeys();
				if (rs.next()) {
					data.setNoArticle(rs.getInt(1));
				}
				rs.close();
				prstms.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10001);
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
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10001);
			throw businessException;
		}
	}

	@Override
	public void update(Article data) throws BusinessException {
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
					CategorieDAOJdbcImpl categorie = new CategorieDAOJdbcImpl();
					String libelle = data.getlibelle().toLowerCase();
					int noCategorie = categorie.selectByLibelle(libelle);
					prstms.setInt(6, noCategorie);

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
			businessException.ajouterErreur(10013);
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
				if (LocalDate.now() != data.getDateDebutEncheres()) {
					prstms.setString(1, data.getNomArticle());
					prstms.setString(2, data.getDescription());
					prstms.setInt(3, data.getMiseAPrix());
					prstms.setDate(4, Date.valueOf(data.getDateDebutEncheres()));
					prstms.setDate(5, Date.valueOf(data.getDateFinEncheres()));
					// Récupération du libelle categorie
					CategorieDAOJdbcImpl categorie = new CategorieDAOJdbcImpl();
					String libelle = data.getlibelle().toLowerCase();
					int noCategorie = categorie.selectByLibelle(libelle);
					prstms.setInt(6, noCategorie);

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
			businessException.ajouterErreur(10013);
			throw businessException;
		}

		return list;
	}

	@Override
	public List<Article> selectByCategorie(int noCategorie) throws BusinessException {
		return null;
	}

}
