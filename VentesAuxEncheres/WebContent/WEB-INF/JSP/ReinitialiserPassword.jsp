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
	<form action="<%=request.getContextPath()%>/ReinitialiserPassword" method="post">
		Email : 
		<input type="text" name="email" required>
		Nouveau mot de passe : 
		<input type="text" name="newPassword" required>
		Confirmer le nouveau mot de passe : 
		<input type="text" name="confirmPassword" required>
		<input type="submit">
	</form>
	<c:if test="${!empty email && empty erreurEmail }">
		<a href="<%=request.getContextPath()%>/PageDeConnexion"><input type="submit" value="Confirmer"></a>
	</c:if>
	<c:if test="${!empty erreurEmail }">
		<div>
			<p>${erreurEmail } ou confirmer mot de passe invalides. Veuillez reessayer.</p>
		</div>
	</c:if>
</body>
</html>