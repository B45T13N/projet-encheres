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
	 * @return
	 * @throws BusinessException
	 * @see org.Encheres.dal.DAO.DAOUtilisateur#update(org.Encheres.bo.Utilisateur)
	 */
	public Utilisateur updateUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int utilisateur) throws BusinessException {

		BusinessException exception = new BusinessException();
		Utilisateur updateUtilisateur = new Utilisateur();
		Utilisateur user = this.selectByNoUtilisateur(utilisateur);

		if (pseudo.equals("")) {
			updateUtilisateur.setPseudo(user.getPseudo());
		} else {
			updateUtilisateur.setPseudo(pseudo);
		}
		if (nom.equals("")) {
			updateUtilisateur.setNom(user.getNom());
		} else {
			updateUtilisateur.setNom(nom);
		}
		if (prenom.equals("")) {
			updateUtilisateur.setPrenom(user.getPrenom());
		} else {
			updateUtilisateur.setPrenom(prenom);
		}
		if (email.equals("")) {
			updateUtilisateur.setEmail(user.getEmail());
		} else {
			updateUtilisateur.setEmail(email);
		}
		if (telephone.equals("")) {
			updateUtilisateur.setTelephone(user.getTelephone());
		} else {
			updateUtilisateur.setTelephone(telephone);
		}
		if (rue.equals("")) {
			updateUtilisateur.setRue(user.getRue());
		} else {
			updateUtilisateur.setRue(rue);
		}
		if (codePostal.equals("")) {
			updateUtilisateur.setCodePostal(user.getCodePostal());
		} else {
			updateUtilisateur.setCodePostal(codePostal);
		}
		if (ville.equals("")) {
			updateUtilisateur.setVille(user.getVille());
		} else {
			updateUtilisateur.setVille(ville);
		}

		updateUtilisateur.setMotDePasse(motDePasse);
		updateUtilisateur.setNoUtilisateur(utilisateur);

		this.validerUtilisateur(updateUtilisateur, exception);
		if (!exception.hasError()) {
			daoUtilisateur.update(updateUtilisateur);
		}
		return updateUtilisateur;
	}

	/**
	 * @param noUtilisateur
	 * @throws BusinessException
	 * @see org.Encheres.dal.DAO.DAOUtilisateur#delete(int)
	 */
	public void deleteUtilisateur(int utilisateur) throws BusinessException {
		articleManager.deleteArticleByNoUser(utilisateur);
		daoUtilisateur.delete(utilisateur);
	}

	public void addArticle(Article newArticle, String rue, String codePostal, String ville) throws BusinessException {
		articleManager.addArticle(newArticle, rue, ville, codePostal);

	}

	public void updateArticle(Article updateArticle) throws BusinessException {
		articleManager.updateArticle(updateArticle);
	}

//	public void deleteArticle(Article deleteArticle) throws BusinessException {
//		articleManager.deleteArticle(deleteArticle);
//	}

	private void validerUtilisateur(Utilisateur utilisateur, BusinessException exception) {
		BusinessException businessException = new BusinessException();
		if (utilisateur == null) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_NULL);
		}
		if (utilisateur.getPseudo().trim().length() == 0 || utilisateur.getPseudo().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_PSEUDO);
		}
		if (utilisateur.getNom().trim().length() == 0 || utilisateur.getNom().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_NOM);
		}
		if (utilisateur.getPrenom().trim().length() == 0 || utilisateur.getPrenom().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_PRENOM);
		}
		if (utilisateur.getEmail().trim().length() == 0 || utilisateur.getEmail().trim().length() > 50) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_EMAIL);
		}
		if (utilisateur.getTelephone().trim().length() == 0 || utilisateur.getTelephone().trim().length() > 15) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_TELEPHONE);
		}
		if (utilisateur.getRue().trim().length() == 0 || utilisateur.getRue().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_RUE);
		}
		if (utilisateur.getCodePostal().trim().length() == 0 || utilisateur.getCodePostal().trim().length() > 10) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_CPO);
		}
		if (utilisateur.getVille().trim().length() == 0 || utilisateur.getVille().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_VILLE);
		}
		if (utilisateur.getMotDePasse().trim().length() == 0 || utilisateur.getMotDePasse().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_ERROR_MDP);
		}
	}

	public Utilisateur selectByNoUtilisateur(int noUtilisateur) throws BusinessException {

		return this.daoUtilisateur.selectByNoUtilisateur(noUtilisateur);

	}

	public Utilisateur selectUtilisateurByEmail(String email) throws BusinessException {

		return this.daoUtilisateur.selectUtilisateurByEmail(email);

	}

	public Utilisateur updatePasswordByEmail(String motDePasse, String email) throws BusinessException {

		return this.daoUtilisateur.updatePasswordByEmail(motDePasse, email);
	}
}
