package org.Encheres.Servlets;

import java.io.IOException;
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
import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Article;

@WebServlet("/Accueil")
public class ServletAccueil<BeanFiltreRecherche> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAccueil() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleManager am = new ArticleManager();
		UtilisateurManager um = new UtilisateurManager();
		int noUtilisateur;
		int noArticle;
		HttpSession sessionScope = request.getSession();
		sessionScope.setMaxInactiveInterval(300);
		List<Article> listeAAfficher = new ArrayList<>();
		request.setAttribute("sessionScope", sessionScope);
		sessionScope.setAttribute("noArticle", request.getAttribute("noArticle"));
		try {
			// noUtilisateur = (int) sessionScope.getAttribute("id");
			listeAAfficher = am.selectAllArticle();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("listeAAfficher", listeAAfficher);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String categorie;
		ArticleManager am = new ArticleManager();
		UtilisateurManager um = new UtilisateurManager();
		int noUtilisateur = 0;
		String articleRechercher = "";
		List<Article> listeAAfficher = new ArrayList<>();
		List<Article> listeTampon1 = new ArrayList<>();
		List<Article> listeTampon2 = new ArrayList<>();
		List<Article> listeTampon3 = new ArrayList<>();
		List<Integer> listeCodesErreur = new ArrayList<>();
		String contient = "";

		HttpSession sessionScope = request.getSession(); // Init de sessionScope
		sessionScope.setMaxInactiveInterval(300);
		// Verification si utilisateur connecté
		if (sessionScope.getAttribute("id") != null) {
			noUtilisateur = (int) sessionScope.getAttribute("id");
		}

		// Traitement choix catégories
		try {
			if (!request.getParameter("categorie").equals("")) {
				categorie = request.getParameter("categorie");
				listeTampon1 = am.selectArticleByCategorie(categorie);
				listeTampon3 = am.selectArticleByCategorie(categorie);
				// Radio boutons et checkbox
				// 1ere checkbox Achat
				if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("1")) {
					if (request.getParameter("enCours") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectArticleIfNotEnd(art.getNoArticle(), -1));
						}
					}
					if (request.getParameter("mesEnCours") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectArticleIfNotEnd(art.getNoArticle(), noUtilisateur));
						}
					}
					if (request.getParameter("remportes") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectByNoAcquereurIfEnd(art.getNoArticle(), noUtilisateur));
						}
					}
				}
				// check box filtreVente
				if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("2")) {
					if (request.getParameter("venteEnCours") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectVenteIfNotEnd(art.getNoArticle(), noUtilisateur));
						}
					}
					if (request.getParameter("venteNonDebut") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectVenteIfNotBegin(art.getNoArticle(), noUtilisateur));
						}
					}
					if (request.getParameter("venteTerminee") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectByNoVendeurIfEnd(art.getNoArticle(), noUtilisateur));
						}
					}
				}
				listeTampon1.clear();
				if (!request.getParameter("contient").equals("")) {
					contient = request.getParameter("contient");
					for (Article art : listeTampon2) {
						listeTampon1.addAll(am.selectArticleWhere(contient));
						if (art.getNomArticle() != contient) {
							listeTampon1.remove(art);
						}

					}
				}
			} else {
				// Radio boutons et checkbox
				// 1ere checkbox Achat
				listeTampon1 = am.selectAllArticle();
				listeTampon3 = am.selectAllArticle();
				if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("1")) {
					if (request.getParameter("enCours") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectArticleIfNotEnd(art.getNoArticle(), -1));
						}
					}
					if (request.getParameter("mesEnCours") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectArticleIfNotEnd(art.getNoArticle(), noUtilisateur));
						}
					}
					if (request.getParameter("remportes") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectByNoAcquereurIfEnd(art.getNoArticle(), noUtilisateur));
						}
					}
				}
				// check box filtreVente
				if (request.getParameter("radioBtn") != null && request.getParameter("radioBtn").equals("2")) {
					if (request.getParameter("venteEnCours") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectVenteIfNotEnd(art.getNoArticle(), noUtilisateur));
						}
					}
					if (request.getParameter("venteNonDebut") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectVenteIfNotBegin(art.getNoArticle(), noUtilisateur));
						}
					}
					if (request.getParameter("venteTerminee") != null) {
						for (Article art : listeTampon1) {
							listeTampon2.addAll(am.selectByNoVendeurIfEnd(art.getNoArticle(), noUtilisateur));
						}
					}
				}
				listeTampon1.clear();
				if (!request.getParameter("contient").equals("")) {
					contient = request.getParameter("contient");
					for (Article art : listeTampon2) {
						listeTampon1.addAll(am.selectArticleWhere(contient));
					}
				}
			}
			if (listeTampon1.size() != 0) {
				listeAAfficher = removeDuplicates(listeTampon1);
			} else if (listeTampon2.size() != 0) {
				listeAAfficher = removeDuplicates(listeTampon2);
			} else {
				listeAAfficher = removeDuplicates(listeTampon3);
			}

			request.setAttribute("listeAAfficher", listeAAfficher);
		} catch (Exception e) {
			e.printStackTrace();
			listeCodesErreur.add(3010);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

	private static List<Article> removeDuplicates(List<Article> list) {
		ArrayList<Article> uniqueList = new ArrayList<>();
		for (Article article : list) {
			if (!inArray(article, uniqueList)) {
				uniqueList.add(article);
			}
		}

		return uniqueList;
	}

	private static boolean inArray(Article test, List<Article> list) {
		for (Article article : list) {
			if (article.getNoArticle() == test.getNoArticle()) {
				return true;
			}
		}

		return false;
	}

}