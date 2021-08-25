package org.Encheres.Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.Encheres.BusinessException;
import org.Encheres.bll.ArticleManager;
import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Article;
import org.Encheres.dal.CodesResultatDAL;
import org.Encheres.dal.JDBCTools.ConnectionProvider;

@WebServlet("/Accueil")
public class ServletAccueil<BeanFiltreRecherche> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAccueil() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleManager am = new ArticleManager();
		UtilisateurManager um = new UtilisateurManager();
		int noUtilisateur;
		int noArticle;
		HttpSession sessionScope = request.getSession();
		sessionScope.setMaxInactiveInterval(300);
		List<Article> listeAAfficher = new ArrayList<>();
		request.setAttribute("sessionScope", sessionScope);
		sessionScope.setAttribute("noArticle", request.getAttribute("noArticle"));
		try {
			// noUtilisateur = (int) sessionScope.getAttribute("id");
			listeAAfficher = am.selectAllArticle();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("listeAAfficher", listeAAfficher);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS WHERE date_fin_encheres > GETDATE() ORDER BY date_fin_encheres DESC";
		final String SELECT_ENCHERES_USER = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =18 AND date_fin_encheres > GETDATE() ORDER BY date_fin_encheres DESC";
		final String SELECT_ENCHERES_USER_CLAIMED = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =18 AND date_fin_encheres < GETDATE() ORDER BY date_fin_encheres DESC";
		final String SELECT_SALES_IN_PROGRESS = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =? AND date_fin_encheres > GETDATE() ORDER BY date_fin_encheres DESC";
		final String SELECT_SALES_NOT_STARTED = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =? AND date_debut_encheres < GETDATE() ORDER BY date_debut_encheres DESC";
		final String SELECT_SALES_SOLD = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur =? AND date_fin_encheres < GETDATE() ORDER BY date_fin_encheres DESC";
		List<Article> list = new ArrayList<Article>();

		String categorie;
		ArticleManager am = new ArticleManager();
		UtilisateurManager um = new UtilisateurManager();
		int noUtilisateur;
		String articleRechercher = "";
		List<Article> listeAAfficher = new ArrayList<>();
		List<Integer> listeCodesErreur = new ArrayList<>();

		HttpSession sessionScope = request.getSession(); // Init de sessionScope
		sessionScope.setMaxInactiveInterval(300);
		// Verification si utilisateur connecté
		if (sessionScope.getAttribute("id") != null) {
			noUtilisateur = (int) sessionScope.getAttribute("id");
		}

		// Traitement choix catégories
		try {
			if (!request.getParameter("categorie").equals("")) {
				categorie = request.getParameter("categorie");
				listeAAfficher = am.selectArticleByCategorie(categorie);
			} else {
				listeAAfficher = am.selectAllArticle();
			}

			// Radio boutons et checkbox
			// 1ere checkbox Achat
			if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("1")) {

				if (request.getParameter("enCours") != null) {

					try (Connection cnx = ConnectionProvider.getConnection()) {
						try {
							cnx.setAutoCommit(false);
							// Mise à jour article
							PreparedStatement prstms = cnx.prepareStatement(SELECT_ALL_ARTICLES);
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
				}
				if (request.getParameter("mesEnCours") != null) {
					try (Connection cnx = ConnectionProvider.getConnection()) {
						try {
							cnx.setAutoCommit(false);
							// Mise à jour article
							PreparedStatement prstms = cnx.prepareStatement(SELECT_ENCHERES_USER);
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

				}
				if (request.getParameter("remportes") != null) {
					try (Connection cnx = ConnectionProvider.getConnection()) {
						try {
							cnx.setAutoCommit(false);
							// Mise à jour article
							PreparedStatement prstms = cnx.prepareStatement(SELECT_ENCHERES_USER_CLAIMED);
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
				}
			}
			// check box filtreVente
			if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("2"))

			{

				if (request.getParameter("venteEnCours") != null) {
					try (Connection cnx = ConnectionProvider.getConnection()) {
						try {
							cnx.setAutoCommit(false);
							// Mise à jour article
							PreparedStatement prstms = cnx.prepareStatement(SELECT_SALES_IN_PROGRESS);
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

				}
				if (request.getParameter("venteNonDebut") != null) {
					try (Connection cnx = ConnectionProvider.getConnection()) {
						try {
							cnx.setAutoCommit(false);
							// Mise à jour article
							PreparedStatement prstms = cnx.prepareStatement(SELECT_SALES_NOT_STARTED);
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

				}
				if (request.getParameter("venteTerminee") != null) {
					try (Connection cnx = ConnectionProvider.getConnection()) {
						try {
							cnx.setAutoCommit(false);
							// Mise à jour article
							PreparedStatement prstms = cnx.prepareStatement(SELECT_SALES_SOLD);
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

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			listeCodesErreur.add(3010);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

}
