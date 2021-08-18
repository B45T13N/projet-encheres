package org.Encheres.bo;

import java.time.LocalDate;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utilisateur perso = new Utilisateur("Eklypse", "Martins", "Pedro", "pedro@test.com", "0610101010", "Rue 50",
				"84500", "Boll�ne", "Pedro8408");
		System.out.println("Utilisateur :");
		System.out.println(perso.toString());

		LocalDate dateVente = LocalDate.now();
		LocalDate dateFinVente;
		Categorie categorie = new Categorie(1, "Ameublement");

		Article article = new Article(1, "Table", "Un plateau et 4 pieds", categorie, dateVente, 500, 500, "en cours");
		article.setNoUtilisateur(perso.getNoUtilisateur());
		System.out.println("Article :");
		System.out.println(article.toString());

	}

}
