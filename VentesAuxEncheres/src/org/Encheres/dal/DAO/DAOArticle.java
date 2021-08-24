package org.Encheres.dal.DAO;

import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;

public interface DAOArticle extends DAO<Article> {

	@Override
	public void insert(Article data) throws BusinessException;

	public void delete(int idArticle) throws BusinessException;

	public void update(Article data) throws BusinessException;

	public List<Article> selectAll() throws BusinessException;

	public List<Article> selectByCategorie(int noCategorie) throws BusinessException;

	public Article selectByNoArticle(int noArticle) throws BusinessException;

}
