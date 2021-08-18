package org.Encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements DAORetrait{

	public static final String INSERT_RETRAITS = "INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES(?,?,?,?)";
	
	public static final String DELETE_RETRAITS = "DELETE FROM RETRAITS WHERE no_article=?";
	
	@Override
	public void insert(Retrait data) throws BusinessException{
		if (data == null) 
		{
			BusinessException busi = new BusinessException();
			busi.ajouterErreur(10000);
			throw busi;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			try {
				cnx.setAutoCommit(false);
				PreparedStatement prstms = cnx.prepareStatement(INSERT_RETRAITS, PreparedStatement.RETURN_GENERATED_KEYS);
				
				if (data.getRue().length() < 30 || data.getCodePostal().length() < 15 || data.getVille().length() < 30) {
					prstms.setString(1, data.getRue());
					prstms.setString(2, data.getCodePostal());
					prstms.setString(3, data.getVille());
				} else {
					BusinessException busi = new BusinessException();
					busi.ajouterErreur(10002);
					throw busi;
				}
				ResultSet rs = prstms.getGeneratedKeys();
				if(rs.next()) {
					data.setId(rs.getInt(1));
				}
				rs.close();
				prstms.close();
				cnx.commit();
				cnx.close();
			
			}
			catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException busi = new BusinessException();
			busi.ajouterErreur(10001);
			throw busi;
		}
	}

	
	@Override
	public void delete(int noArticle) throws BusinessException {
		if (noArticle < 0) {
			BusinessException busi = new BusinessException();
			busi.ajouterErreur(10000);
			throw busi;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'un article
				PreparedStatement prstms = cnx.prepareStatement(DELETE_RETRAITS);
				prstms.setInt(1, noArticle);
				prstms.executeUpdate();

				prstms.close();
				cnx.commit();

				cnx.close();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10001);
			throw businessException;
		}
	}
	

	

	@Override
	public Retrait selectRetraitByIdArticle(int noArticle) {
		return null;
	}
	
	
	
}
