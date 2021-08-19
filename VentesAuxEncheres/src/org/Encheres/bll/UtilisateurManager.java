package org.Encheres.bll;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Utilisateur;
import org.Encheres.dal.DAO.DAOFactory;
import org.Encheres.dal.DAO.DAOUtilisateur;

public class UtilisateurManager {

	private static DAOUtilisateur daoUtilisateur;

	public UtilisateurManager() {

		daoUtilisateur = DAOFactory.getUtilisateursDAO();

	}

	public Utilisateur getUtilisateur() {
		Utilisateur utilisateur = null;
		try {
			utilisateur = daoUtilisateur.selectUtilisateurCourant(1);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	/**
	 * @param data
	 * @throws BusinessException
	 * @see org.Encheres.dal.DAO.DAOUtilisateur#insert(org.Encheres.bo.Utilisateur)
	 */
	public Utilisateur addUtilisateur(Utilisateur newUtilisateur) throws BusinessException {
		validerUtilisateur(newUtilisateur);
		daoUtilisateur.insert(newUtilisateur);
		return newUtilisateur;
	}

	/**
	 * @param data
	 * @throws BusinessException
	 * @see org.Encheres.dal.DAO.DAOUtilisateur#update(org.Encheres.bo.Utilisateur)
	 */
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		validerUpdate(utilisateur);
		daoUtilisateur.update(utilisateur);

	}

	/**
	 * @param noUtilisateur
	 * @throws BusinessException
	 * @see org.Encheres.dal.DAO.DAOUtilisateur#delete(int)
	 */
	public void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		daoUtilisateur.delete(utilisateur.getNoUtilisateur());
	}

	public void addArticle(Article newArticle) throws BusinessException {
		ArticleManager article = new ArticleManager();
		article.addArticle(newArticle.getDateDebutEncheres(), newArticle.getDateFinEncheres(),
				newArticle.getDescription(), newArticle.getNomArticle(), newArticle.getMiseAPrix(),
				newArticle.getLieuRetrait(), newArticle.getlibelle());

	}

	public void updateArticle(Article article) throws BusinessException {

	}

	public void deleteArticle(Article article) throws BusinessException {

	}

	private void validerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		boolean valide = true;
		BusinessException businessException = new BusinessException();
		if (utilisateur == null) {
			System.out.println("Utilisateur null");
		}
		if (utilisateur.getPseudo() == null || utilisateur.getPseudo().trim().length() == 0
				|| utilisateur.getPseudo().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getNom() == null || utilisateur.getNom().trim().length() == 0
				|| utilisateur.getNom().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().length() == 0
				|| utilisateur.getPrenom().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getEmail() == null || utilisateur.getEmail().trim().length() == 0
				|| utilisateur.getEmail().trim().length() > 50) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getTelephone() == null || utilisateur.getTelephone().trim().length() == 0
				|| utilisateur.getTelephone().trim().length() > 15) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getRue() == null || utilisateur.getRue().trim().length() == 0
				|| utilisateur.getRue().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getCodePostal() == null || utilisateur.getCodePostal().trim().length() == 0
				|| utilisateur.getCodePostal().trim().length() > 10) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getVille() == null || utilisateur.getVille().trim().length() == 0
				|| utilisateur.getVille().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().trim().length() == 0
				|| utilisateur.getMotDePasse().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}

		if (!valide) {
			businessException.ajouterErreur(10025);
		}

	}

	private void validerUpdate(Utilisateur utilisateur) throws BusinessException {
		boolean valide = true;
		BusinessException businessException = new BusinessException();
		if (utilisateur.getPseudo().trim().length() == 0 || utilisateur.getPseudo().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getNom().trim().length() == 0 || utilisateur.getNom().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getPrenom().trim().length() == 0 || utilisateur.getPrenom().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getEmail().trim().length() == 0 || utilisateur.getEmail().trim().length() > 50) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getTelephone().trim().length() == 0 || utilisateur.getTelephone().trim().length() > 15) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getRue().trim().length() == 0 || utilisateur.getRue().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getCodePostal().trim().length() == 0 || utilisateur.getCodePostal().trim().length() > 10) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getVille().trim().length() == 0 || utilisateur.getVille().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}
		if (utilisateur.getMotDePasse().trim().length() == 0 || utilisateur.getMotDePasse().trim().length() > 30) {
			businessException.ajouterErreur(10025);
			valide = false;
		}

		if (!valide) {
			businessException.ajouterErreur(10025);
		}
	}

}
