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
import org.Encheres.bo.Categorie;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArticleDAOJdbcImpl daoArticle = new ArticleDAOJdbcImpl();

		Utilisateur perso = new Utilisateur(1, "Eklypse", "Martins", "Pedro", "pedro@test.com", 0610101010, "Rue 50",
				84500, "Boll�ne", "Pedro8408", 50, true);
		System.out.println("Utilisateur :");
		System.out.println(perso.toString());

		LocalDate dateVente = LocalDate.of(2021, 8, 19);
		LocalDate dateFinVente = LocalDate.of(2021, 8, 31);
		Categorie categorie = new Categorie(1, "Ameublement");

		Article article = new Article(1, "Table", "Un plateau et 4 pieds", categorie, dateVente, 500, 500, "en cours");
		article.setNoUtilisateur(perso.getNoUtilisateur());
		article.setDateFinEncheres(dateFinVente);
		System.out.println("Article :");
		System.out.println(article.toString());

		try {
			daoArticle.insert(article);
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println("insertion erreur");
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
