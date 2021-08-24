<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="org.Encheres.bo.Article" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<!-- <link href="WEB-INF/template/css/bootstrap.css" rel='stylesheet' type='text/css' /> -->
<title>Accueil</title>
</head>
<body>
	<header id="entete">
		<div class="logo">		
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
		</div>
		<div class="btnGroupLeft">
			<c:if test="${empty sessionScope.id}">
			<p><a class="btnLeft" href="<%=request.getContextPath()%>/PageDeConnexion">S'inscrire - Se connecter</a></p>
			</c:if>
			<c:if test="${!empty sessionScope.id}">
			<a class="btnLeft" href="${pageContext.request.contextPath}/Deconnecte">Déconnexion</a>
			<a class="btnLeft" href="<c:out value="${pageContext.request.contextPath}"/>/MonProfil">Mon profil</a>	
			<a class="btnLeft" href="<c:out value="${pageContext.request.contextPath}"/>/NouvelleVente">Vendre un article</a>
			<a class="btnLeft" href="<c:out value="${pageContext.request.contextPath}"/>/NouvelleVente">Enchères</a>		

			</c:if>
		</div>
	</header>
	<h1 class="titreH1">Liste des enchères</h1>
	<br/>
	
	<div class="filtre">	
		<h3>Filtres :</h3>
		<br/>
		<div>
			<form action="<%=request.getContextPath()%>/Accueil" method="POST"> <!-- Method="GET" ???? -->
				<input type="search" placeholder=" Le nom de l'article contient" size="30" class="barreRch">
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
		        <input type="submit" name="btnValider" value="Rechercher" id="btnRech">

	<c:if test="${!empty sessionScope.id}"> 

		<div>
			<input type="radio" id="achats" name="radioBtn" value="1"/>
			<c:if test="${filtreAchat == \"1\"}">checked</c:if>
			<label for="achats">Achats</label>
				<ul>
					<li>
						<input type="checkbox" id="encheresEnCours" name="enCours" value="encheresOuvertes"/>
							<c:if test="${radio == \"radioBtn\"}">disabled</c:if>
							<c:if test="${enCours !=null}">checked</c:if>
							<label for="enCours">Enchères ouvertes</label>
					</li>
					<li>						
						<input type="checkbox" id="encheresEnCours" name="mesEnCours" value="esEncheresEnCours"/>
							<c:if test="${radio == \"radioBtn\"}">disabled</c:if>
							<c:if test="${mesEnCours !=null}">checked</c:if>
							<label for="mesEnCours">Mes enchères en cours</label>
					</li>
					<li>
						<input type="checkbox" id="encheresEnCours" name="remportes" value="mesEncheresRemportees"/>
						<c:if test="${radio == \"radioBtn\"}">disabled</c:if>
						<c:if test="${remportes !=null}">checked</c:if>
							<label for="remportes">Mes enchères remportées</label>
					</li>
				</ul>
		</div>
		
		<div>
			<input type="radio" id="ventes" name="radioBtn" value="2"/>
			<c:if test="${filtreVente == \"2\"}">checked</c:if>
			<label for="ventes">Mes ventes</label>
			<ul>
					<li>
						<input type="checkbox" id="ventesEnCours" name="venteEnCours" value="mesVentesEnCours"/>
							<c:if test="${radio == \"radioBtn\"}">disabled</c:if>
							<c:if test="${venteEnCours !=null}">checked</c:if>
							<label class="txtCheckbox" for="filtreCheckbox">Mes ventes en cours</label>
					</li>
					
					<li>
						<input type="checkbox" id="encheresEnCours" name="venteNonDebut" value="encheresNonDebutees"/>
							<c:if test="${radio == \"radioBtn\"}">disabled</c:if>
							<c:if test="${venteNonDebut !=null}">checked</c:if>
							<label class="txtCheckbox" for="filtreCheckbox">Enchères non débutées</label>
							
					</li>
					
					<li>
						<input type="checkbox" id="encheresEnCours" name="venteTerminee" value="ventesTerminees"/>
							<c:if test="${radio == \"radioBtn\"}">disabled</c:if>
							<c:if test="${venteTerminee !=null}">checked</c:if>
							<label class="txtCheckbox" for="filtreCheckbox">Ventes terminées</label>
					</li>
				</ul>
		</div>
		
	</c:if>
	</form>
		</div>
		</div>
	<br/>
	<section class="secArticle">
	 <c:forEach var="article" items="${listeAAfficher}">
	 
		<article class="cardVente">
			<div class="imageVente">
				<a href="<%=request.getContextPath()%>/DetailVente?noArticle=${article.getNoArticle()}">
					<img src="Images/chaussuredroite.jpg" alt="Descriptif de l'image pour mal voyant"> <!-- URL image à faire ainsi que les ALT -->
				</a>
			</div>
			<div class="detailVente">
				<h4>${article.getNomArticle()}</h4>
				<p>Prix : ${article.getPrixVente()} points</p>
				<p>Fin de l'enchère : ${article.getDateFinEncheres()}</p>
				<c:if test="${!empty sessionScope.id}"><p>Vendeur : <a href="<%=request.getContextPath()%>/Profil?idVendeur=${article.getNoUtilisateur()}">${article.getPseudoUser()}</a></p></c:if>
				<c:if test="${empty sessionScope.id}"><p>Vendeur : ${article.getPseudoUser()}</p></c:if>
			</div>
		
		</article>
	
	 </c:forEach>
	</section>
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>