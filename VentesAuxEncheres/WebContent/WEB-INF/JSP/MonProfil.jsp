<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.Encheres.bo.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
</head>
<body>
	<header>
		<h2>ENI-Enchères</h2>
	</header>
		<div>
			<p><a>Pseudo :</a><%=pseudo%></p>
			<div>
				<p><a>Nom :</a><%=nom%>
				<br>
				<a>Prenom :</a><%=prenom%>
				<br>
				<a>Email :</a><%=email%>
				<br>
				<a>Teléphone :</a><%=telephone%>
				<br>
				<a>Rue :</a><%=rue%>
				<br>
				<a>Code Postal :</a><%=codePostal%>
				<br>
				<a>Ville :</a><%=ville%></p>
			
				<div>
					<a href="<%=request.getContextPath()%>/ModifierProfil"><input type="button" value="Modifier"></a>
				</div>
			</div>
		</div>
</body>
</html>