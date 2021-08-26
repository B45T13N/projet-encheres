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

	public List<Article> selectArticleIfNotEnd(int noArticle, int noUser) throws BusinessException;

	public List<Article> selectByNoAcquereurIfEnd(int noArticle, int noUser) throws BusinessException;

	public List<Article> selectVenteIfNotEnd(int noArticle, int noUser) throws BusinessException;

	public List<Article> selectByNoVendeurIfEnd(int noArticle, int noUser) throws BusinessException;

	public Article selectByNoArticle(int noArticle) throws BusinessException;

	public List<Article> selectVenteIfNotBegin(int noArticle, int noUser) throws BusinessException;

	public List<Article> selectArticleByNoUser(int noUser) throws BusinessException;

	public void deleteArticleByNoUser(int noUser) throws BusinessException;

}
