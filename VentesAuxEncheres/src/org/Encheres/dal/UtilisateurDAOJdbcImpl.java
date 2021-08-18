package org.Encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Encheres.BusinessException;
import org.Encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements DAOUtilisateur {

	public static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";

	public static final String DELETE_USER = "DELETE FROM UTILISATEUR WHERE no_utilisateur = ?";

	public static final String UPDATE_USER = "UPDATE UTILISATEUR set pseudo = ?, nom = ?";

	public static final String SELECT_USER = "SELECT FROM UTILISATEUR WHERE no_utilisateur = ?";

	@Override
	public void insert(Utilisateur data) throws BusinessException {

		if (data == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			cnx.setAutoCommit(false);
			PreparedStatement prstms = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);

			prstms.setString(1, data.getPseudo());
			prstms.setString(2, data.getNom());
			prstms.setString(3, data.getPrenom());
			prstms.setString(4, data.getEmail());
			prstms.setInt(5, data.getTelephone());
			prstms.setString(6, data.getRue());
			prstms.setInt(7, data.getCodePostal());
			prstms.setString(8, data.getVille());
			prstms.setString(9, data.getMotDePasse());
			prstms.setInt(10, data.getCredit());

			prstms.executeUpdate();

			// if pour administrateur
			prstms.close();

			cnx.commit();
			cnx.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la connection au serveur SQL");
		}
	}

	@Override
	public void update(Utilisateur data) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement prstms = cnx.prepareStatement(UPDATE_USER);

				prstms.setString(1, data.getPseudo());
				prstms.setString(2, data.getNom());
				prstms.setString(3, data.getPrenom());
				prstms.setString(4, data.getEmail());
				prstms.setInt(5, data.getTelephone());
				prstms.setString(6, data.getRue());
				prstms.setInt(7, data.getCodePostal());
				prstms.setString(8, data.getVille());
				prstms.setString(9, data.getMotDePasse());
				prstms.setInt(10, data.getCredit());

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
	public List<Utilisateur> selectByNoUtilisateur(int noUtilisateur) throws BusinessException {

		List<Utilisateur> user = new ArrayList<Utilisateur>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement prstms = cnx.prepareStatement(SELECT_USER);
			ResultSet rs = prstms.getResultSet();
			Utilisateur infos = new Utilisateur();
			while (rs.next()) {
				infos = new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getInt("telephone"), rs.getString("rue"), rs.getInt("code_postal"),
						rs.getString("ville"));

			}
			user.add(infos);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void delete(int noUtilisateur) throws BusinessException {

		if (noUtilisateur < 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10003);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'un article
				PreparedStatement prstms = cnx.prepareStatement(DELETE_USER);
				prstms.setInt(1, noUtilisateur);
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
			businessException.ajouterErreur(10004);
			throw businessException;
		}

	}

}
