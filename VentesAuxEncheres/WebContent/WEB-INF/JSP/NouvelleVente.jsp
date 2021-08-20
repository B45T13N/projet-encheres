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
	
	<form action="<%=request.getContextPath()%>/NouvelleVente" method="post">
		<label for="nomArticle">Article :</label>
		<input type="text" required="required" name="nomArticle">
		<label for="description">Description :</label>
		<textarea rows="4" cols="3" required="required" name="description"></textarea>
		<label for="categorie">Catégorie</label>
		<select required="required" name="libelle">			
			<option value="informatique">Informatique</option>
			<option value="ameublement">Ameublement</option>
			<option value="vetement">Vêtement</option>
			<option value="sport_loisirs">Sport et loisirs</option>		
		</select>
		<label>Photo de l'article</label>
		<input type="button" value="Uploader">
		<label for="prix">Mise à prix : </label>
		<input required="required" type="number" name="prix">
		<label for="dateDebut">Début de l'enchère</label>
		<input required="required" type="date" name="dateDebut">
		<label for="dateFin">Début de l'enchère</label>
		<input required="required" type="date" name="dateFin">
		
		<div>
		<h4>Retrait</h4>
		<label for="rue">Rue :</label>
		<input required="required" type="text" name="rue">
		<label for="cpo">Code Postal :</label>
		<input required="required" type="text" name="cpo">
		<label for="ville">Ville :</label>
		<input required="required" type="text" name="ville">
		</div>
		
		<input type="submit" value="Enregistrer">
		<a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Annuler"></a>
			
	</form>
	
	
</body>
</html>