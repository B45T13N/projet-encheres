package org.Encheres.bo;

import java.time.LocalDate;

import org.apache.tomcat.jni.Local;

public class TestUtilisateur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utilisateur perso = new Utilisateur(1,"Eklypse","Martins","Pedro","pedro@test.com",0610101010,"Rue 50",84500,"Bollène","Pedro8408",50,true);
		System.out.println("Utilisateur :");
		System.out.println(perso.toString());
		
		LocalDate dateVente = LocalDate.now();
		LocalDate dateFinVente;
		
		Article article = new Article(perso.getNoUtilisateur(),1,"Table","Un plateau et 4 pieds",Categorie.Ameublement,dateVente,500,500,"en cours");
		System.out.println("Article :");
		System.out.println(article.toString());

	}

}
