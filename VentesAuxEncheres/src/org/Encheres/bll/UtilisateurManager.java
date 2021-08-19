package org.Encheres.bll;

import org.Encheres.BusinessException;
import org.Encheres.bo.Article;
import org.Encheres.bo.Utilisateur;
import org.Encheres.dal.DAO.DAOFactory;
import org.Encheres.dal.DAO.DAOUtilisateur;

public class UtilisateurManager {

	private static DAOUtilisateur daoUtilisateur;
	private ArticleManager articleManager;

	public UtilisateurManager() {

		daoUtilisateur = DAOFactory.getUtilisateursDAO();
		articleManager = new ArticleManager();

	}

	public Utilisateur getUtilisateur(String login, String password) throws BusinessException {
		Utilisateur utilisateur = null;
		utilisateur = daoUtilisateur.selectUtilisateurCourant(login, password);
		return utilisateur;
	}

	/**
	 * @param data
	 * @throws BusinessException
	 * @see org.Encheres.dal.DAO.DAOUtilisateur#insert(org.Encheres.bo.Utilisateur)
	 */
	public Utilisateur addUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws BusinessException {
		BusinessException exception = new BusinessException();

		Utilisateur newUtilisateur = new Utilisateur();
		newUtilisateur.setPseudo(pseudo);
		newUtilisateur.setNom(nom);
		newUtilisateur.setPrenom(prenom);
		newUtilisateur.setEmail(email);
		newUtilisateur.setTelephone(telephone);
		newUtilisateur.setRue(rue);
		newUtilisateur.setCodePostal(codePostal);
		newUtilisateur.setVille(ville);
		newUtilisateur.setMotDePasse(motDePasse);

		this.validerUtilisateur(newUtilisateur, exception);
		if (!exception.hasError()) {
			daoUtilisateur.insert(newUtilisateur);
		} else {
			throw exception;
		}
		return newUtilisateur;
	}

	/**
	 * @param data
	 * @throws BusinessException
	 * @see org.Encheres.dal.DAO.DAOUtilisateur#update(org.Encheres.bo.Utilisateur)
	 */
	public void updateUtilisateur(Utilisateur updateUser) throws BusinessException {
		BusinessException exception = new BusinessException();

//		Utilisateur updateUser = new Utilisateur();
//		updateUser.getNoUtilisateur();
//		updateUser.setPseudo(pseudo);
//		updateUser.setNom(nom);
//		updateUser.setPrenom(prenom);
//		updateUser.setEmail(email);
//		updateUser.setTelephone(telephone);
//		updateUser.setRue(rue);
//		updateUser.setCodePostal(codePostal);
//		updateUser.setVille(ville);
//		updateUser.setMotDePasse(motDePasse);

		this.validerUtilisateur(updateUser, exception);
		if (!exception.hasError()) {
			daoUtilisateur.update(updateUser);
		}
	}

	/**
	 * @param noUtilisateur
	 * @throws BusinessException
	 * @see org.Encheres.dal.DAO.DAOUtilisateur#delete(int)
	 */
	public void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		daoUtilisateur.delete(utilisateur.getNoUtilisateur());
	}

	public void addArticle(Article newArticle, String rue, String codePostal, String ville) throws BusinessException {
		articleManager.addArticle(newArticle, rue, ville, codePostal);

	}

	public void updateArticle(Article updateArticle) throws BusinessException {
		articleManager.updateArticle(updateArticle);
	}

	public void deleteArticle(Article deleteArticle) throws BusinessException {
		articleManager.deleteArticle(deleteArticle);
	}

	private void validerUtilisateur(Utilisateur utilisateur, BusinessException exception) {
		BusinessException businessException = new BusinessException();
		if (utilisateur == null) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_NULL);
		}
		if (utilisateur.getPseudo() == null || utilisateur.getPseudo().trim().length() == 0
				|| utilisateur.getPseudo().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_PSEUDO);
		}
		if (utilisateur.getNom() == null || utilisateur.getNom().trim().length() == 0
				|| utilisateur.getNom().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_NOM);
		}
		if (utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().length() == 0
				|| utilisateur.getPrenom().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_PRENOM);
		}
		if (utilisateur.getEmail() == null || utilisateur.getEmail().trim().length() == 0
				|| utilisateur.getEmail().trim().length() > 50) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_EMAIL);
		}
		if (utilisateur.getTelephone() == null || utilisateur.getTelephone().trim().length() == 0
				|| utilisateur.getTelephone().trim().length() > 15) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_TELEPHONE);
		}
		if (utilisateur.getRue() == null || utilisateur.getRue().trim().length() == 0
				|| utilisateur.getRue().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_RUE);
		}
		if (utilisateur.getCodePostal() == null || utilisateur.getCodePostal().trim().length() == 0
				|| utilisateur.getCodePostal().trim().length() > 10) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_CPO);
		}
		if (utilisateur.getVille() == null || utilisateur.getVille().trim().length() == 0
				|| utilisateur.getVille().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_VILLE);
		}
		if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().trim().length() == 0
				|| utilisateur.getMotDePasse().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_MDP);
		}
	}

}
