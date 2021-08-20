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
		
		HttpSession sessionScope = request.getSession(); //Init de sessionScope
		
				
		try {
			if (request.getParameter("categorie")!=null) {
				categorie = request.getParameter("categorie");
				am.selectArticleByCategorie(categorie);
			}
			else { //Afficher toutes les catégories dispo
				
			}
		//Radio boutons et checkbox
//			if()
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
	}

}
