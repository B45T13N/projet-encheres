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
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// HttpSession currentUser = request.getSession();

		String pseudo;
		String nom;
		String prenom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String motDePasse;
		// int noUtilisateur;

		try {
			pseudo = request.getParameter("pseudo");
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			email = request.getParameter("email");
			telephone = request.getParameter("telephone");
			rue = request.getParameter("rue");
			codePostal = request.getParameter("codepostal");
			ville = request.getParameter("ville");
			motDePasse = request.getParameter("motDePasse");

			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur = new Utilisateur();
			utilisateur = utilisateurManager.updateUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal,
					ville, motDePasse);
			request.setAttribute("updated", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
