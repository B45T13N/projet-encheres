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
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		Article currentArticle = (Article) session.getAttribute("article");
		EnchereManager em = new EnchereManager();
		UtilisateurManager um = new UtilisateurManager();
		Utilisateur seller = new Utilisateur();

		try {
			seller = um.selectByNoUtilisateur(1);
			em.selectAcheteur(9);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			listeCodesErreur.add(30000);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp");
		rd.forward(request, response);
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
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		Article currentArticle = (Article) session.getAttribute("article");

		EnchereManager em = new EnchereManager();

		prixEnchere = currentArticle.getPrixVente();

		try {
			em.updateEnchere(currentArticle, user, prixEnchere);
		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(30000);
		}

	}

}
