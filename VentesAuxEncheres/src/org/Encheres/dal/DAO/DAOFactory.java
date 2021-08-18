package org.Encheres.dal.DAO;

import org.Encheres.bo.Article;
import org.Encheres.dal.JDBCImpl.ArticleDAOJdbcImpl;

public class DAOFactory {

	public static DAO<Article> getRepasDAO() {
		return new ArticleDAOJdbcImpl();
	}
}
