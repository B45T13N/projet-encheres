package org.Encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements DAORetrait{

	public static final String INSERT_RETRAITS = "INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES(?,?,?,?)";
	
	public static final String DELETE_RETRAITS = "DELETE FROM RETRAITS WHERE no_article=?";
	
	public static final String SELECT_RETRAITS = "SELECT FROM RETRAITS WHERE no_article=?";
	
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
				PreparedStatement prstms = cnx.prepareStatement(INSERT_RETRAITS);
				
				if (data.getRue().length() < 30 || data.getCodePostal().length() < 15 || data.getVille().length() < 30) {
					prstms.setInt(1, data.getId());
					prstms.setString(2, data.getRue());
					prstms.setString(3, data.getCodePostal());
					prstms.setString(4, data.getVille());
				} else {
					BusinessException busi = new BusinessException();
					busi.ajouterErreur(10002);
					throw busi;
				}
				
				
				
				
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
		Retrait retraitCourant = new Retrait();
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement prstms = cnx.prepareStatement(SELECT_RETRAITS);
			prstms.setInt(1, noArticle);
			ResultSet rs = prstms.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("no_article") == noArticle) {
					retraitCourant = new Retrait(noArticle, rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
					
					
				}
			}
			
			
		} catch (SQLException e) {
			BusinessException busi = new BusinessException();
			busi.ajouterErreur(10003);
			e.printStackTrace();
		}
		return retraitCourant;
	}
	
	
	
}
