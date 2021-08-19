package org.Encheres.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
			
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur = utilisateurManager.addUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
			if(mdp.equals(confirmationMdp)) {
			request.setAttribute("connecte", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			rd.forward(request, response);
			}else {
				request.setAttribute("erreurMdp", utilisateur);
			}
		
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		
	}

}
