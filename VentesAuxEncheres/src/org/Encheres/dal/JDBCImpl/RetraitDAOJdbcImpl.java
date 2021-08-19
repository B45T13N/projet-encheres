package org.Encheres.dal.JDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.Encheres.BusinessException;
import org.Encheres.bo.Retrait;
import org.Encheres.dal.CodesResultatDAL;
import org.Encheres.dal.DAO.DAORetrait;
import org.Encheres.dal.JDBCTools.ConnectionProvider;

public class RetraitDAOJdbcImpl implements DAORetrait {

	public static final String INSERT_RETRAITS = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(?,?,?,?)";

	public static final String DELETE_RETRAITS = "DELETE FROM RETRAITS WHERE no_article=?";

	public static final String SELECT_RETRAITS = "SELECT FROM RETRAITS WHERE no_article=?";

	@Override
	public void insert(Retrait data) throws BusinessException {
		if (data == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);
				PreparedStatement prstms = cnx.prepareStatement(INSERT_RETRAITS,
						PreparedStatement.RETURN_GENERATED_KEYS);

				prstms.setInt(1, data.getId());
				prstms.setString(2, data.getRue());
				prstms.setString(3, data.getCodePostal());
				prstms.setString(4, data.getVille());
				prstms.executeUpdate();

				prstms.close();
				cnx.commit();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();

				cnx.rollback();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_RETRAIT_FAIL);
				throw businessException;

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
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.DELETE_FAIL);
				throw businessException;
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
	public Retrait selectRetraitByIdArticle(int noArticle) throws BusinessException {
		Retrait retraitCourant = new Retrait();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement prstms = cnx.prepareStatement(SELECT_RETRAITS);
			prstms.setInt(1, noArticle);
			ResultSet rs = prstms.executeQuery();

			while (rs.next()) {
				if (rs.getInt("no_article") == noArticle) {
					retraitCourant = new Retrait(noArticle, rs.getString("rue"), rs.getString("code_postal"),
							rs.getString("ville"));

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_FAIL);
			throw businessException;
		}
		return retraitCourant;
	}

}
