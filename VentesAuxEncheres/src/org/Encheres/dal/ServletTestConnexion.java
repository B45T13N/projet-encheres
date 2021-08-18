package org.Encheres.dal;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Utilisateur;

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

		UtilisateurDAOJdbcImpl user = new UtilisateurDAOJdbcImpl();

		Utilisateur perso = new Utilisateur("Eklypse", "Martins", "Pedro", "pedro@test.com", "0650505050", "Rue 50",
				"84500", "Bollene", "Pedro8408");
		System.out.println("Utilisateur :");
		System.out.println(perso.toString());
		try {
			user.insert(perso);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			System.out.println("Insertion erreur");
		}

		ArticleDAOJdbcImpl articleDAO = new ArticleDAOJdbcImpl();
		Article articleCourant = new Article(perso.getNoUtilisateur(), "Babouche", "deux chaussures", "Vêtement",
				LocalDate.of(2021, 8, 19), LocalDate.of(2021, 8, 31), 300);
		try {
			articleDAO.insert(articleCourant);
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println("Erreur insertion article");
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
