<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Création de compte</title>
</head>
<body>
<header id="entete">			
			<div class="logo">		
				<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
			</div>
	</header>

	<div class="titreCreationCompte">
		<h1>Mon profil</h1>
	</div>
<div class="formulaireCreationCompte">
<form action="<%=request.getContextPath()%>/CreationCompte" method="post">
<div class="formCreaCompte" style ="margin 30px;">
		<p class="PseudoResponsive">
			<label for="pseudo" class="labelFormCreCompte">Pseudo :</label>
			<input type="text" name="pseudo" size="30" required class="caseFormModifProfil">
		</p>
	
		<p class="prenomResponsive">
			<label for="prenom" class="labelFormCreCompte">Prénom :</label>
			<input type="text" name="prenom"  size="30" required class="caseFormModifProfil">
		</p>
		<p class="telephoneResponsive">
			<label for="telephone" class="labelFormCreCompte">Téléphone :</label>
			<input type="text" name="telephone" size="30" required class="case">
		</p>
		<p class="codepostalResponsive">
			<label for="codepostal" class="labelFormCreCompte">Code postal :</label>
			<input type="text" name="codepostal" size="30" required class="case">
		</p>
		<p class="mdpResponsive" >
			<label for="mdp" class="labelFormCreCompte">Mot de passe :</label>
			<input type="password" name="mdp" size="30" required class="case">
		</p>
	

		<p class="nomResponsive" class="labelFormCreCompte">
			<label for="nom" class="labelFormCreCompte">Nom :</label>
			<input type="text" name="nom" size="30" required class="case">
		</p>
		<p class="emailResponsive">
			<label for="email" class="labelFormCreCompte">Email :</label>
			<input type="email" name="email" size="30" required class="case">
		</p>
		<p class="rueResponsive">
			<label for="rue" class="labelFormCreCompte">Rue :</label>
			<input type="text" name="rue" size="30" required class="case" style=""> 
		</p>
		<p class="villeResponsive">
			<label for="ville" class="labelFormCreCompte">Ville :</label>
			<input type="text" name="ville" size="30" required class="case">
		</p>
		<p class="confirmationmdpResponsive">
			<label for="confirmationmdp" class="labelFormCreCompte">Confirmation :</label>
			<input type="password" name="confirmationmdp" size="30"  required class="case">
		</p>
</div>
<div class="containerBtnPageCreCompte">
	<p class="btnPageCreaCompte">
	<input type="submit" value="Créer" class="btnCreate">
 	</p>
 	<p class="btnPageCreaCompte">
	<a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Annuler" class="btnAnnulation"></a>
	</p>
</div>
</form>
</div>
<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>