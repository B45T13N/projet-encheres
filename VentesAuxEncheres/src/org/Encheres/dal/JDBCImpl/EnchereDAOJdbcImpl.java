package org.Encheres.dal.JDBCImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import org.Encheres.BusinessException;
import org.Encheres.bo.Enchere;
import org.Encheres.dal.CodesResultatDAL;
import org.Encheres.dal.DAO.DAOEnchere;
import org.Encheres.dal.JDBCTools.ConnectionProvider;

public class EnchereDAOJdbcImpl implements DAOEnchere {

	public static final String INSERT_ENCHERE = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur)"
			+ " VALUES(?,?,?,?)";
	public static final String DELETE_ENCHERE = "DELETE FROM ENCHERES WHERE no_article=?";

	public static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere= ?, no_utilisateur=?  WHERE no_article = ?";

	// INSERT enchere
	@Override
	public void insert(Enchere data) throws BusinessException {

		if (data == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'une enchère
				PreparedStatement prstms = cnx.prepareStatement(INSERT_ENCHERE);

				prstms.setDate(1, Date.valueOf(data.getDateEnchere()));
				prstms.setInt(2, data.getMontantEnchere());
				prstms.setInt(3, data.getNoArticle());
				prstms.setInt(4, data.getNoUtilisateur());
				prstms.executeUpdate();
				prstms.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_FAIL);
				throw businessException;

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// Delete enchere
	@Override
	public void delete(int noArticle) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// suppression d'une enchère
				PreparedStatement prstms = cnx.prepareStatement(DELETE_ENCHERE);
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
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

//	private Enchere enchereBuilder(ResultSet rs) throws SQLException {
//	        Enchere encherePlusHaute;
//	        encherePlusHaute = new Enchere();
//	        encherePlusHaute.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
//	        encherePlusHaute.setMontantEnchere(rs.getInt("montant_enchere"));
//	        return encherePlusHaute;

	// Update enchere
	@Override
	public void update(int noArticle, int noUtilisateur, int montantEnchere, LocalDate date) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Mise à jour enchère
				PreparedStatement prstms = cnx.prepareStatement(UPDATE_ENCHERE);
				prstms.setDate(1, Date.valueOf(date));
				prstms.setInt(2, montantEnchere);
				prstms.setInt(3, noUtilisateur);
				prstms.setInt(4, noArticle);
				prstms.executeUpdate();
				prstms.close();
				cnx.commit();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();

			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ENCHERE_FAIL);
			throw businessException;
		}

	}

}
