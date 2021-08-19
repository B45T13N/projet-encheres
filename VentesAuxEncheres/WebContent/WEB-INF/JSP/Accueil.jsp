<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
	<header>
		<h2>ENI-Enchères</h2>
		<p><a href="<%=request.getContextPath() %>/ServletModifierProfil">S'inscrire - </a></p>
		<p><a href="<%=request.getContextPath() %>/ServletLoggin"> Se connecter </a></p>
	</header>
	<h1>Liste des enchères</h1>
	<br/>
	
	<div>	
		<h4>Filtres :</h4>
		<br/>
		<div>
			<form action="accueil" method="POST">
				<input type="search" placeholder="Le nom de l'article contient">
				<br/>
				<br/>
				<label for="categorie">Catégorie : </label>
				<select name="categorie" id="categorie">
				    <option value="Toutes les catégories">Toutes</option>
				    <option value="Informatique">Informatique</option>
				    <option value="Ameublement">Ameublement</option>
				    <option value="Vetement">Vêtement</option>
				    <option value="Sport_loisir">Sports et Loisirs</option>
				</select>
		        <input type="submit" name="btValider" value="Rechercher">
			</form>	
		</div>
	</div>
	
	<br/>
	<section>
		<article>
			<img alt="Descriptif de l'image pour mal voyant" src="../WebContent/images/#"> <!-- URL image à faire ainsi que les ALT -->
			<div>
				<h4>Nom de l'article en vente</h4>
				<p>Prix : </p>
				<p>Fin de l'enchère : </p>
				<p>Vendeur : </p>
			</div>
		</article>
		<article>
			<img alt="Descriptif de l'image pour mal voyant" src="../WebContent/images/#"> <!-- URL image à faire ainsi que les ALT -->
			<div>
				<h4>Nom de l'article en vente</h4>
				<p>Prix : </p>
				<p>Fin de l'enchère : </p>
				<p>Vendeur : </p>
			</div>
		</article>
	</section>
</body>
</html>