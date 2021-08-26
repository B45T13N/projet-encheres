<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<p><a>Pseudo : </a>${pseudo}</p>
		<div>
			<p><a>Nom : </a>${nom}
			<br>
			<a>Prenom : </a>${prenom}
			<br>
			<a>Email : </a>${email}
			<br>
			<a>Teléphone : </a>${telephone}
			<br>
			<a>Rue : </a>${rue}
			<br>
			<a>Code Postal : </a>${code_postal}
			<br>
			<a>Ville : </a>${ville}</p>
		</div>
	</div>
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>