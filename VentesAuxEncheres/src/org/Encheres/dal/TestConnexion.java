package org.Encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnexion {

	public static void main(String[] args) {
		System.out.println("début de test");
		try (Connection cnx = ConnectionProvider.getConnection()) {
			System.out.println("Connextion réussie");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connexion échouée");
		}
		System.out.println("fin de test");

	}

}
