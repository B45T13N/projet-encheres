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
 * Servlet implementation class ServletCreationCompte
 */
@WebServlet("/ServletCreationCompte")
public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("connecte") != null) {
			response.sendRedirect("/ServletAccueil");
			
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/CreationCompte.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = new Utilisateur();
		HttpSession session = request.getSession();
		try
		{
			pseudo = request.getParameter("pseudo");
			nom = request.getParameter("mdp");
			prenom = request.getParameter("prenom");
			email = request.getParameter("email");
			telephone = request.getParameter("telephone");
			rue = request.getParameter("rue");
			codePostal = request.getParameter("codepostal");
			ville = request.getParameter("ville");
			mdp = request.getParameter("mdp");
			confirmationMdp = request.getParameter("confirmationmdp");
			
// à faire : gestion d'erreur > pseudo ou email deja existant.
			
			
			if(mdp.equals(confirmationMdp)) {
			session = request.getSession(true);
			request.setAttribute("connecte", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			rd.forward(request, response);
			}else {
			request.setAttribute("erreurMDP", erreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/CreationCompte.jsp");
			rd.forward(request, response);
			}
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		
	}

}
