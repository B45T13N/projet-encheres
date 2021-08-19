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
	String pseudo = String.valueOf(request.getAttribute("pseudo"));
	String nom = String.valueOf(request.getAttribute("nom"));
	String prenom = String.valueOf(request.getAttribute("prenom"));
	String email = String.valueOf(request.getAttribute("email"));
	String telephone = String.valueOf(request.getAttribute("telephone"));
	String rue = String.valueOf(request.getAttribute("rue"));
	String codePostal = String.valueOf(request.getAttribute("code_postal"));
	String ville = String.valueOf(request.getAttribute("ville"));
%>

	<h1>Mon Profil</h1>
	<br>
	<b>Pseudo :</b>
	<%=pseudo%>
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
	
	<a href="/WEB-INF/JSP/ModifierProfil.jsp"><input type="submit" value="Modifier"></a>
	
	
</body>
</html>