<%@page import="org.Encheres.bll.UtilisateurManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier Profil</title>
</head>
<body>
	<h2>Modifier mon profil</h2>
	<form action="<%=request.getContextPath()%>/ServletModifierProfil" method="post">
	<br>
	<b>Pseudo : </b>
	<input type="text" name="Pseudo">
	<b>Nom : </b>
	<input type="text" name="nom">
	<br>
	<b>Prenom : </b>
	<input type="text" name="prenom">
	<b>Email : </b>
	<input type="text" name="email">
	<br>
	<b>Teléphone : </b>
	<input type="text" name="telephone">
	<b>Rue : </b>
	<input type="text" name="rue">
	<br>
	<b>Code Postal :</b>
	<input type="text" name="codepostal">
	<b>Ville : </b>
	<input type="text" name="ville">
	<br>
	<b>Mot de passe actuel : </b>
	<input type="text" name="motDePasse">
	<br>
	<b>Nouveau mot de passe : </b>
	<input type="text" name="newMotDePasse">
	<b>Confirmation : </b>
	<input type="text" name="confirmMdp">
	<br>
	<br>
	<input type="submit" value="Enregistrer" id="register">
	</form>
	<input type="submit" value="Supprimer mon compte" id="delete">

</body>
</html>