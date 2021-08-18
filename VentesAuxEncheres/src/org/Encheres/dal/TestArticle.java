package org.Encheres.dal;

import java.time.LocalDate;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Categorie;
import org.Encheres.bo.Utilisateur;

public class TestArticle {

	public static void main(String[] args) {

		ArticleDAOJdbcImpl daoArticle = new ArticleDAOJdbcImpl();
		UtilisateurDAOJdbcImpl user = new UtilisateurDAOJdbcImpl();

		Utilisateur perso = new Utilisateur("Eklypse", "Martins", "Pedro", "pedro@test.com", "0610101010", "Rue 50",
				"84500", "Boll�ne", "Pedro8408");
		System.out.println("Utilisateur :");
		System.out.println(perso.toString());

		try {
			user.insert(perso);
			System.out.println("Votre utilisateur a été enregistrer!");
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		LocalDate dateVente = LocalDate.now();
		LocalDate dateFinVente = LocalDate.of(2021, 8, 31);
		Categorie categorie = new Categorie(1, "Ameublement");

		Article article = new Article(1, "Table", "Un plateau et 4 pieds", categorie, dateVente, 500, 500, "en cours");
		article.setNoUtilisateur(perso.getNoUtilisateur());
		article.setDateFinEncheres(dateFinVente);
		System.out.println("Article :");
		System.out.println(article.toString());

		try {
			daoArticle.insert(article);
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println("insertion erreur");
		}

	}

}
