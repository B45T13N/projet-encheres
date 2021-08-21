package org.Encheres.Servlets;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.Encheres.BusinessException;
import org.Encheres.bll.ArticleManager;
import org.Encheres.bo.Article;
import org.Encheres.bo.Enchere;
import org.Encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/NouvelleVente")
public class ServletVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur currentUser = (Utilisateur) session.getAttribute("utilisateur");
		String nomArticle = "";
		String description = "";
		String libelle = "";
		int miseAPrix = 0;
		int noUser = 1;
		LocalDate dateDebutEnchere = null;
		LocalDate dateFinEnchere = null;
		Article article = null;
		String rue = ""; // currentUser.getRue();
		String codePostal = "";// currentUser.getCodePostal();
		String ville = "";// currentUser.getVille();

		request.setCharacterEncoding("UTF-8");

		List<Integer> listeCodeErreur = new ArrayList<>();

		// Complétion des champs du retrait avec des valeurs de bases
		request.setAttribute("rue", rue);
		request.setAttribute("cpo", codePostal);
		request.setAttribute("ville", ville);

		// Enregistrement nom, description, libelle, mise à prix
		nomArticle = request.getParameter("nomArticle");
		description = request.getParameter("description");
		libelle = request.getParameter("libelle");
		miseAPrix = Integer.parseInt(request.getParameter("prix"));

		// enregistrmeent des dates
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebutEnchere = LocalDate.parse(request.getParameter("dateDebut"), dtf);
			dateFinEnchere = LocalDate.parse(request.getParameter("dateFin"), dtf);
		} catch (DateTimeException e) {
			e.printStackTrace();
			listeCodeErreur.add(0);
		}
		// Verification des modifs éventuelles sur l'adresse de retrait
		rue = request.getParameter("rue");
		ville = request.getParameter("ville");
		codePostal = request.getParameter("cpo");

		if (listeCodeErreur.size() > 0) {
			request.setAttribute("listeCodeErreur", listeCodeErreur);
		} else {
			ArticleManager am = new ArticleManager();
//			EnchereManager em = new EnchereManager();
			article = new Article(description, nomArticle, libelle, dateDebutEnchere, dateFinEnchere, miseAPrix);
			article.setNoUtilisateur(1);
			Enchere enchere = new Enchere();

			try {
				am.addArticle(article, rue, ville, codePostal);
//				enchere.setDateEnchere(article.getDateDebutEncheres());
//				enchere.setMontantEnchere(article.getMiseAPrix());
//				enchere.setNoArticle(article.getNoArticle());
//				enchere.setNoUtilisateur(article.getNoUtilisateur());
//				em.addEnchere(enchere);
				RequestDispatcher rd = request.getRequestDispatcher("/DetailVente");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();

			}
		}

	}

}
