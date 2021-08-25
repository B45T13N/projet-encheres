<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<link href="WEB-INF/template/css/bootstrap.css" rel='stylesheet' type='text/css' />
<title>Connexion</title>
</head>
<body>
	<header>
			<h2>ENI-Enchères</h2>
			<div class="logo">		
				<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
			</div>
	</header>
<div class="titrePageConnexion">
	<h1>Identification</h1>
</div>

<div class="formulairePageConnexion">
<form action="<%=request.getContextPath()%>/PageDeConnexion" method="post">
	<div class ="formPageConnexion1">
		<p>			
			<label for="identifiant" class="id">Identifiant :</label>
			<input type="text" name="identifiant" class="case"required>
			<label for="mdp" class="mdp">Mot de passe :</label>
			<input type="password" name="mdp" required class="case">
		</p>
		<p>${errMdp}</p>
	</div>
<div class ="formPageConnexion2">
<p>
<input type="submit" value="connexion">
<input type="checkbox" name="souvenir"> 
<label for="souvenir">Se souvenir de moi</label>
</p>
<a href="<%=request.getContextPath()%>/CreationCompte"> <input type="button" value="Créer un compte"> </a>
</div>
</form>
</div>
<footer class="piedPageConnexion">
<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
</footer>
</body>
</html>




        
       