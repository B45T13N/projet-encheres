<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.Encheres.bo.Utilisateur" %>
<%@page import="org.Encheres.bo.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail de la vente</title>
</head>
<body>
	<h1>ENI-Enchères</h1>
	<h3>Détail vente</h3>
	
	
	<div class="container">
		<h4>${currentArticle.getNom()}</h4>
		<p>	
			Description : 	
			${currentArticle.getDescrption()}
		</p>
		<p>
			Catégorie : ${currentArticle.getlibelle()}
		</p>
		<p>
			Meilleure offre : ${currentArticle.getPrixVente()}
		</p>
		<p>
			Mise à prix : ${currentArticle.getMiseAPrix()}
		</p>
		<p>
			Fin de l'enchère : ${currentArticle.getDateFinEncheres()}
		</p>
		<p>
			Retrait : ${currentArticle.getLieuRetrait()}
		</p>
		<p>
			Vendeur : ${seller.getNom()}
		</p>
		<c:if test="${!empty session.getAttribute(\"utilisateur\")}">
			<form>
				<label for="prixEnchere">Ma proposition : </label>
				<input type="number" name="prixEnchere" min="${currentArticle.getPrixVente()}" value="${currentArticle.getPrixVente()}">
				<input type="submit" value="Enchérir">
			</form>	
		</c:if>
		<c:if test="${empty session.getAttribute(\"utilisateur\")}">
			<a href="<%=request.getContextPath()%>/ServletAccueil">Retour</a>
		</c:if>
	</div>
	
	
</body>
</html>