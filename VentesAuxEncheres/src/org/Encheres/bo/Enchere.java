package org.Encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Enchere implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private int noArticle;
	private int noUtilisateur;
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	public Enchere() {
		
	}
	
	
	
	
	public Enchere(int noArticle, int noUtilisateur, LocalDate dateEnchere, int montantEnchere) {
		super();
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	
	
	

}
