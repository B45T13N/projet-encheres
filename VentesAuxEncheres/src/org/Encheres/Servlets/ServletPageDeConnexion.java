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
@WebServlet("/PageDeConnexion")
public class ServletPageDeConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("utilisateur") != null) {
			response.sendRedirect("/Accueil");
		} 

		
	else {			
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
		HttpSession session = request.getSession();
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
			int id = utilisateur.getNoUtilisateur();
			session.setAttribute("id", id);
			
			if (utilisateur != null) {
				response.sendRedirect(request.getContextPath() + "/Accueil");
			}
		} catch (Exception e) {
			e.printStackTrace();
			listeCodesErreur.add(30000);
		}
	}

}
