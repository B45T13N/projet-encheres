<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.Encheres.bo.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<meta charset="UTF-8">
<title>Mot de Passe Oublié</title>
</head>
<body>
	<header id="entete">
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
	</header>
	<form action="<%=request.getContextPath()%>/OublieMotDePasse" method="post">
		<div class="oubliePass">
		<h2>Vous avez oublié votre mot de passe?</h2>
		<p>Pas de panique ! Pour réinitialiser votre mot de passe, saisissez l'adresse mail de votre compte ci-dessous</p>
		<h4>Adresse mail :</h4>
		</div>
		<div class="oublieEmail">
			<input type="email" name="email" size="30" required>
		</div>
		<br>
		<div style="align-item: center">
			<input type="submit" class="oublieButton">
		</div>
	</form>
		<c:if test="${!empty email && empty erreurEmail }">
			<div class="oublieRedirectTitle">
				<h2>Un email de réinitialisation de mot de passe a était envoyé !</h2>
			</div>
			
			<div class="oublieRedirect">
				<p>Un lien pour reinitialiser votre mot de passe a était envoyé a l'adresse ${email}.</p>
			</div>
			<div style="align-item: center">
				<a style="text-decoration: none;" href="<%=request.getContextPath()%>/ReinitialiserPassword"><input type="button" value="Modifier" class="oublieButton"></a>
			</div>
				
			
		</c:if>
		<c:if test="${!empty erreurEmail }">
			<div class="oublieFail">
				<p>${erreurEmail } Veuillez reessayer.</p>
			</div>
		</c:if>
		
</body>
</html>