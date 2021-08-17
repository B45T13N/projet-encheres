package org.Encheres.dal;

import org.Encheres.bo.Article;

public class DAOFactory {

	public static DAO<Article> getRepasDAO() {
		return new ArticleDAOJdbcImpl();
	}
}
