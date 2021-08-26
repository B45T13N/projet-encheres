<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style2.css">
<title>Profil user</title>
</head>
<body>
	<header id="entete">
				
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
		
	</header>
	<section class="containerSection">
			<span class="containerProfil">
				<h3 class="lineProfil">Pseudo : </h3>
				<h3 class="txtProfilDroite">${pseudo}</h3>
			</span>
			
			<span class="containerProfil">
				<h3 class="lineProfil">Nom : </h3>
				<h3 class="txtProfilDroite">${nom}</h3>
			</span>
			
			<span class="containerProfil">
				<h3 class="lineProfil">Prenom : </h3>
				<h3 class="txtProfilDroite">${prenom}</h3>
			</span>
			
			<span class="containerProfil">
				<h3 class="lineProfil">Email : </h3>
				<h3 class="txtProfilDroite">${email}</h3>
			</span>
			
			<span class="containerProfil">
				<h3 class="lineProfil">Teléphone : </h3>
				<h3 class="txtProfilDroite">${telephone}</h3>
			</span>
			
			<span class="containerProfil">
				<h3 class="lineProfil">Rue : </h3>
				<h3 class="txtProfilDroite">${rue}</h3>
			</span>
			
			<span class="containerProfil">
				<h3 class="lineProfil">Code Postal : </h3>
				<h3 class="txtProfilDroite">${code_postal}</h3>
			</span>
			
			<span class="containerProfil">
				<h3 class="lineProfil">Ville : </h3>
				<h3 class="txtProfilDroite">${ville}</h3>
			</span>
			
			<c:if test="${noUtilisateur == idVendeur}">
				<div class="containerBtnProfil">
					<a href="<%=request.getContextPath()%>/ModifierProfil"><input type="button" value="Modifier" id="btnRech"></a>
				</div>
			</c:if>
			<c:if test="${admin != false}">
				<div>
					<a href="<%=request.getContextPath()%>/DeleteProfil"><input type="button" value="Supprimer utilisateur"></a>
					<a href="<%=request.getContextPath()%>/DeleteProfil"><input type="button" value="Désactiver utilisateur"></a>
				</div>
			</c:if>
		</section>
	
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>