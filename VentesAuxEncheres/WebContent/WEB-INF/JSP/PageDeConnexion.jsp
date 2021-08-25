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
	<header id="entete">			
			<div class="logo">		
				<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
			</div>
	</header>

	<h1 class="titreH1">Identification</h1>


	<form class="formulairePageConnexion" action="<%=request.getContextPath()%>/PageDeConnexion" method="post">

		<div class="champsSaisie">			
			<label for="identifiant" class="id">Identifiant :</label>
			<input type="text" name="identifiant" autofocus="autofocus" class="case"required>
		</div>
		<div class="champsSaisie">
			<label for="mdp" class="mdp">Mot de passe :</label>
			<input type="password" name="mdp" required class="case">
		</div>
		<p>${errMdp}</p>
		<div>
			<div>
				<input class="btn" type="submit" value="connexion">
			</div>
			<div>
				<label class="btn" for="save">Se souvenir de moi</label> 
				<input  type="checkbox" name="save">
			</div>			
		</div>
		<a href="<%=request.getContextPath()%>/CreationCompte"> <input type="button" class="btn" value="Créer un compte"> </a>

	</form>


	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>




        
       