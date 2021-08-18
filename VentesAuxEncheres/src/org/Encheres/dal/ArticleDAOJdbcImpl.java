package org.Encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Enchere;
import org.Encheres.bo.Retrait;
import org.Encheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements DAOArticle {

	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, "
			+ "date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)"
			+ " VALUES(?,?,?,?,?,?,?)";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	public static final String UPDATE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";

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
				prstms.setInt(7, categorie.selectByLibelle(data.getLibelleCategorie()));
				prstms.executeUpdate();
				ResultSet rs = prstms.getGeneratedKeys();
				if (rs.next()) {
					data.setNoArticle(rs.getInt(1));
				}
				rs.close();
				prstms.close();

				// Insert Enchere
				EnchereDAOJdbcImpl enchereDAO = new EnchereDAOJdbcImpl();
				Enchere enchereCourant = new Enchere(data.getNoUtilisateur(), data.getNoArticle(),
						data.getDateDebutEncheres(), data.getMiseAPrix());
				enchereDAO.insert(enchereCourant);
				// Insert retrait
				RetraitDAOJdbcImpl retraitDAO = new RetraitDAOJdbcImpl();
				UtilisateurDAOJdbcImpl utilisateurDAO = new UtilisateurDAOJdbcImpl();
				Utilisateur utilisateurCourant = utilisateurDAO.selectByNoUtilisateur(data.getNoUtilisateur());
				Retrait retraitCourant = new Retrait(data.getNoArticle(), utilisateurCourant.getRue(),
						utilisateurCourant.getCodePostal(), utilisateurCourant.getVille());
				retraitDAO.insert(retraitCourant);

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
	public void update(int noArticle) throws BusinessException {

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
