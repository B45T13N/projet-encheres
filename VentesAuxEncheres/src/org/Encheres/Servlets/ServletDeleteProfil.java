package org.Encheres.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.Encheres.BusinessException;
import org.Encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletDeleteProfil
 */
@WebServlet("/DeleteProfil")
public class ServletDeleteProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurManager deleteUser = new UtilisateurManager();
		HttpSession session = request.getSession();
		int userDelete = 0;
		if (session.getAttribute("admin") == null) {
			userDelete = (int) session.getAttribute("id");
		} else {
			userDelete = (int) (session.getAttribute("idVendeur"));
		}

		try {
			deleteUser.deleteUtilisateur(userDelete);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/Accueil");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		UtilisateurManager deleteUser = new UtilisateurManager();
		HttpSession session = request.getSession();
		int userDelete = (int) session.getAttribute("id");

		try {
			deleteUser.deleteUtilisateur(userDelete);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/Accueil");

	}

}
