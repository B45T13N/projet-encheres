package org.Encheres.dal;

import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Categorie;

public interface DAOArticle extends DAO<Article> {

	@Override
	public void insert(Article data) throws BusinessException;
	
	public void delete(int idArticle) throws BusinessException;

	public void update(int idArticle) throws BusinessException;
	
	public List<Article> selectAll() throws BusinessException;
	
	public List<Article> selectByCategorie(Categorie categorie) throws BusinessException;

}
