package org.Encheres.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.Encheres.BusinessException;
import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletMonProfil
 */
@WebServlet("/MonProfil")
public class ServletMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMonProfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager user = new UtilisateurManager();
		Utilisateur selectedUser = new Utilisateur();
		HttpSession session = request.getSession();

		int currentUser = (int) session.getAttribute("id");
		try {
			selectedUser = user.selectByNoUtilisateur(currentUser);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("pseudo", selectedUser.getPseudo());
		request.setAttribute("nom", selectedUser.getNom());
		request.setAttribute("prenom", selectedUser.getPrenom());
		request.setAttribute("email", selectedUser.getEmail());
		request.setAttribute("telephone", selectedUser.getTelephone());
		request.setAttribute("rue", selectedUser.getRue());
		request.setAttribute("code_postal", selectedUser.getCodePostal());
		request.setAttribute("ville", selectedUser.getVille());

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/MonProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
