package org.Encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.Encheres.BusinessException;
import org.Encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements DAOUtilisateur {
	
	public static final String INSERT_USER = "INSERT INTO UTILISATEURS VALUES(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, )";
	
	public static final String DELETE_USER = "DELETE FROM WHERE no_utilisateur = ?";
	
	public static final String UPDATE_USER = "UPDATE ";
	

	@Override
	public void insert(Utilisateur data) throws BusinessException {
		
		if (data == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
			throw businessException;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()){	
			cnx.setAutoCommit(false);
			PreparedStatement prstms = cnx.prepareStatement(INSERT_USER);
			ResultSet rs = prstms.getGeneratedKeys();
			
			if(rs.next()) {
				data.setNoUtilisateur(rs.getInt(1));
			}
			
			// set Pseudo statement
			if(data.getPseudo().length() < 30 || data.getNom().length() < 30 
					|| data.getPrenom().length() < 30 || data.getEmail().length() < 20 ) {
				
				prstms.setString(1, data.getPseudo());
				prstms.setString(2, data.getNom());
				prstms.setString(3, data.getPrenom());
				prstms.setString(4, data.getEmail());
				
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(10001);
				throw businessException;	
			}
			
			prstms.setInt(5, data.getTelephone());
			prstms.setString(6, data.getRue());
			prstms.setInt(7, data.getCodePostal());
			
			if(data.getVille().length() < 30 || data.getMotDePasse().length() < 30) {
				
				prstms.setString(8, data.getVille());
				prstms.setString(9, data.getMotDePasse());
			
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(10006);
				throw businessException;
			}

			
			prstms.setInt(10, data.getCredit());
			prstms.executeUpdate();
			
			//if pour administrateur
			rs.close();
			prstms.close();
			
			
			cnx.commit();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la connection au serveur SQL");
		}
			
		
		
		
	}

	@Override
	public void update(int noUtilisateur) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				// Ajout d'un article
				PreparedStatement prstms = cnx.prepareStatement(UPDATE_USER);
				
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
	public void delete(int noUtilisateur) throws BusinessException {
		
		if(noUtilisateur < 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
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
			businessException.ajouterErreur(10001);
			throw businessException;
		}
		
		
	}

}
