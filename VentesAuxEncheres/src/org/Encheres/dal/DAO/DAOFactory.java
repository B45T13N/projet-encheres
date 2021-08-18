package org.Encheres.dal.DAO;

import org.Encheres.bo.Article;
import org.Encheres.bo.Utilisateur;
import org.Encheres.dal.JDBCImpl.ArticleDAOJdbcImpl;
import org.Encheres.dal.JDBCImpl.RetraitDAOJdbcImpl;
import org.Encheres.dal.JDBCImpl.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	public static DAO<Article> getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}

	public static DAO<Utilisateur> getUtilisateursDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static DAORetrait getDAORetrait() 
	{
	return new RetraitDAOJdbcImpl()	;
	}
}
