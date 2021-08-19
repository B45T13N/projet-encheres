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
		<p><a href="<%=request.getContextPath() %>/"> Se connecter </a></p> <!-- Mettre URL page connection -->
	</header>
	<h1>Liste des enchères</h1>
	<br/>
	
	<form>
		<label>Filtres :</label><br/>
		<div>
			<div>
				<input placeholder="Le nom de l'article contient" type="search" id="site-search" name="recherche">
			</div>
			<div>
				<button>Rechercher</button>
			</div>
		</div>	
	</form>
	<br/>
	<div>
		<label for="Selection-categorie">Catégories</label>
		<select class="" name="Categorie" id="Selection-categorie"> <!-- Class à définir -->
				<option value="Toutes">Toutes </option>
				<option value="Informatique">Informatique </option>
				<option value="Ameublement">Ameublement </option>
				<option value="Vêtement">Vêtement </option>
				<option value="Sport_Loisirs">Sport et Loisirs </option>
		</select>		
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