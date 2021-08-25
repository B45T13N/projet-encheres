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
					// am.setEnCours(true); //methode sur ArticleManager
				} else {
					// am.setEnCours(false); //methode sur ArticleManager
				}
				if (request.getParameter("mesEnCours") != null) {

				} else {

				}
				if (request.getParameter("remportes") != null) {

				} else {

				}
			}
			// check box filtreVente
			if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("2"))

			{

				if (request.getParameter("venteEnCours") != null) {

				} else {

				}
				if (request.getParameter("venteNonDebut") != null) {

				} else {

				}
				if (request.getParameter("venteTerminee") != null) {

				} else {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			listeCodesErreur.add(3010);
		}

//		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
//		rd.forward(request, response);
	}

}
