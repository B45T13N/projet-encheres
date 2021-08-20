<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
</head>
<body>
	<header>Mon profil</header>
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
	<b>Pseudo :</b>
	<%=pseudo%>

	<b>Nom :</b>
	<%= nom %>

	<b>Prenom :</b>
	<%= prenom %>

	<b>Email :</b>
	<%= email %>

	<b>Teléphone :</b>
	<%= telephone %>

	<b>Rue :</b>
	<%= rue %>
	
	<b>Code Postal :</b>
	<%= codePostal %>
	
	<b>Ville :</b>
	<%= ville %>

	<c:if test ="#">
	<a href="<%=request.getContextPath()%>/ModifierProfil"><input type="button" value="Modifier"></a>
	</c:if>
	
</body>
</html>