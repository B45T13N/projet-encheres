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
<title>Profil user</title>
</head>
<body>
	<header>
		<div id="entete">		
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
		</div>
	</header>
	<div>
		<div class ="blockProfil">
			<h3 class="lineProfil">
				<div>Pseudo :</div>
				<div>${pseudo}</div>
			</h3>
			<h3 class="lineProfil">
				<div>Nom : </div>
				<div>${nom}</div>
			</h3>
			<h3 class="lineProfil">
            	<div>Prenom : </div>
				<div>${prenom}</div>
			</h3>
			<h3 class="lineProfil">
				<div>Email : </div>
				<div>${email}</div>
			</h3>
			<h3 class="lineProfil">
				<div>Teléphone : </div>
				<div>${telephone}</div>
			</h3>
			<h3 class="lineProfil">
				<div>Rue : </div>
				<div>${rue}</div>
			</h3>
			<h3 class="lineProfil">
				<div>Code Postal : </div>
				<div>${code_postal}</div>
			</h3>
			<h3 class="lineProfil">
				<div>Ville : </div>
				<div>${ville}</div>
			</h3>
			<c:if test="${noUtilisateur == idVendeur}">
				<div>
					<a href="<%=request.getContextPath()%>/ModifierProfil"><input type="button" value="Modifier"></a>
				</div>
			</c:if>
			<c:if test="${admin != false}">
				<div>
					<a href="<%=request.getContextPath()%>/DeleteProfil"><input type="button" value="Supprimer utilisateur"></a>
					<a href="<%=request.getContextPath()%>/DeleteProfil"><input type="button" value="Désactiver utilisateur"></a>
				</div>
			</c:if>
		</div>
	</div>
	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>
</body>
</html>