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
		<p><a href="<%=request.getContextPath() %>/ServletPageDeConnexion"> Se connecter </a></p>
	</header>
	<h1>Liste des enchères</h1>
	<br/>
	
	<div>	
		<h4>Filtres :</h4>
		<br/>
		<div>
			<form action="<%=request.getContextPath() %>/ServletAccueil" method="POST">
				<input type="search" placeholder="Le nom de l'article contient">
				<br/>
				<br/>
				<label for="categorie">Catégorie : </label>
				<select name="categorie" id="categorie" > <!-- multiple="multiple" pour selection plusieurs choix, voir si recu par getParameterValues(String) -->
				
				    <option value="">Toutes</option>
				    <option value="informatique">Informatique</option>
				    <option value="ameublement">Ameublement</option>
				    <option value="vetement">Vêtement</option>
				    <option value="sport_loisirs">Sports et Loisirs</option>
				</select>
		        <input type="submit" name="btnValider" value="Rechercher">
			</form>	
		</div>
	</div>
	
	<form action="<%=request.getContextPath() %>/ServletAccueil">
		<div>
			<input type="radio" id="achats" name="filtre" value="1"/>
			<label for="achats">Achats</label>
				<ul>
					<li>
						<label for="filtreCheckBox">
							<input type="checkbox" id="encheresEnCours" value="1"/>Enchères en cours</label>
					</li>
					<li>
						<label for="filtreCheckBox">
							<input type="checkbox" id="encheresEnCours" value="2"/>Mes enchères en cours</label>
					</li>
					<li>
						<label for="filtreCheckBox">
							<input type="checkbox" id="encheresEnCours" value="3"/>Mes enchères remportées</label>
					</li>
				</ul>
		</div>
		
		<div>
			<input type="radio" id="ventes" name="filtre" value="2"/>
			<label for="ventes">Mes ventes</label>
			<ul>
					<li>
						<label class="txtCheckbox" for="filtreCheckbox">
						<input type="checkbox" id="ventesEnCours" value="1"/>Mes ventes en cours</label>
					</li>
					
					<li>
						<label class="txtCheckbox" for="filtreCheckbox">
						<input type="checkbox" id="encheresEnCours" value="2"/>Enchères non débutées</label>
					</li>
					
					<li>
						<label class="txtCheckbox" for="filtreCheckbox">
						<input type="checkbox" id="encheresEnCours" value="3"/>Ventes terminées</label>
					</li>
				</ul>
		</div>
	</form>
	
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