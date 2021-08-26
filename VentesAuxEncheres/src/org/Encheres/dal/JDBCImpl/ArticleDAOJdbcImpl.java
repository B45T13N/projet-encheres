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
			+ "date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_photo)"
			+ " VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	public static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, prix_initial=?, date_debut_encheres = ?, "
			+ "date_fin_encheres = ?, no_categorie =?, prix_vente = ?, url_photo = ?  WHERE no_article = ?";
	public static final String SELECT_ALL = "SELECT u.no_utilisateur, nom_article, description, url_photo, c.libelle as libelle, prix_vente, date_fin_encheres, pseudo, a.no_categorie, a.no_article as noArticle "
			+ "FROM ARTICLES_VENDUS a " + "INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE (date_debut_encheres < GETDATE() OR date_debut_encheres = GETDATE())";
	public static final String SELECT_ALL_WHERE = "SELECT u.no_utilisateur, nom_article, description, url_photo, c.libelle as libelle, prix_vente, date_fin_encheres, pseudo, a.no_categorie, a.no_article as noArticle "
			+ "FROM ARTICLES_VENDUS a " + "INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE (date_debut_encheres < GETDATE() OR date_debut_encheres = GETDATE()) AND nom_article LIKE ?";
	public static final String SELECT_BY_CATEGORIE = "SELECT u.no_utilisateur, nom_article, description, url_photo , c.libelle as libelle, prix_vente, date_fin_encheres, pseudo, a.no_categorie, a.no_article as noArticle, "
			+ "pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE c.no_categorie=? AND (date_debut_encheres < GETDATE() OR date_debut_encheres = GETDATE())";
	public static final String SELECT_BY_ARTICLE = "SELECT u.no_utilisateur as noUser, description, nom_article, prix_initial, montant_enchere, date_debut_encheres, date_fin_encheres, c.libelle as libelle, "
			+ "u.pseudo, a.no_article, r.rue as rue, r.code_postal as cpo, r.ville as ville, url_photo "
			+ "FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN ENCHERES e ON e.no_article = a.no_article "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie "
			+ "INNER JOIN RETRAITS r ON r.no_article = a.no_article" + " WHERE a.no_article=?";
	public static final String ADDON_SELECT_ENCHERES_EN_COURS = " date_debut_encheres < GETDATE() OR date_debut_encheres = GETDATE()";
	public static final String ADDON_SELECT_VENTE_EN_COURS = " (date_debut_encheres < GETDATE() OR date_debut_encheres = GETDATE())";
	public static final String ADDON_SELECT_VENTE_NON_DEBUT = " date_debut_encheres > GETDATE() ";
	public static final String ADDON_SELECT_ARTICLES_REMPORTES = " date_fin_encheres < GETDATE() ";
	public static final String ADDON_SELECT_MES_VENTES = " u.no_utilisateur = ? ";
	public static final String ADDON_SELECT_MES_ENCHERES = " e.no_utilisateur = ? ";
	public static final String ADDON_SELECT_ARTICLES = " a.no_article = ? ";

	public static final String SELECT_ENCHERES = "SELECT u.no_utilisateur as noUser, nom_article, url_photo, description, c.libelle as libelle, prix_vente, prix_initial, "
			+ "montant_enchere, date_fin_encheres, pseudo, a.no_categorie, a.no_article as noArticle,"
			+ " a.no_article, r.rue as rue, r.code_postal as cpo, r.ville as ville, date_debut_encheres "
			+ " FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN ENCHERES e ON e.no_article = a.no_article "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie "
			+ "INNER JOIN RETRAITS r ON r.no_article = a.no_article ";
	public static final String DELETE_ARTICLE_BY_NO_USER = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	public static final String SELECT_ALL_BY_NO_USER = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";

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

				prstms.setString(1, data.getNomArticle().toLowerCase());
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
				prstms.setString(9, data.getUrlPhoto());
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
					prstms.setString(8, data.getUrlPhoto());
					prstms.setInt(9, data.getNoArticle());

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
					art.setUrlPhoto(rs.getString("url_photo"));
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
					art.setUrlPhoto(rs.getString("url_photo"));
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
					articleCourant.setUrlPhoto(rs.getString("url_photo"));
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

	@Override
	public List<Article> selectArticleIfNotEnd(int noArticle, int noUser) throws BusinessException {
		Article articleCourant = new Article();
		List<Article> list = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				// Mise à jour article
				PreparedStatement prstms;
				if (noUser == -1) {
					prstms = cnx.prepareStatement(SELECT_ENCHERES + " WHERE " + ADDON_SELECT_ENCHERES_EN_COURS + " AND "
							+ ADDON_SELECT_ARTICLES);
					prstms.setInt(1, noArticle);
				} else {
					prstms = cnx.prepareStatement(SELECT_ENCHERES + " WHERE (" + ADDON_SELECT_ENCHERES_EN_COURS
							+ " AND " + ADDON_SELECT_ARTICLES + ") AND " + ADDON_SELECT_MES_ENCHERES);
					prstms.setInt(1, noArticle);
					prstms.setInt(2, noUser);
				}
				ResultSet rs = prstms.executeQuery();
				while (rs.next()) {
					articleCourant = new Article(rs.getInt("noUser"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("montant_enchere"),
							rs.getString("pseudo"));
					articleCourant.setMiseAPrix(rs.getInt("prix_initial"));
					articleCourant.setPrixVente(rs.getInt("montant_enchere"));
					articleCourant.setLieuRetrait(
							rs.getString("rue") + " " + rs.getString("ville") + " " + rs.getString("cpo"));
					articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleCourant.setNoArticle(rs.getInt("noArticle"));
					articleCourant.setUrlPhoto(rs.getString("url_photo"));
					list.add(articleCourant);
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
	public List<Article> selectByNoAcquereurIfEnd(int noArticle, int noUser) throws BusinessException {
		Article articleCourant = new Article();
		List<Article> list = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				// Mise à jour article
				PreparedStatement prstms = cnx.prepareStatement(SELECT_ENCHERES + " WHERE "
						+ ADDON_SELECT_ARTICLES_REMPORTES + " AND " + ADDON_SELECT_MES_VENTES);
				prstms.setInt(1, noUser);
				ResultSet rs = prstms.executeQuery();
				while (rs.next()) {
					articleCourant = new Article(rs.getInt("noUser"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("montant_enchere"),
							rs.getString("pseudo"));
					articleCourant.setMiseAPrix(rs.getInt("prix_initial"));
					articleCourant.setPrixVente(rs.getInt("montant_enchere"));
					articleCourant.setNoArticle(rs.getInt("noArticle"));
					articleCourant.setLieuRetrait(
							rs.getString("rue") + " " + rs.getString("ville") + " " + rs.getString("cpo"));
					articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleCourant.setUrlPhoto(rs.getString("url_photo"));
					list.add(articleCourant);
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
	public List<Article> selectVenteIfNotEnd(int noArticle, int noUser) throws BusinessException {
		Article articleCourant = new Article();
		List<Article> list = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				// Mise à jour article
				PreparedStatement prstms;
				prstms = cnx.prepareStatement(
						SELECT_ENCHERES + " WHERE " + ADDON_SELECT_MES_VENTES + " AND " + ADDON_SELECT_VENTE_EN_COURS);
				prstms.setInt(1, noUser);
//				prstms.setInt(2, noArticle);
				ResultSet rs = prstms.executeQuery();
				while (rs.next()) {
					articleCourant = new Article(rs.getInt("noUser"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("montant_enchere"),
							rs.getString("pseudo"));
					articleCourant.setMiseAPrix(rs.getInt("prix_initial"));
					articleCourant.setPrixVente(rs.getInt("montant_enchere"));
					articleCourant.setNoArticle(rs.getInt("noArticle"));
					articleCourant.setLieuRetrait(
							rs.getString("rue") + " " + rs.getString("ville") + " " + rs.getString("cpo"));
					articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleCourant.setUrlPhoto(rs.getString("url_photo"));
					list.add(articleCourant);
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
	public List<Article> selectVenteIfNotBegin(int noArticle, int noUser) throws BusinessException {
		Article articleCourant = new Article();
		List<Article> list = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				// Mise à jour article
				PreparedStatement prstms;
				prstms = cnx.prepareStatement(
						SELECT_ENCHERES + " WHERE " + ADDON_SELECT_VENTE_NON_DEBUT + " AND " + ADDON_SELECT_MES_VENTES);
				prstms.setInt(1, noUser);
//				prstms.setInt(2, noArticle);
				ResultSet rs = prstms.executeQuery();
				while (rs.next()) {
					articleCourant = new Article(rs.getInt("noUser"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("montant_enchere"),
							rs.getString("pseudo"));
					articleCourant.setMiseAPrix(rs.getInt("prix_initial"));
					articleCourant.setPrixVente(rs.getInt("montant_enchere"));
					articleCourant.setNoArticle(rs.getInt("noArticle"));
					articleCourant.setLieuRetrait(
							rs.getString("rue") + " " + rs.getString("ville") + " " + rs.getString("cpo"));
					articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleCourant.setUrlPhoto(rs.getString("url_photo"));
					list.add(articleCourant);
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
	public List<Article> selectByNoVendeurIfEnd(int noArticle, int noUser) throws BusinessException {
		Article articleCourant = new Article();
		List<Article> list = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				// Mise à jour article
				PreparedStatement prstms = cnx.prepareStatement(SELECT_ENCHERES + " WHERE "
						+ ADDON_SELECT_ARTICLES_REMPORTES + " AND " + ADDON_SELECT_MES_VENTES);
				prstms.setInt(1, noUser);
				ResultSet rs = prstms.executeQuery();
				while (rs.next()) {
					articleCourant = new Article(rs.getInt("noUser"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("montant_enchere"),
							rs.getString("pseudo"));
					articleCourant.setMiseAPrix(rs.getInt("prix_initial"));
					articleCourant.setPrixVente(rs.getInt("montant_enchere"));
					articleCourant.setNoArticle(rs.getInt("noArticle"));
					articleCourant.setLieuRetrait(
							rs.getString("rue") + " " + rs.getString("ville") + " " + rs.getString("cpo"));
					articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleCourant.setUrlPhoto(rs.getString("url_photo"));
					list.add(articleCourant);
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
	public List<Article> selectArticleByNoUser(int noUser) throws BusinessException {
		List<Article> list = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Mise à jour article
				PreparedStatement prstms = cnx.prepareStatement(SELECT_ALL_BY_NO_USER);
				prstms.setInt(1, noUser);
				ResultSet rs = prstms.executeQuery();

				Article art = null;
				while (rs.next()) {
					art = new Article();
					art.setNoUtilisateur(rs.getInt("no_utilisateur"));
					art.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					art.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					art.setDescription(rs.getString("description"));
					art.setNomArticle(rs.getString("nom_article"));
					art.setPrixVente(rs.getInt("prix_vente"));
					art.setNoCategorie(rs.getInt("no_categorie"));
					art.setNoArticle(rs.getInt("no_article"));
					art.setUrlPhoto(rs.getString("url_photo"));
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
	public void deleteArticleByNoUser(int noUser) throws BusinessException {
		if (noUser < 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'un article
				PreparedStatement prstms = cnx.prepareStatement(DELETE_ARTICLE_BY_NO_USER);
				prstms.setInt(1, noUser);
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
	public List<Article> selectArticleWhere(String contient) throws BusinessException {
		List<Article> list = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Mise à jour article
				PreparedStatement prstms = cnx.prepareStatement(SELECT_ALL_WHERE);
				prstms.setString(1, "%" + contient.toLowerCase() + "%");
				ResultSet rs = prstms.executeQuery();

				Article art = null;
				while (rs.next()) {
					art = new Article(rs.getInt("no_utilisateur"), rs.getString("nom_article"),
							rs.getString("description"), rs.getString("libelle"),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_vente"),
							rs.getString("pseudo"));
					art.setNoArticle(rs.getInt("noArticle"));
					art.setUrlPhoto(rs.getString("url_photo"));
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

}