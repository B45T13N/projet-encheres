package org.Encheres.dal.DAO;

import org.Encheres.dal.JDBCImpl.ArticleDAOJdbcImpl;
import org.Encheres.dal.JDBCImpl.RetraitDAOJdbcImpl;
import org.Encheres.dal.JDBCImpl.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	public static DAOArticle getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}

	public static DAOUtilisateur getUtilisateursDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static DAORetrait getDAORetrait() {
		return new RetraitDAOJdbcImpl();
	}
}
