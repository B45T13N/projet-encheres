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
<%
	String credit = String.valueOf(request.getAttribute("credit"));	
%>

<h1>Mon Profil</h1>
	<form action="<%=request.getContextPath()%>/servletModifierProfil" method="post">
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
	<input type="text" name="codePostal">
	<b>Ville : </b>
	<input type="text" name="ville">
	<br>
	<b>Mot de passe actuel : </b>
	<input type="text" name="mdpActu">
	<br>
	<b>Nouveau mot de passe : </b>
	<input type="text" name="newMdp">
	<b>Confirmation : </b>
	<input type="text" name="confirmMdp">
	<br>
	<b>Crédit </b>
	<b><%= credit %></b>
	<br>
	<br>
	<input type="submit" value="Enregistrer" id="register">
	</form>
	<input type="submit" value="Supprimer mon compte" id="delete">

</body>
</html>