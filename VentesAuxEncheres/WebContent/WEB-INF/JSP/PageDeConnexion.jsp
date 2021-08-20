<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>


<form action="<%=request.getContextPath()%>/PageDeConnexion" method="post">
<label for="identifiant">Identifiant :</label>
<input type="text" name="identifiant" required>
<label for="mdp">Mot de passe :</label>
<input type="password" name="mdp" required>
<input type="submit" value="connexion">
<input type="checkbox" name="souvenir"> 
<label for="souvenir">Se souvenir de moi</label>
<a href="<%=request.getContextPath()%>/#">Mot de passe oublié</a>
<a href="<%=request.getContextPath()%>/CreationCompte"> <input type="button" value="Créer un compte"> </a>
</form>

</body>
</html>