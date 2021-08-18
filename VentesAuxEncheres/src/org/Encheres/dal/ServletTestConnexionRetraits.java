package org.Encheres.dal;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Encheres.BusinessException;
import org.Encheres.bo.Retrait;

/**
 * Servlet implementation class ServletTestConnexionRetraits
 */
@WebServlet("/ServletTestConnexionRetraits")
public class ServletTestConnexionRetraits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestConnexionRetraits() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RetraitDAOJdbcImpl daoRetrait = new RetraitDAOJdbcImpl();
		
		Retrait retrait = new Retrait(1, "50 rue Des Fleurs", "49190", "Angers");
		System.out.println("Retrait");
		System.out.println(retrait.toString());;
		
		try {
			daoRetrait.insert(retrait);
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println("Erreur Insersion Retrait");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
