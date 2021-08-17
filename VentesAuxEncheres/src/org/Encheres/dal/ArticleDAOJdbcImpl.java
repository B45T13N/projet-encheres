package org.Encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;

public class ArticleDAOJdbcImpl implements DAOArticle {

	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, "
			+ "date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)"
			+ " VALUES(?,?,?,?,?,?,?)";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";

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
				if (data.getNomArticle().length() < 30 && data.getDescription().length() < 300) {
					prstms.setString(1, data.getNomArticle());
					prstms.setString(2, data.getDescription());
				} else {
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(10002);
					throw businessException;
				}
				if (data.getDateDebutEncheres().isBefore(LocalDate.now()) || data.getDateDebutEncheres() == null
						|| data.getDateFinEncheres().isBefore(data.getDateDebutEncheres())
						|| data.getDateFinEncheres() == null
						|| data.getDateFinEncheres().equals(data.getDateDebutEncheres())) {
					prstms.setDate(3, Date.valueOf(data.getDateDebutEncheres()));
					prstms.setDate(4, Date.valueOf(data.getDateFinEncheres()));
				} else {
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(10003);
					throw businessException;
				}

				if (data.getMiseAPrix() < 0) {
					prstms.setInt(5, data.getMiseAPrix());
				} else {
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(10004);
					throw businessException;
				}
				prstms.setInt(6, data.getNoUtilisateur());
				prstms.setInt(7, data.getNoCategorie());
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
	public void delete(int idArticle) throws BusinessException {
		if (idArticle < 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'un article
				PreparedStatement prstms = cnx.prepareStatement(DELETE_ARTICLE);
				prstms.setInt(1, idArticle);
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
	public void update(int idArticle) throws BusinessException {

	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		return null;
	}

	@Override
	public List<Article> selectByCategorie(int noCategorie) throws BusinessException {
		return null;
	}

}
