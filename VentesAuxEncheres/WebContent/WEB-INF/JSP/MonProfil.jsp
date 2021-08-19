<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
</head>
<body>
<%
	String pseudo = String.valueOf(application.getAttribute("pseudo"));
	String nom = request.getParameter("nom");
	String prenom = request.getParameter("prenom");
	String email = request.getParameter("email");
	String telephone = request.getParameter("telephone");
	String rue = request.getParameter("rue");
	String codePostal = request.getParameter("code_postal");
	String ville = request.getParameter("ville");
%>

	<h1>Mon Profil</h1>
	<br>
	<b>Pseudo :</b>
	<%= pseudo %>
	<br>
	<b>Nom :</b>
	<%= nom %>
	<br>
	<b>Prenom :</b>
	<%= prenom %>
	<br>
	<b>Email :</b>
	<%= email %>
	<br>
	<b>Teléphone :</b>
	<%= telephone %>
	<br>
	<b>Rue :</b>
	<%= rue %>
	<br>
	<b>Code Postal :</b>
	<%= codePostal %>
	<br>
	<b>Ville :</b>
	<%= ville %>
	<br>
	<br>
	
	<input type="submit" value="Modifier">
	
</body>
</html>