<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
</head>
<body>
	
	
	<h2>Nouvelle vente</h2>
	
	<form action="post">
		<label for="nomArticle">Article :</label>
		<textarea rows="1" cols="3" name="nomArticle"></textarea>
		<label for="description">Description :</label>
		<textarea rows="4" cols="3" name="description"></textarea>
		<label for="libelle">Catégorie</label>
		<select name="libelle">
			<option value="informatique">Informatique</option>
			<option value="ameublement">Ameublement</option>
			<option value="vetement">Vêtement</option>
			<option value="sport_loisirs">Sport et loisirs</option>		
		</select>
		<label>Photo de l'article</label>
		<input type="button" value="Uploader">
		<label for="prix">Mise à prix : </label>
		<input type="number" name="prix">
		<label for="dateDebut">Début de l'enchère</label>
		<input type="date" name="dateDebut">
		<label for="dateFin">Début de l'enchère</label>
		<input type="date" name="dateFin">
		
		<div>
		<h4>Retrait</h4>
		<label for="rue">Rue :</label>
		<textarea rows="1" cols="3" name="rue"></textarea>
		<label for="cpo">Code Postal :</label>
		<textarea rows="1" cols="3" name="rue"></textarea>
		<label for="ville">Ville :</label>
		<textarea rows="1" cols="3" name="rue"></textarea>
		</div>
		
		<input type="submit" value="Enregistrer">
		<a href="<%=request.getContextPath()%>/accueil"><input type="button" value="Annuler"></a>
			
	</form>
	
	
</body>
</html>