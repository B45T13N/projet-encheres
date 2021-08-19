package org.Encheres.bll;

import org.Encheres.dal.DAO.DAORetrait;
import org.Encheres.dal.JDBCImpl.RetraitDAOJdbcImpl;

public class RetraitManager {

	private DAORetrait retraitDAO;

	public RetraitManager() {
		this.retraitDAO = new RetraitDAOJdbcImpl();
	}

}
