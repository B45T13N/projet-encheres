<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<link href="WEB-INF/template/css/bootstrap.css" rel='stylesheet' type='text/css' />
<title>Connexion</title>
</head>
<body>


<div>
<a href="<%=request.getContextPath()%>/Accueil"><img alt="Logo application" src="<%=request.getContextPath()%>/Images/logoENI.jpg"></a>
</div>

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
<div class="piedPageConnexion">
<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
</div>
</body>
</html>




        
       