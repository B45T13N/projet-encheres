package org.Encheres.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Encheres.BusinessException;
import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEmailConfirmer
 */
@WebServlet("/ReinitialiserPassword")
public class ServletReinitialiserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletReinitialiserPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ReinitialiserPassword.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur updatePassword = new Utilisateur();
		Utilisateur verifiedEmail = new Utilisateur();

		String newPassword = request.getParameter("newPassword");
		request.setAttribute("newPassword", newPassword);
		String confirmPassword = request.getParameter("confirmPassword");
		request.setAttribute("confirmPassword", confirmPassword);
		String email = request.getParameter("email");
		request.setAttribute("email", email);

		try {

			verifiedEmail = utilisateurManager.selectUtilisateurByEmail(email);
			verifiedEmail.getEmail();

			if (verifiedEmail.getEmail() == null || newPassword.compareTo(confirmPassword) != 0) {
				request.setAttribute("erreurEmail", "Email");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ReinitialiserPassword.jsp");
				rd.forward(request, response);
				System.out.println("Email ou mot de passe invalide");
			} else if (verifiedEmail.getEmail().equals(email) || newPassword.equals(confirmPassword)) {
				updatePassword = utilisateurManager.updatePasswordByEmail(newPassword, email);
				System.out.println("Mot de passe changé");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ReinitialiserPassword.jsp");
				rd.forward(request, response);
			}

		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

}
