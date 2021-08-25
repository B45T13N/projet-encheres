package org.Encheres.Servlets;

import java.io.IOException;

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

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(300);
		request.setCharacterEncoding("UTF-8");
		String pseudo;
		String nom;
		String prenom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String oldPass;
		String newPass;
		String confirmPass;
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		int noUtilisateur = (int) session.getAttribute("id");

		pseudo = request.getParameter("pseudo");
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		rue = request.getParameter("rue");
		codePostal = request.getParameter("code_postal");
		ville = request.getParameter("ville");
		oldPass = request.getParameter("motDePasse");
		newPass = request.getParameter("newMotDePasse");
		confirmPass = request.getParameter("confirmMotDePasse");

		/*
		 * if submit = register executeUpdate else if submit = delete executeDelete
		 */
		try {

			Utilisateur updatedUser = new Utilisateur();
			if (newPass.equals(confirmPass) && (!confirmPass.equals("") || !newPass.contentEquals(""))) {
				updatedUser = utilisateurManager.updateUtilisateur(pseudo, nom, prenom, email, telephone, rue,
						codePostal, ville, newPass, noUtilisateur);
			} else if (newPass.equals("") || newPass == null) {
				updatedUser = utilisateurManager.updateUtilisateur(pseudo, nom, prenom, email, telephone, rue,
						codePostal, ville, oldPass, noUtilisateur);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/ModifierProfil");
				rd.forward(request, response);
			}

			response.sendRedirect(request.getContextPath() + "/Accueil");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
