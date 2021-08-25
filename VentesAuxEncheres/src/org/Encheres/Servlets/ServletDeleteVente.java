package org.Encheres.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.Encheres.BusinessException;
import org.Encheres.bll.ArticleManager;

/**
 * Servlet implementation class ServletDeleteProfil
 */
@WebServlet("/DeleteVente")
public class ServletDeleteVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleManager am = new ArticleManager();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int noArticle = (int) session.getAttribute("noArticle");

		try {
			am.deleteArticle(noArticle);
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

		ArticleManager am = new ArticleManager();
		HttpSession session = request.getSession();
		int noArticle = (int) session.getAttribute("noArticle");

		try {
			am.deleteArticle(noArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/Accueil");

	}

}
