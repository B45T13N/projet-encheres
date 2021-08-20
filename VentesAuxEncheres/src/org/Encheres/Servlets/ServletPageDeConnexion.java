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

import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletPageDeConnexion
 */
@WebServlet("/page_de_connexion")
public class ServletPageDeConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("connecte") != null) {
			response.sendRedirect("/ServletAccueil");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/PageDeConnexion.jsp");
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
		request.setCharacterEncoding("UTF-8");
		HttpSession currentUser = request.getSession();
		String identifiant;
		String mdp;
		UtilisateurManager utilisateurManager;
		Utilisateur utilisateur;
		RequestDispatcher rd;
		try {
			identifiant = request.getParameter("identifiant");
			mdp = request.getParameter("mdp");
			utilisateurManager = new UtilisateurManager();
			utilisateur = utilisateurManager.getUtilisateur(identifiant, mdp);
			if (utilisateur != null) {
				currentUser.setAttribute("connecte", utilisateur);
				rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
				rd.forward(request, response);
			} else {
				rd = request.getRequestDispatcher("/WEB-INF/JSP/PageDeConnexion.jsp");
				rd.forward(request, response);
				listeCodesErreur.add(30000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
