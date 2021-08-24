package org.Encheres.Servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import org.Encheres.bll.EnchereManager;
import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Article;
import org.Encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/DetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Integer> listeCodesErreur = new ArrayList<>();
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("id");

		request.setAttribute("session", session);
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		session.setAttribute("noArticle", noArticle);
		Article currentArticle = new Article();
		EnchereManager em = new EnchereManager();
		ArticleManager am = new ArticleManager();
		UtilisateurManager um = new UtilisateurManager();
		Utilisateur seller = new Utilisateur();
		LocalDate date;
		try {
			currentArticle = am.selectArticleByNoArticle(noArticle);
			request.setAttribute("currentArticle", currentArticle);
			seller = um.selectByNoUtilisateur(currentArticle.getNoUtilisateur());
			request.setAttribute("seller", seller);
			em.selectAcheteur(idUser);

		} catch (BusinessException e1) {
			e1.printStackTrace();
			listeCodesErreur.add(30000);
		}
		if (seller.getNoUtilisateur() == idUser && currentArticle.getDateDebutEncheres().isAfter(LocalDate.now())) {
			response.sendRedirect(request.getContextPath() + "/NouvelleVente");

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Integer> listeCodesErreur = new ArrayList<>();
		int prixEnchere = 0;
		HttpSession session = request.getSession();
		int idCurrentUser = (int) session.getAttribute("id");
		request.setAttribute("session", session);
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		request.setAttribute("noArticle", noArticle);
		Article currentArticle = new Article();
		ArticleManager am = new ArticleManager();

		EnchereManager em = new EnchereManager();
		prixEnchere = Integer.parseInt(request.getParameter("prixEnchere"));
		try {
			currentArticle = am.selectArticleByNoArticle(noArticle);
			request.setAttribute("currentArticle", currentArticle);
			am.updateVenteArticle(currentArticle, idCurrentUser, prixEnchere);
		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(30000);
		}

		response.sendRedirect(request.getContextPath() + "/Accueil");

	}

}
