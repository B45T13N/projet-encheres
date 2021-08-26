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
 * Servlet implementation class ServletOublieMotDePasse
 */
@WebServlet("/OublieMotDePasse")
public class ServletOublieMotDePasse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/OublieMotDePasse.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		UtilisateurManager user = new UtilisateurManager();
		Utilisateur getEmail = new Utilisateur();

		String email = request.getParameter("email");
		request.setAttribute("email", email);

		try {
			getEmail = user.selectUtilisateurByEmail(email);
			getEmail.getEmail();

			if (getEmail.getEmail() == null) {
				request.setAttribute("erreurEmail", "Email invalide !");
				System.out.println("Email Invalide");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/OublieMotDePasse.jsp");
				rd.forward(request, response);
			} else if (getEmail.getEmail().equals(email)) {
				System.out.println("Email Valide!");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/OublieMotDePasse.jsp");
				rd.forward(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
