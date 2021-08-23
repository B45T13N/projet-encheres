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

import org.Encheres.bll.ArticleManager;
import org.Encheres.bll.UtilisateurManager;
import org.Encheres.bo.Article;
import org.Encheres.bo.Utilisateur;
import org.apache.naming.factory.BeanFactory;

/**
 * Servlet implementation class ServletAccueil
 * @param <BeanFiltreRecherche>
 */
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String categorie;
		ArticleManager am = new ArticleManager();
		UtilisateurManager um = new UtilisateurManager();
		int noUtilisateur;
		String articleRechercher ="";
		List<Article> listeAAficher = new ArrayList<>();
		
		HttpSession sessionScope = request.getSession(); //Init de sessionScope
		
		//Verification si utilisateur connecté 
		if(sessionScope.getAttribute("utilisateur")!=null) {
			Utilisateur utilisateur = (Utilisateur) sessionScope.getAttribute("utilisateur");
		}
		
		//Traitement choix catégories
		try {
			if (request.getParameter("categorie")!=null) {
				categorie = request.getParameter("categorie");
				am.selectArticleByCategorie(categorie);
			}
			else {
				categorie = request.getParameter("categorie");
				am.selectAllArticle();				
			}
			
		//Radio boutons et checkbox
			//1ere checkbox Achat
			if(request.getParameter("filtreAchat")!=null && request.getParameter("filtreAchat").equals("1")) 
			{
								
					if (request.getParameter("enCours")!=null)
					{
					//	am.setEnCours(true);   //methode sur ArticleManager
					}
					else {
					//	am.setEnCours(false);  //methode sur ArticleManager
					}
					if (request.getParameter("mesEnCours")!=null) {
						
					}
					else {
						
					}
					if (request.getParameter("remportes")!=null) {
						
					}
					else {
						
					}
			}
			//check box filtreVente
			if(request.getParameter("filtreVente")!=null && request.getParameter("filtreVente").equals("1")) 
			{
								
					if (request.getParameter("venteEnCours")!=null)
					{
					
					}
					else {
					
					}
					if (request.getParameter("venteNonDebut")!=null) {
						
					}
					else {
						
					}
					if (request.getParameter("venteTerminee")!=null) {
						
					}
					else {
						
					}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
	}

}
