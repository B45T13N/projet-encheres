<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création de compte</title>
</head>
<body>




<table>
<caption>Mon profil</caption>



<form action="<%=request.getContextPath()%>/ServletCreationCompte" method="post">
<tr>
	<td>
	<label for="pseudo">Pseudo :</label>
	<input type="text" name="pseudo" required>
	</td>
	<td><label for="nom">Nom :</label>
	<input type="text" name="nom" required>
	</td>
</tr>
<tr>
	<td>
<label for="prenom">Prénom :</label>
<input type="text" name="prenom" required>
	</td>
	<td>
<label for="email">Email :</label>
<input type="text" name="email" required>
	</td>
</tr>
<tr>
	<td>
<label for="telephone">Téléphone :</label>
<input type="text" name="telephone" required>
	</td>
	<td>
<label for="rue">Rue :</label>
<input type="text" name="rue" required> 
	</td>
</tr>
<tr>
	<td>
<label for="codepostal">Code postal :</label>
<input type="text" name="codepostal" required>
	</td>
	<td>
<label for="ville">Ville :</label>
<input type="text" name="ville" required>
	</td>
</tr>
<tr>
	<td>
<label for="mdp">Mot de passe :</label>
<input type="text" name="mdp" required>
	</td>
	<td>
<label for="confirmationmdp">Confirmation :</label>
<input type="text" name="confirmationmdp" required>
	</td>
</tr>
<tr>
	<td>
<input type="submit" value="Créer">
 	</td>
 	<td>
<input type="reset" value="Annuler">
	</td>
</tr>

</form>
</table>




</body>
</html>