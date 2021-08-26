<%@page import="org.Encheres.bll.UtilisateurManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Modifier Profil</title>
</head>
<body>
	<header>
		<div id="entete">		
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
		</div>
	</header>
	<h2 class="titreModif">Modifier mon profil</h2>
	<form action="<%=request.getContextPath()%>/ModifierProfil" method="post">
	<div class="formulaireModifierProfil">
		<div class="coteGaucheModifProfil">
			<p>
				<label for="pseudo">Pseudo : </label>
				<input type="text" name="pseudo" class="caseFormModifProfil">
			</p>
			<p>
				<label for="prenom">Prenom :</label>
				<input type="text" name="prenom" class="caseFormModifProfil">
			</p>
			<p>
				<label for="telephone">Téléphone :</label>
				<input type="text" name="telephone" class="caseFormModifProfil">
			</p>
			<p>
				<label for="code_postal">Code Postal :</label>
				<input type="text" name="code_postal" class="caseFormModifProfil">
			</p>
			<p>
				<label for="motDePasse">Mot de passe actuel : </label>
				<input type="password" name="motDePasse" required class="caseFormModifProfil">
			</p>
			<p>
				<label for="confirmMotDePasse">Confirmation mot de passe : </label>
				<input type="password" name="confirmMotDePasse" class="caseFormModifProfil">
			</p>
		</div>
		<div class="coteDroitModifProfil">
			<p>
				<label for="nom">Nom : </label>
				<input type="text" name="nom" class="caseFormModifProfil" >
			</p>
			<p>
				<label for="email">Email :</label>
				<input type="text" name="email" class="caseFormModifProfil" >
			</p>
			<p>
				<label for="rue">Rue :</label>
				<input type="text" name="rue" class="caseFormModifProfil" >
			</p>
			<p>
				<label for="ville">Ville :</label>
				<input type="text" name="ville" class="caseFormModifProfil" >
			</p>
			<p>
				<label for="newMotDePasse" style="width: 100px; margin-right:50px;">Nouveau mot de passe : </label>
				<input type="password" name="newMotDePasse" class="caseFormModifProfil" >
			</p>
		</div>
	</div>
		<div class="btnParentEnregistrerProfil">
		<input type="submit" value="Enregistrer" id="register" class="btnEnregistrerProfil">
		</div>
	</form>
	<form action="<%=request.getContextPath()%>/DeleteProfil" method="post">
	<div class="btnParentSupprimerCompte">
		<input type="submit" value="Supprimer mon compte" id="delete" class="btnSupprimerCompte">
	</div>
	</form>

	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>

</body>
</html>