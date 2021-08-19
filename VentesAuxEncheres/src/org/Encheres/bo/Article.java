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
	private int noCategorie;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String lieuRetrait;
	private String libelle;

	public String getlibelle() {
		return libelle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public void setlibelle(String libelle) {
		this.libelle = libelle;
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

	// Constructeurs

	public Article() {
	}

	public Article(int noArticle, String nomArticle, String description, String libelle, LocalDate dateDebutEncheres,
			int prixVente) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.libelle = libelle;
		this.dateDebutEncheres = dateDebutEncheres;
		this.miseAPrix = prixVente;
		this.prixVente = prixVente;

	}

	public Article(int noArticle, String nomArticle, String description, String libelle, LocalDate dateDebutEncheres,
			int miseAPrix, int prixVente) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.libelle = libelle;
		this.dateDebutEncheres = dateDebutEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
	}

	public Article(int noArticle, String nomArticle, String description, String libelle, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.libelle = libelle;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	@Override
	public String toString() {
		return "Nom d'article : " + nomArticle + ", description : " + description + ",categorie d'article : " + libelle
				+ ", date fin d'encheres : " + dateFinEncheres + ", Prix de vente : " + prixVente;
	}

}
