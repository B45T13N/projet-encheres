<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Création de compte</title>
</head>
<body>
	<div class="titreCreationCompte">
		<h1>Mon profil</h1>
	</div>
<div class="formaulaireCreationCompte">
<form action="<%=request.getContextPath()%>/CreationCompte" method="post">
<div class="formCreaCompte">
	<div class="coteDroit">
		<p>
			<label for="pseudo">Pseudo :</label>
			<input type="text" name="pseudo" size="30" required>
		</p>
		<p>
			<label for="prenom">Prénom :</label>
			<input type="text" name="prenom"  size="30" required>
		</p>
		<p>
			<label for="telephone">Téléphone :</label>
			<input type="text" name="telephone" size="30" required>
		</p>
		<p>
			<label for="codepostal">Code postal :</label>
			<input type="text" name="codepostal" size="30" required>
		</p>
		<p>
			<label for="mdp">Mot de passe :</label>
			<input type="password" name="mdp" size="30" required>
		</p>
	</div>
	<div class="coteGauche">
		<p>
			<label for="nom">Nom :</label>
			<input type="text" name="nom" size="30" required>
		</p>
		<p>
			<label for="email">Email :</label>
			<input type="email" name="email" size="30" required>
		</p>
			<label for="rue">Rue :</label>
			<input type="text" name="rue" size="30" required> 
		<p>
			<label for="ville">Ville :</label>
			<input type="text" name="ville" size="30" required>
		</p>
		<p>
			<label for="confirmationmdp">Confirmation :</label>
			<input type="password" name="confirmationmdp" size="30"  required>
		</p>
	</div>
</div>
	<p>
	<input type="submit" value="Créer">
 	</p>
 	<p>
	<a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Annuler"></a>
	</p>
</form>
</div>

<% String erreurMDP = (String) request.getAttribute("erreurMDP");
if(erreurMDP != null) { 	out.println(erreurMDP);
}
%>


</body>
</html>