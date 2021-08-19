package org.Encheres.bll;

public abstract class CodesResultatBLL {
	// Code RESULTAT DAL Allant de 20000 à 29999;

	// TOUT LES MANAGERS

	public final static int INSERT_NULL = 28000;

	// Manager utilisateurs:

	public final static int UTILISATEUR_ERROR_NOM = 20000;
	public final static int UTILISATEUR_ERROR_PSEUDO = 20001;
	public final static int UTILISATEUR_ERROR_EMAIL = 20002;
	public final static int UTILISATEUR_ERROR_TELEPHONE = 20003;
	public final static int UTILISATEUR_ERROR_RUE = 20004;
	public final static int UTILISATEUR_ERROR_CPO = 20005;
	public final static int UTILISATEUR_ERROR_VILLE = 20006;
	public final static int UTILISATEUR_ERROR_MDP = 20007;
	public final static int UTILISATEUR_ERROR_PRENOM = 20008;

	// TABLE ARTICLE

	public final static int ARTICLE_ERROR_NOM = 20009;
	public final static int ARTICLE_ERROR_DESCRIPTION = 20010;

	// table enchere

	public final static int ENCHERE_ERROR_AMOUNT = 20005;
}
