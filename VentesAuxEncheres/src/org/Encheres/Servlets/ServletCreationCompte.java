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
 * Servlet implementation class ServletCreationCompte
 */
@WebServlet("/CreationCompte")
public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("connecte") != null) {
			response.sendRedirect("/Accueil");

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/CreationCompte.jsp");
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

		String pseudo;
		String nom;
		String prenom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String mdp;
		String confirmationMdp;
		String erreur = "Veuillez saisir un mot de passe identique";
		String pseudoExistant;
		String emailExistant;
		List<Integer> listeCodesErreur = new ArrayList<>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = new Utilisateur();
		RequestDispatcher rd;
		HttpSession session = request.getSession();
		try {
			pseudo = request.getParameter("pseudo");
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			email = request.getParameter("email");
			telephone = request.getParameter("telephone");
			rue = request.getParameter("rue");
			codePostal = request.getParameter("codepostal");
			ville = request.getParameter("ville");
			mdp = request.getParameter("mdp");
			confirmationMdp = request.getParameter("confirmationmdp");

// 			à faire : gestion d'erreur > pseudo ou email deja existant.
//			|| pseudo != pseudoExistant || email != emailExistant

			if (mdp.equals(confirmationMdp)) {
				utilisateur = utilisateurManager.addUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal,
						ville, mdp);
				response.sendRedirect(request.getContextPath() + "/PageDeConnexion");
			} else {
// modifier en liste code erreur 
//request.setAttribute("erreurMDP", erreur);
				listeCodesErreur.add(30031);
				rd = request.getRequestDispatcher("/WEB-INF/JSP/CreationCompte.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			listeCodesErreur.add(30000);
		}

	}

}
