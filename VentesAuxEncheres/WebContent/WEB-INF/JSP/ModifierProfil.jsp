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
			<h2>ENI-Enchères</h2>
			<div class="logo">		
				<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
			</div>
	</header>
	<h2>Modifier mon profil</h2>
	<form action="<%=request.getContextPath()%>/ModifierProfil" method="post">
		<br>
		<label for="pseudo">Pseudo : </label>
		<input type="text" name="pseudo">
		<label for="nom">Nom : </label>
		<input type="text" name="nom">
		<br>
		<label for="prenom">Prenom :</label>
		<input type="text" name="prenom">
		<label for="email">Email :</label>
		<input type="text" name="email">
		<br>
		<label for="telephone">Teléphone :</label>
		<input type="text" name="telephone">
		<label for="rue">Rue :</label>
		<input type="text" name="rue">
		<br>
		<label for="code_postal">Code Postal :</label>
		<input type="text" name="code_postal">
		<label for="ville">Ville :</label>
		<input type="text" name="ville">
		<br>
		<label for="motDePasse">Mot de passe actuel : </label>
		<input type="password" name="motDePasse" required>
		<br>
		<label for="newMotDePasse">Nouveau mot de passe : </label>
		<input type="password" name="newMotDePasse">
		<label for="confirmMotDePasse">Confirmation : </label>
		<input type="password" name="confirmMotDePasse">
		<br>
		<br>
		<input type="submit" value="Enregistrer" id="register">
	</form>
	<form action="<%=request.getContextPath()%>/DeleteProfil" method="post">
		<input type="submit" value="Supprimer mon compte" id="delete">
	</form>
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>

</body>
</html>