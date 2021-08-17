package org.Encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int noUtilisateur;
	private int noArticle;
	private String nomArticle;
	private String description;
	private Categorie categorieArticle;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String lieuRetrait;
	private String etatVente;

	public Categorie getCategorieArticle() {
		return categorieArticle;
	}

	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	public String getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(String lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	// Constructeurs

	public Article() {
	}

	public Article(int noUtilisateur, int noArticle, String nomArticle, String description, Categorie categorieArticle,
			LocalDate dateDebutEncheres, int prixVente, String etatVente) {
		this.setNoUtilisateur(noUtilisateur);
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.categorieArticle = categorieArticle;
		this.dateDebutEncheres = dateDebutEncheres;
		this.miseAPrix = prixVente;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}

	public Article(int noUtilisateur, int noArticle, String nomArticle, String description, Categorie categorieArticle,
			LocalDate dateDebutEncheres, int miseAPrix, int prixVente, String etatVente) {
		this.setNoUtilisateur(noUtilisateur);
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.categorieArticle = categorieArticle;
		this.dateDebutEncheres = dateDebutEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	@Override
	public String toString() {
		return "Article [noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + ", nomArticle=" + nomArticle
				+ ", description=" + description + ", categorieArticle=" + categorieArticle + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix
				+ ", prixVente=" + prixVente + ", lieuRetrait=" + lieuRetrait + ", etatVente=" + etatVente + "]";
	}
	
	
	

}
