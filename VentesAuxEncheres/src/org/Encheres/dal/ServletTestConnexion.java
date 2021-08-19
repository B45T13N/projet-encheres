package org.Encheres.dal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Encheres.BusinessException;
import org.Encheres.dal.JDBCImpl.ArticleDAOJdbcImpl;

/**
 * Servlet implementation class ServletTestConnexion
 */
@WebServlet("/ServletTestConnexion")
public class ServletTestConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTestConnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
//		UtilisateurDAOJdbcImpl user = new UtilisateurDAOJdbcImpl();
//		Utilisateur u1 = new Utilisateur(4, "Eklypses", "modifier", "Pedro", "modif@test.fr", "0655104050", "ENI",
//				"75100", "VilleEni", "ENI123");
//		try {
//			user.update(u1);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		ArticleDAOJdbcImpl article = new ArticleDAOJdbcImpl();
//		Article a1 = new Article(9, "Chaussures", "modifier", "ameublement", LocalDate.of(2021, 8, 20),
//				LocalDate.of(2021, 9, 15), 300);
//		try {
//			article.update(a1);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		try {
//			article.delete(10);
//		} catch (BusinessException e) {
//
//		}

		try {
			List<String> list = article.selectAll();
			for (String art : list) {
				out.println(art);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println("Erreur connexion bdd");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
