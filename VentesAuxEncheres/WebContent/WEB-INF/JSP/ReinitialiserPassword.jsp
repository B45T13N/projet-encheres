<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.Encheres.bo.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Email Confirmer</title>
</head>
<body>
	<header id="entete">
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
	</header>
	<form action="<%=request.getContextPath()%>/ReinitialiserPassword" method="post">
		<div class="reinitPass">
			<h3>Email :</h3> 
			<input type="text" name="email" required>
			<h3>Nouveau mot de passe : </h3>
			<input type="text" name="newPassword" required>
			<h3>Confirmer nouveau mot de passe : </h3>
			<input type="text" name="confirmPassword" required>
			<br>
			<p><input type="submit" class="redirectButton"></p>
		</div>
	</form>
	<c:if test="${!empty email && empty erreurEmail }">
		<div class="reinitRedirect">
			<p>Redirection vers la page de connexion</p>
			<a style="text-decoration: none;" href="<%=request.getContextPath()%>/PageDeConnexion"><input type="submit" value="Redirection" class="redirectButton"></a>
		</div>
	</c:if>
	<c:if test="${!empty erreurEmail }">
		<div class="reinitFail">
			<p>${erreurEmail } ou mots de passes invalides. Veuillez reessayer.</p>
		</div>
	</c:if>
</body>
</html>