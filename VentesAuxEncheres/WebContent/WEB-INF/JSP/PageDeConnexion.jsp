<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
<link href="WEB-INF/template/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Connexion</title>
</head>
<body>
	<header id="entete">			
			<div class="logo correctifPageCo">		
				<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
			</div>
	</header>

	<h1 class="titrePageConnexion">Identification</h1>


	<form class="formulairePageConnexion" action="<%=request.getContextPath()%>/PageDeConnexion" method="post">

		<div class= "champsSaisie">
			<div class="idPageCoParent">
				<label for="pseudo" class="idPageCo">Identifiant :</label>
				<input type="text" name="pseudo" size="30" autofocus="autofocus" class="case"required value="<c:out value="${pseudo}"/>">
			</div>
			<div class="mdpPageCoParent">
				<label for="mdp" class="mdpPageCo">Mot de passe :</label>
			</div>	<input type="password" name="mdp" size="30" required class="case" value="<c:out value="${mdp}"/>" >
		</div>
			<div class="erreurPageCo"><p>${errMdp}</p></div>
		<div class="boutonConnexion">
			<div class="connexionPageCo">
				<input class="btnCo" type="submit" value="Connexion">
			</div>
			<div class="remember">
				<label class="btnRemember" for="save">Se souvenir de moi</label> 
				<input  type="checkbox" name="save" checked="checked" value="active">
			</div>		
			<div class="lienMDPOubliie"> 
				<a href="<%=request.getContextPath()%>/OublieMotDePasse"> Mot de passe oublié</a>
			</div>	
			<div class="creaComptePageCo">
				<a href="<%=request.getContextPath()%>/CreationCompte"> <input type="button" class="btnCreaCompte" value="Créer un compte"> </a>	
			</div>
		</div>
	</form>


	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>




        
       