package org.Encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Categorie;

public class ArticleDAOJdbcImpl implements DAOArticle {

	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, "
			+ "date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)"
			+ " VALUES(?,?,?,?,?,?,?)";

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
					prstms.setDate(3, Date.valueOf(data.getDateDebutEncheres()));
					prstms.setDate(4, Date.valueOf(data.getDateFinEncheres()));
					if (data.getMiseAPrix() < 0) {
						prstms.setInt(5, data.getMiseAPrix());
					} else {
						BusinessException businessException = new BusinessException();
						businessException.ajouterErreur(10003);
						throw businessException;
					}
					prstms.setInt(6, data.getNoUtilisateur());
					prstms.setInt(7, data.getNoCategorie());
				} else {
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(10003);
					throw businessException;
				}

			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10001);
			throw businessException;
		}
	}

	@Override
	public void delete(int idArticle) throws BusinessException {

	}

	@Override
	public void update(int idArticle) throws BusinessException {

	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		return null;
	}

	@Override
	public List<Article> selectByCategorie(Categorie categorie) throws BusinessException {
		return null;
	}

}
