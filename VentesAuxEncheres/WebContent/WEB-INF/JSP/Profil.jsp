<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Profil user</title>
</head>
<body>
	<header>
		<h2>ENI-Enchères</h2>
		<div class="logo">		
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
		</div>
	</header>
	<div>
		<p>Pseudo : ${pseudo}</p>
		<div>
			<p>Nom : ${nom}
			<br>
			Prenom : ${prenom}
			<br>
			Email : ${email}
			<br>
			Teléphone : ${telephone}
			<br>
			Rue : ${rue}
			<br>
			Code Postal : ${code_postal}
			<br>
			Ville : ${ville}</p>
			<c:if test="${noUtilisateur == idVendeur}">
				<div>
					<a href="<%=request.getContextPath()%>/ModifierProfil"><input type="button" value="Modifier"></a>
				</div>
			</c:if>
			<c:if test="${admin != false}">
				<div>
					<a href="<%=request.getContextPath()%>/DeleteProfil"><input type="button" value="Supprimer utilisateur"></a>
					<a href="<%=request.getContextPath()%>/DeleteProfil"><input type="button" value="Désactiver utilisateur"></a>
				</div>
			</c:if>
		</div>
	</div>
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>