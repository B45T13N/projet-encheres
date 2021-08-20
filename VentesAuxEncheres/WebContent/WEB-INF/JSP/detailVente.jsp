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
	
	<c:if test="${currentArticle.getDateFinEnchere() > LocalDate.now()}">
		<h3>Détail vente</h3>
		<div class="container">
			
	        <%@include file="AffichageDUnArticle.jsp" %>
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
	</c:if>
	<c:if test="${currentArticle.getDateFinEnchere() <= LocalDate.now() && seller.getNoUtilisateur() != user.getNoUtilisateur() &&  }">
		<h3>Vous avez remporté la vente !</h3>
		<div class="container">	
	        <%@include file="AffichageDUnArticle.jsp" %>
	        <p>Tel : ${seller.getTelelephone()}</p>
			<c:if test="${!empty session.getAttribute(\"utilisateur\")}">
				<a href="<%=request.getContextPath()%>/ServletAccueil">Retour</a>
			</c:if>
		</div>	
	</c:if>
	
	<c:if test="${currentArticle.getDateFinEnchere() <= LocalDate.now() && seller.getNoUtilisateur() != user.getNoUtilisateur() }">
		<h3>Vente terminée !</h3>
		<div class="container">	
	        <%@include file="AffichageDUnArticle.jsp" %>
	        <p>Tel : ${seller.getTelelephone()}</p>
			<c:if test="${!empty session.getAttribute(\"utilisateur\")}">
				<a href="<%=request.getContextPath()%>/ServletAccueil">Retour</a>
			</c:if>
		</div>	
	</c:if>
	
</body>
</html>