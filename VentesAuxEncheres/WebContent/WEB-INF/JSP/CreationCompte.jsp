<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création de compte</title>
</head>
<body>

<h1>Mon profil</h1>


<form action="<%=request.getContextPath()%>/ServletCreationCompte" method="post">
	<p>
	<label for="pseudo">Pseudo :</label>
	<input type="text" name="pseudo" required>
	</p>
	<label for="nom">Nom :</label>
	<input type="text" name="nom" required>
	<p>
	<label for="prenom">Prénom :</label>
	<input type="text" name="prenom" required>
	</p>
	<label for="email">Email :</label>
	<input type="text" name="email" required>
	<p>
	<label for="telephone">Téléphone :</label>
	<input type="text" name="telephone" required>
	</p>
	<label for="rue">Rue :</label>
	<input type="text" name="rue" required> 
	<p>
	<label for="codepostal">Code postal :</label>
	<input type="text" name="codepostal" required>
	</p>
	<label for="ville">Ville :</label>
	<input type="text" name="ville" required>
	<p>
	<label for="mdp">Mot de passe :</label>
	<input type="text" name="mdp" required>
	</p>
	<label for="confirmationmdp">Confirmation :</label>
	<input type="text" name="confirmationmdp" required>
	<p>
	<input type="submit" value="Créer">
 	</p>
 	<p>
	<input type="reset" value="Annuler">
	</p>
</form>


<%
	String erreurMDP = (String) request.getAttribute("erreurMDP");
	if(erreurMDP != null) {
	out.println(erreurMDP);
	}
%>


</body>
</html>