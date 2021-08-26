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
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");

		try {

			verifiedEmail = utilisateurManager.selectUtilisateurByEmail(email);

			if (verifiedEmail == null && newPassword != confirmPassword) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ReinitialiserPassword.jsp");
				rd.forward(request, response);
			} else if (verifiedEmail.equals(email) && newPassword.equals(confirmPassword)) {
				updatePassword = utilisateurManager.updatePasswordByEmail(newPassword, email);
				response.sendRedirect(request.getContextPath() + "/PageDeConnexion");
			}

		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

}
