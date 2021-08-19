<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>


<form action="<%=request.getContextPath()%>/ServletPageConnexion" method="post">
<label for="identifiant">Identifiant :</label>
<input type="text" name="Identifiant" required>
<label for="mdp">Mot de passe :</label>
<input type="text" name="mdp" required>
<input type="submit" value="Connexion">
<input type="checkbox" name="souvenir"> 
<label for="souvenir">Se souvenir de moi</label>
<a href="adresse_MotDePasseOublié">Mot de passe oublié</a>
<a href="adresse_PageCréationCompte"> <input type="button" value="Créer un compte"> </a>
</form>

</body>
</html>