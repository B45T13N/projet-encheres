package org.Encheres.Servlets;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.Encheres.BusinessException;
import org.Encheres.bll.ArticleManager;
import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Article;
import org.Encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/NouvelleVente")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ServletVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String IMAGES_FOLDER = "/Images";

	public String uploadPath;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(300);
		int idUser = (int) session.getAttribute("id");
		int idArticle = 0;
		if (session.getAttribute("noArticle") != null) {
			idArticle = (int) session.getAttribute("noArticle");
		}

		List<Integer> listException = new ArrayList<Integer>();
		UtilisateurManager um = new UtilisateurManager();
		Utilisateur currentUser = new Utilisateur();
		ArticleManager am = new ArticleManager();
		Article currentArticle = new Article();
		try {
			if (idArticle == 0) {
				currentUser = um.selectByNoUtilisateur(idUser);
				currentUser.setNoUtilisateur(idUser);
				request.setAttribute("currentUser", currentUser);
			} else {
				currentUser = um.selectByNoUtilisateur(idUser);
				currentUser.setNoUtilisateur(idUser);
				request.setAttribute("currentUser", currentUser);
				currentArticle = am.selectArticleByNoArticle(idArticle);
				request.setAttribute("currentArticle", currentArticle);

			}
		} catch (BusinessException e1) {
			e1.printStackTrace();
			listException.add(30004);
		}
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
		session.setMaxInactiveInterval(300);
		String urlPhoto = "";
		int idUser = (int) session.getAttribute("id");
		for (Part part : request.getParts()) {
			String fileName = getFileName(part);
			if (fileName != "Default.file") {
				urlPhoto = fileName;
			}
			String fullPath = uploadPath + File.separator + fileName;
			part.write(fullPath);
		}

		List<Integer> listException = new ArrayList<Integer>();
		UtilisateurManager um = new UtilisateurManager();
		Utilisateur currentUser = new Utilisateur();
		try {
			currentUser = um.selectByNoUtilisateur(idUser);
			request.setAttribute("currentUser", currentUser);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			listException.add(30004);
		}
		String nomArticle = "";
		String description = "";
		String libelle = "";
		int miseAPrix = 0;
		LocalDate dateDebutEnchere = null;
		LocalDate dateFinEnchere = null;
		Article article = null;
		String rue = "";
		String codePostal = "";
		String ville = "";

		request.setCharacterEncoding("UTF-8");

		List<Integer> listeCodeErreur = new ArrayList<>();

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
			article = new Article(nomArticle, description, libelle, dateDebutEnchere, dateFinEnchere, miseAPrix);
			article.setNoUtilisateur(idUser);
			article.setUrlPhoto(urlPhoto);
//			Enchere enchere = new Enchere();

			try {
				int idArticle = 0;
				if (session.getAttribute("noArticle") != null) {
					idArticle = (int) session.getAttribute("noArticle");
					article.setNoArticle(idArticle);
					am.updateArticle(article);
				} else {
					am.addArticle(article, rue, ville, codePostal);
				}
				response.sendRedirect(request.getContextPath() + "/Accueil");
			} catch (BusinessException e1) {
				e1.printStackTrace();
				listException.add(30004);
			}
		}

	}

	public void init() throws ServletException {
		uploadPath = "E:\\git\\projet-encheres\\VentesAuxEncheres\\WebContent" + IMAGES_FOLDER;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();
	}

	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return "Default.file";
	}
}