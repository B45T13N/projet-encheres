<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style2.css">
<title>Nouvelle vente</title>
</head>
<body>
	<c:set var="dateJour" value="${LocalDate.now()}"/>
	<header id="entete">
			<div class="logoNouvelleVente">		
				<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
			</div>
	</header>
	
	<form action="<%=request.getContextPath()%>/NouvelleVente" method="post" class="formNouvelleVente" enctype="multipart/form-data">
		<h2 class="nouvelleVenteH2">Nouvelle vente</h2>
		<div>
			<label for="nomArticle">Article :</label>
			<input type="text" required="required" name="nomArticle" size="30" value="${currentArticle.getNomArticle()}">
		</div><br/><br class="hiddenBr"/>
		<div>
			<label for="description">Description :</label>
			<textarea rows="4" cols="28" required="required" name="description"></textarea>
		</div><br/><br class="hiddenBr"/>
		<div>
			<label for="categorie">Catégorie</label>
			<select required="required" name="libelle">			
				<option value="informatique">Informatique</option>
				<option value="ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sport_loisirs">Sport et loisirs</option>		
			</select>
		</div><br/><br class="hiddenBr"/>
		<div>
			<label>Photo de l'article</label>
	            <input type="file" name="multiPartServlet" accept="image/*" multiple
	                   onchange="readFilesAndDisplayPreview(this.files);" />
		</div><br/><br class="hiddenBr"/>
		<div>
			<label for="prix">Mise à prix : </label>
			<input required="required" type="number" name="prix" value="${currentArticle.getMiseAPrix()}">
		</div><br/><br class="hiddenBr"/>
		<div>
			<label for="dateDebut">Début de l'enchère</label>
			<input required="required" type="date" name="dateDebut" value="${currentArticle.getDateDebutEncheres()}">
		</div><br/><br class="hiddenBr"/>
		<div>
			<label for="dateFin">Fin de l'enchère</label>
			<input required="required" type="date" name="dateFin" value="${currentArticle.getDateFinEncheres()}">
		</div><br/><br class="hiddenBr"/>
		<div class="retraitNouvelleVente">
			<h4>Retrait</h4>
			<div>
				<label for="rue">Rue :</label>
				<input required="required" type="text" name="rue" value="${currentUser.getRue()}">
			</div><br/><br class="hiddenBr"/>
			<div>
				<label for="cpo">Code Postal :</label>
				<input required="required" type="text" name="cpo" value="${currentUser.getCodePostal()}">
			</div><br/><br class="hiddenBr"/>
			<label for="ville">Ville :</label>
			<input required="required" type="text" name="ville" value="${currentUser.getVille()}">
		</div>
		<br/><br class="hiddenBr"/>
		
		<span class="blocBtnNouvelleVente">
			<input type="submit" value="Enregistrer" id="btnEnregistrer">
			<a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Annuler" id="btnAnnuler"></a>	
		</span>	
		
	</form>
	<c:if test="${currentArticle.getDateFinEncheres().isAfter(dateJour)}">
		<a href="<%=request.getContextPath()%>/DeleteVente" type="button" id="btnSupp">
			Supprimer ma vente
		</a>
	</c:if>
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>