<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.Encheres.bo.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mot de Passe Oublié</title>
</head>
<body>
	<header>
		<div class="logo">		
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
		</div>
	</header>
	<form action="<%=request.getContextPath()%>/OublieMotDePasse" method="post">
		<div>
		<h2>Vous avez oublié votre mot de passe?</h2>
		<p>Pas de panique ! Pour réinitialiser votre mot de passe, saisissez l'adresse mail de votre compte ci-dessous</p>
		<h4>
			<label for="email">Adresse mail :</label>
		</h4>
		<input type="email" name="email" size="30" required>
		<input type="submit">
		</div>
	</form>
		<c:if test="${!empty email }">
			<h2>Un email de réinitialisation de mot de passe a était envoyé !</h2>
			<div>
				<p>Un lien pour reinitialiser votre mot de passe a était envoyé a l'adresse ${email} .</p>
				
				<a href="<%=request.getContextPath()%>/ReinitialiserPassword"><input type="button" value="Modifier mon mot de passe"></a>
			</div>
		</c:if>
		
</body>
</html>