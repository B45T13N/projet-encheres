package org.Encheres.Servlets;

import java.io.IOException;
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

		String categorie;
		ArticleManager am = new ArticleManager();
		UtilisateurManager um = new UtilisateurManager();
		int noUtilisateur = 0;
		String articleRechercher = "";
		List<Article> listeAAfficher = new ArrayList<>();
		List<Article> listeTmp = new ArrayList<>();
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

				// Radio boutons et checkbox
				// 1ere checkbox Achat
				if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("1")) {
					if (request.getParameter("enCours") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectArticleIfNotEnd(art.getNoArticle(), -1);
						}
						listeAAfficher = listeTmp;
					}
					if (request.getParameter("mesEnCours") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectArticleIfNotEnd(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					if (request.getParameter("remportes") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectByNoAcquereurIfEnd(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					request.setAttribute("listeAAfficher", listeAAfficher);
				}
				// check box filtreVente
				if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("2")) {
					if (request.getParameter("venteEnCours") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectVenteIfNotEnd(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					if (request.getParameter("venteNonDebut") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectVenteIfNotBegin(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					if (request.getParameter("venteTerminee") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectByNoVendeurIfEnd(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					request.setAttribute("listeAAfficher", listeAAfficher);
				}
				request.setAttribute("listeAAfficher", listeAAfficher);
			} else {
				// Radio boutons et checkbox
				// 1ere checkbox Achat
				listeAAfficher = am.selectAllArticle();
				request.setAttribute("listeAAfficher", listeAAfficher);
				if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("1")) {
					if (request.getParameter("enCours") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectArticleIfNotEnd(art.getNoArticle(), -1);
						}
						listeAAfficher = listeTmp;
					}
					if (request.getParameter("mesEnCours") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectArticleIfNotEnd(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					if (request.getParameter("remportes") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectByNoAcquereurIfEnd(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					request.setAttribute("listeAAfficher", listeAAfficher);
				}
				// check box filtreVente
				if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("2")) {
					if (request.getParameter("venteEnCours") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectVenteIfNotEnd(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					if (request.getParameter("venteNonDebut") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectVenteIfNotBegin(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					if (request.getParameter("venteTerminee") != null) {
						for (Article art : listeAAfficher) {
							listeTmp = am.selectByNoVendeurIfEnd(art.getNoArticle(), noUtilisateur);
						}
						listeAAfficher = listeTmp;
					}
					request.setAttribute("listeAAfficher", listeAAfficher);
				}
				request.setAttribute("listeAAfficher", listeAAfficher);
			}
		} catch (Exception e) {
			e.printStackTrace();
			listeCodesErreur.add(3010);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

}