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
<div class="formaulaireCreationCompte">
	<form action="<%=request.getContextPath()%>/CreationCompte" method="post">
		<div style="display: flex; flex-direction : column;">
	        <div class="divZoneCrea">
	        	<p class="classCrea">Pseudo : 
	        	<input type="text" name="pseudo" size="30" required class="caseCrea"></p>
	        	<p class="classCrea">Nom : 
				<input type="text" name="nom" size="30" required class="caseCrea"></p>
	        </div>   
	        <div class="divZoneCrea">
				<p class="classCrea">Prenom : 
				<input type="text" name="prenom"  size="30" required class="caseCrea"></p>
				<p class="classCrea">Email : 
				<input type="email" name="email" size="30" required class="caseCrea"></p>
			</div>
			<div class="divZoneCrea">
				<p class="classCrea">Teléphone : 
				<input type="text" name="telephone" size="30" required class="caseCrea"></p>
				<p class="classCrea">Rue : 
				<input type="text" name="rue" size="30" required class="caseCrea"></p>
			</div>
			<div class="divZoneCrea">
				<p class="classCrea">Code Postal : <input type="text" name="codepostal" size="30" required class="caseCrea"></p>
				<p class="classCrea">Ville : 
				<input type="text" name="ville" size="30" required class="caseCrea"></p>
			</div>
			<div class="divZoneCrea">
				<p class="classCrea">Mot de passe : 
				<input type="password" name="mdp" size="30" required class="caseCrea"></p>
				<p class="classCrea">Confirmation : 
				<input type="password" name="confirmationmdp" size="30"  required class="caseCrea"></p>
			</div>
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