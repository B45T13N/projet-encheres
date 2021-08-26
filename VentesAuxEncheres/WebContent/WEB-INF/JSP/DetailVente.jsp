<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.Encheres.bo.Utilisateur" %>
<%@page import="org.Encheres.bo.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Détail de la vente</title>
</head>
<body>
	<header>
		<h1>ENI-Enchères</h1>
		<div class="logo">		
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
		</div>
	</header>	
	<c:set var="dateJour" value="${LocalDate.now()}"/>
	<c:choose>
	<c:when test="${currentArticle.getDateFinEncheres().isAfter(dateJour)}">
		<h3>Détail vente</h3>
		<div class="container">
			
	        <%@include file="AffichageDUnArticle.jsp" %>
			<c:if test="${!empty session.id}">
				<form action="<%=request.getContextPath()%>/DetailVente?noArticle=${noArticle}" method="post">
					<label for="prixEnchere">Ma proposition : </label>
					<input type="number" name="prixEnchere" min="${currentArticle.getPrixVente()}" value="${currentArticle.getPrixVente()}">
					<input type="submit" value="Enchérir">
				</form>	
				<a href="<%=request.getContextPath()%>/Accueil">Retour</a>
			</c:if>
		</div>
	</c:when>
	<c:when test="${seller.getNoUtilisateur() != user.getNoUtilisateur() && user.getNoUtilisateur() == gagnantVente.getNoUtilisateur() && (currentArticle.getDateFinEncheres().isBefore(dateJour) || currentArticle.getDateFinEncheres().isEqual(dateJour))}">
		<h3>Vous avez remporté la vente !</h3>
		<div class="container">	
	        <%@include file="AffichageDUnArticle.jsp" %>
	        <p>Tel : ${seller.getTelephone()}</p>
			<a href="<%=request.getContextPath()%>/Accueil">Retour</a>
		</div>	
	</c:when>
	
	<c:when test="${seller.getNoUtilisateur() == user.getNoUtilisateur() && (currentArticle.getDateFinEncheres().isBefore(dateJour) || currentArticle.getDateFinEncheres().isEqual(dateJour))}">
		<h3>${gagnantVente.getPseudo()} a remporté l'enchère !</h3>
		<div class="container">	
	        <%@include file="AffichageDUnArticle.jsp"%>
	        <a href="<%=request.getContextPath()%>/DeleteVente">Retrait effectué</a>
	        <br/>
			<a href="<%=request.getContextPath()%>/Accueil">Retour</a>
		</div>	
	</c:when>
	
	<c:when test="${seller.getNoUtilisateur() != user.getNoUtilisateur() && user.getNoUtilisateur() != gagnantVente.getNoUtilisateur() && (currentArticle.getDateFinEncheres().isBefore(dateJour) || currentArticle.getDateFinEncheres().isEqual(dateJour))}">
		<h3>${user.getPseudo()} dommage, vous êtes trop lent !</h3>
		<div class="container">	
	        <%@include file="AffichageDUnArticle.jsp"%>
			<a href="<%=request.getContextPath()%>/Accueil">Retour</a>
		</div>	
	</c:when>
	</c:choose>
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>