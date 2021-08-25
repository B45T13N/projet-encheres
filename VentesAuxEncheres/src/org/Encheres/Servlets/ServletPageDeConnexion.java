package org.Encheres.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

		Cookie[] cookies = request.getCookies();

		if (cookies.length > 1) {
			request.setAttribute("pseudo", cookies[0].getValue());
			request.setAttribute("mdp", cookies[1].getValue());
		}
		if (request.getParameter("utilisateur") != null) {
			response.sendRedirect("/Accueil");
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
		HttpSession session = request.getSession();
		request.setAttribute("errMdp", "Veuillez ressaisir votre mot de passe");
		session.setMaxInactiveInterval(300);
		String identifiant;
		String mdp;
		UtilisateurManager utilisateurManager;
		Utilisateur utilisateur;
		RequestDispatcher rd;
		int id = 0;
		Cookie cookies[] = request.getCookies();

		try {

			identifiant = request.getParameter("pseudo");
			mdp = request.getParameter("mdp");
			utilisateurManager = new UtilisateurManager();
			utilisateur = utilisateurManager.getUtilisateur(identifiant, mdp);
			id = utilisateur.getNoUtilisateur();
			session.setAttribute("id", id);

			if (cookies.length >= 1) {

				if (request.getParameter("save") != null) {
					Cookie cookiePseudo = new Cookie("pseudo", identifiant);
					Cookie cookieMDP = new Cookie("MDP", mdp);
					response.addCookie(cookiePseudo);
					response.addCookie(cookieMDP);
				} else {
					cookies[0].setMaxAge(0);
					cookies[1].setMaxAge(0);
					response.addCookie(cookies[0]);
					response.addCookie(cookies[1]);
				}

			}

// suprimer les cookies : 

//			for(int i =0 ; i < cookies.length; i++) {
//				cookies[i].setMaxAge(0);
//				response.addCookie(cookies[i]);
//			}

//			for(int i = 0; i < cookies.length; i++) {
//		Cookie cookiePseudo = new Cookie("pseudo", identifiant);
//		Cookie cookieMDP = new Cookie("MDP", mdp);
//		response.addCookie(cookiePseudo);
//		response.addCookie(cookieMDP);
//			}

			if (id > 0) {
				response.sendRedirect(request.getContextPath() + "/Accueil");
			} else {
				rd = request.getRequestDispatcher("/WEB-INF/JSP/PageDeConnexion.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			listeCodesErreur.add(30000);
		}
	}
}
