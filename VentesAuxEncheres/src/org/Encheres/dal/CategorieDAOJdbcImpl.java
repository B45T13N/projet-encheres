package org.Encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.Encheres.BusinessException;
import org.Encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements DAOCategorie {

	private static final String INSERT_CATEGORIE = "INSERT INTO CATEGORIES(libelle) VALUES (?)";
	private static final String SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle like ?";

	@Override
	public void insert(Categorie data) throws BusinessException {
		if (data == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'un article
				PreparedStatement prstms = cnx.prepareStatement(INSERT_CATEGORIE,
						PreparedStatement.RETURN_GENERATED_KEYS);

				prstms.setString(1, data.getLibelle());

				ResultSet rs = prstms.getGeneratedKeys();
				if (rs.next()) {
					data.setNoCategorie(rs.getInt(1));
				}
				rs.close();
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
	public int selectByLibelle(String libelle) throws BusinessException {
		int noCategorie = 0;

		if (libelle == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10003);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			// Ajout d'un article
			PreparedStatement prstms = cnx.prepareStatement(SELECT_BY_LIBELLE);

			prstms.setString(1, libelle);

			ResultSet rs = prstms.executeQuery();
			if (rs.next()) {
				if (libelle.equalsIgnoreCase(rs.getString("libelle").trim())) {
					noCategorie = rs.getInt("no_categorie");
				}
			}
			rs.close();
			prstms.close();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10001);
			throw businessException;
		}

		return noCategorie;
	}

}
