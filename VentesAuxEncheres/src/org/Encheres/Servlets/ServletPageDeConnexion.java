package org.Encheres.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletPageDeConnexion
 */
@WebServlet("/ServletPageDeConnexion")
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
<<<<<<< HEAD
			
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/PageDeConnexion.jsp");
=======

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/PageDeConnexion.jsp");
>>>>>>> refs/remotes/origin/master
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String identifiant;
		String mdp;

		try {
			identifiant = request.getParameter("identifiant");
			mdp = request.getParameter("mdp");
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur = utilisateurManager.getUtilisateur(identifiant, mdp);
			request.setAttribute("connecte", utilisateur);

		} catch (Exception e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
=======

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil");
>>>>>>> refs/remotes/origin/master
		rd.forward(request, response);
	}

}