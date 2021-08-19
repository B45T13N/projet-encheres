package org.Encheres.dal;

public abstract class CodesResultatDAL {
	// Code RESULTAT DAL Allant de 10000 à 19999;

	// TOUTES TABLES
	public final static int INSERT_NULL = 10000;

	public final static int DELETE_FAIL = 10001;

	// table utilisateurs:

	public final static int INSERT_UTILISATEUR_FAIL = 10002;

	public final static int UPDATE_UTILISATEUR_FAIL = 10003;

	// TABLE ARTICLE

	public final static int INSERT_ARTICLE_FAIL = 10004;

	public final static int LECTURE_ARTICLE_FAIL = 10005;

	public final static int UPDATE_ARTICLE_FAIL = 10006;

	// table enchere

	public final static int INSERT_ENCHERE_FAIL = 10007;

	public final static int UPDATE_ENCHERE_FAIL = 10008;

	// table retrait

	public final static int INSERT_RETRAIT_FAIL = 10009;
	public final static int LECTURE_RETRAIT_FAIL = 10010;

	// table categorie

	public final static int LECTURE_LIBELLE_FAIL = 10011;
}
