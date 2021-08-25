<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Nouvelle vente</title>
</head>
<body>
	<c:set var="dateJour" value="${LocalDate.now()}"/>
	<header>
			
			<div class="logo">		
				<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
			</div>
	</header>
	<h2>Nouvelle vente</h2>
	<form action="<%=request.getContextPath()%>/NouvelleVente" method="post">
		<div>
			<label for="nomArticle">Article :</label>
			<input type="text" required="required" name="nomArticle" value="${currentArticle.getNomArticle()}">
		</div>
		<div>
			<label for="description">Description :</label>
			<textarea rows="4" cols="3" required="required" name="description"></textarea>
		</div>
		<div>
			<label for="categorie">Catégorie</label>
			<select required="required" name="libelle">			
				<option value="informatique">Informatique</option>
				<option value="ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sport_loisirs">Sport et loisirs</option>		
			</select>
		</div>
		<div>
			<label>Photo de l'article</label>
			<input type="button" value="Uploader">
		</div>
		<div>
			<label for="prix">Mise à prix : </label>
			<input required="required" type="number" name="prix" value="${currentArticle.getMiseAPrix()}">
		</div>
		<div>
			<label for="dateDebut">Début de l'enchère</label>
			<input required="required" type="date" name="dateDebut" value="${currentArticle.getDateDebutEncheres()}">
		</div>
		<div>
			<label for="dateFin">Fin de l'enchère</label>
			<input required="required" type="date" name="dateFin" value="${currentArticle.getDateFinEncheres()}">
		</div>
		<div>
			<h4>Retrait</h4>
			<label for="rue">Rue :</label>
			<input required="required" type="text" name="rue" value="${currentUser.getRue()}">
			<div>
				<label for="cpo">Code Postal :</label>
				<input required="required" type="text" name="cpo" value="${currentUser.getCodePostal()}">
			</div>
			<label for="ville">Ville :</label>
			<input required="required" type="text" name="ville" value="${currentUser.getVille()}">
		</div>
		
		<input type="submit" value="Enregistrer">
		<a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Annuler"></a>		
	</form>
	<c:if test="${currentArticle.getDateFinEncheres().isAfter(dateJour)}">
		<form action="<%=request.getContextPath()%>/DeleteVente" method="post">
			<input type="submit" value="Supprimer ma vente" id="delete">
		</form>
	</c:if>
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>