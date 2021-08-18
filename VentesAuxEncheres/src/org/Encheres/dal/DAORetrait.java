package org.Encheres.dal;

import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Retrait;

public interface DAORetrait {

		public void insert (Retrait retrait) throws BusinessException;
		
			
		public void delete(int noArticle) throws BusinessException;
		
				
		public Retrait selectRetraitByIdArticle(int noArticle);
}
