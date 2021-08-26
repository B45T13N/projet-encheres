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
   <link rel="icon" href="Images/IconeENI.jpg">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<!-- <link href="WEB-INF/template/css/bootstrap.css" rel='stylesheet' type='text/css' /> -->


<title>ENI Enchères Accueil</title>
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
			<a class="btnLeft" href="<c:out value="${pageContext.request.contextPath}"/>/Profil">Mon profil</a>	
			<a class="btnLeft" href="<c:out value="${pageContext.request.contextPath}"/>/NouvelleVente">Vendre un article</a>
			<a class="btnLeft" href="<c:out value="${pageContext.request.contextPath}"/>/NouvelleVente">Enchères</a>		

			</c:if>
		</div>
	</header>
	
	<h1 class="titreH1">Liste des enchères</h1>
<br/>
		
	<h3 class="titreH3">Filtres :</h3>
<br/>
		
			<form action="<%=request.getContextPath()%>/Accueil" method="POST"> <!-- Method="GET" ???? -->
				<input type="search" placeholder=" Le nom de l'article contient" size="50" class="barreRch">
				<br/>
				<br/>
				<div class="containerAllCategorie">
					
						<span class="containerCategorie">
							<label for="categorie" class="texteCategorie">Catégorie:</label>
							
							<select name="categorie" id="categorie" > 
							    <option value="">Toutes</option>
							    <option value="informatique">Informatique</option>
							    <option value="ameublement">Ameublement</option>
							    <option value="vetement">Vêtement</option>
							    <option value="sport_loisirs">Sports et Loisirs</option>
							</select>
						</span>
						<br class="hiddenBr"/>
					        <input type="submit" name="btnValider" value="Rechercher" id="btnRech">
					
		        </div>
	
				<c:if test="${!empty sessionScope.id}"> 
			<br class="hiddenBr"/>
			<div class="containerRadio">
				<div>
					<input type="radio" id="achats" name="radioBtn" value="1"/>
					<c:if test="${filtreAchat == \"1\"}">checked</c:if>
					<label for="achats">Achats</label>
						<ul>
							<li>
								<input type="checkbox" id="encheresEnCours" name="enCours" value="encheresOuvertes"/>
								<label for="enCours">Enchères ouvertes</label>
							</li>
							<li>						
								<input type="checkbox" id="encheresEnCours" name="mesEnCours" value="esEncheresEnCours"/>
								<label for="mesEnCours">Mes enchères en cours</label>
							</li>
							<li>
								<input type="checkbox" id="encheresEnCours" name="remportes" value="mesEncheresRemportees"/>
								<label for="remportes">Mes enchères remportées</label>
							</li>
						</ul>
					</div>
					<br class="hiddenBr"/>
					<div>
						<input type="radio" id="ventes" name="radioBtn" value="2"/>
						<c:if test="${filtreVente == \"2\"}">checked</c:if>
						<label for="ventes">Mes ventes</label>
						<ul>
								<li>
									<input type="checkbox" id="ventesEnCours" name="venteEnCours" value="mesVentesEnCours"/>
			
										<label class="txtCheckbox" for="filtreCheckbox">Mes ventes en cours</label>
								</li>
								
								<li>
									<input type="checkbox" id="encheresEnCours" name="venteNonDebut" value="encheresNonDebutees"/>
			
										<label class="txtCheckbox" for="filtreCheckbox">Enchères non débutées</label>
										
								</li>
								
								<li>
									<input type="checkbox" id="encheresEnCours" name="venteTerminee" value="ventesTerminees"/>
			
										<label class="txtCheckbox" for="filtreCheckbox">Ventes terminées</label>
								</li>
							</ul>
					</div>
				</div>
		
	</c:if>
	</form>
		
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