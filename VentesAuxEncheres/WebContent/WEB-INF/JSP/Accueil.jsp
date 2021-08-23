<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="org.Encheres.bo.Article" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
	<header>
		<h2>ENI-Enchères</h2>
		
		<c:if test="${sessionScope.isConnecte == false }">
		<p><a href="<%=request.getContextPath()%>/PageDeConnexion">S'inscrire - Se connecter</a></p>
		</c:if>
		<c:if test="${sessionScope.isConnecte == true }">
		<li><a href="${pageContext.request.contextPath()}/DetailVente">Enchères</a></li>
		<li><a href="${pageContext.request.contextPath()}/NouvelleVente">Vendre un article</a></li>
		<li><a href="${pageContext.request.contextPath()}/MonProfile">Mon profil</a></li>
		<li><a href="${pageContext.request.contextPath()}/Accueil">Déconnexion</a></li>
		</c:if>
	</header>
	<h1>Liste des enchères</h1>
	<br/>
	
	<div>	
		<h4>Filtres :</h4>
		<br/>
		<div>
			<form action="<%=request.getContextPath()%>/Accueil" method="POST"> <!-- Method="GET" ???? -->
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
	
	<form action="<%=request.getContextPath()%>/Accueil">
		<div>
			<input type="radio" id="achats" name="filtreAchat" value="1"/>
			<c:if test="${filtreAchat == \"1\"}">checked</c:if>
			<label for="achats">Achats</label>
				<ul>
					<li>
						<input type="checkbox" id="encheresEnCours" name="enCours" value="1"/>
							<c:if test="${radio == \"filtreVente\"}">disabled</c:if>
							<c:if test="${enCours !=null}">checked</c:if>
							<label for="enCours">Enchères ouvertes</label>
					</li>
					<li>						
						<input type="checkbox" id="encheresEnCours" name="mesEnCours" value="2"/>
							<c:if test="${radio == \"filtreVente\"}">disabled</c:if>
							<c:if test="${mesEnCours !=null}">checked</c:if>
							<label for="mesEnCours">Mes enchères en cours</label>
					</li>
					<li>
						<input type="checkbox" id="encheresEnCours" name="remportes" value="3"/>
						<c:if test="${radio == \"filtreVente\"}">disabled</c:if>
						<c:if test="${remportes !=null}">checked</c:if>
							<label for="remportes">Mes enchères remportées</label>
					</li>
				</ul>
		</div>
		
		<div>
			<input type="radio" id="ventes" name="filtreVente" value="2"/>
			<c:if test="${filtreVente == \"2\"}">checked</c:if>
			<label for="ventes">Mes ventes</label>
			<ul>
					<li>
						<input type="checkbox" id="ventesEnCours" name="venteEnCours" value="1"/>
							<c:if test="${radio == \"filtreAchat\"}">disabled</c:if>
							<c:if test="${venteEnCours !=null}">checked</c:if>
							<label class="txtCheckbox" for="filtreCheckbox">Mes ventes en cours</label>
					</li>
					
					<li>
						<input type="checkbox" id="encheresEnCours" name="venteNonDebut" value="2"/>
							<c:if test="${radio == \"filtreAchat\"}">disabled</c:if>
							<c:if test="${venteNonDebut !=null}">checked</c:if>
							<label class="txtCheckbox" for="filtreCheckbox">Enchères non débutées</label>
							
					</li>
					
					<li>
						<input type="checkbox" id="encheresEnCours" name="venteTerminee" value="3"/>
							<c:if test="${radio == \"filtreAchat\"}">disabled</c:if>
							<c:if test="${venteTerminee !=null}">checked</c:if>
							<label class="txtCheckbox" for="filtreCheckbox">Ventes terminées</label>
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