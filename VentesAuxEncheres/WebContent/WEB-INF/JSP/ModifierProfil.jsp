
<%@page import="org.Encheres.bll.UtilisateurManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="icon" href="Images/IconeENI.jpg">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
<title>Modifier Profil</title>
</head>
<body>
	<header>
		<div id="entete">		
			<%@ include file="/WEB-INF/JSP/LogoAccueil.jsp" %>
		</div>
	</header>
	<h2 class="titreModif">Modifier mon profil</h2>
	<form action="<%=request.getContextPath()%>/ModifierProfil" method="post">
           <div style="display: flex; flex-direction : column;">
                <div class="formulaireModifierProfil">
                    <p class="formulairepModifProfil">Pseudo : 
                    <input type="text" name="pseudo" class="caseFormModifProfil"></p>
                    <p class="formulairepModifProfil">Nom : 
                    <input type="text" name="nom" class="caseFormModifProfil"></p>
                </div>
                <div class="formulaireModifierProfil">
                    <p class="formulairepModifProfil">Prenom :
                    <input type="text" name="prenom" class="caseFormModifProfil"> </p>
                    <p class="formulairepModifProfil">Email : 
                    <input type="text" name="email" class="caseFormModifProfil"> </p>
                </div> 
                <div class="formulaireModifierProfil" class="caseFormModifProfil">
                    <p class="formulairepModifProfil">Téléphone :
                    <input type="text" name="telephone" class="caseFormModifProfil"> </p>
                    <p class="formulairepModifProfil">Rue :
                    <input type="text" name="rue" class="caseFormModifProfil" > </p>
                </div> 
                <div class="formulaireModifierProfil">
                    <p class="formulairepModifProfil">Code Postal : 
                    <input type="text" name="code_postal" class="caseFormModifProfil"></p>
                    <p class="formulairepModifProfil">Ville : 
                    <input type="text" name="ville" class="caseFormModifProfil"></p>
                </div> 
                
               <div class="mdpModif">
                <div class="formulaireModifierProfil">
                    <p class="formulairepModifProfil correctifTexte">Mot de passe actuel : 
                    <input type="password" name="motDePasse" required class="caseFormModifProfil correctifSolo"></p>
                </div>
                <div class="formulaireModifierProfil">
                    <p class="formulairepModifProfil">Nouveau mot de passe :
                    <input type="password" name="newMotDePasse" class="caseFormModifProfil">  </p>
                    <p class="formulairepModifProfil">Confirmation :
                    <input type="password" name="confirmMotDePasse"class="caseFormModifProfil">  </p>
                </div>
               </div>
		</div>
		<div class="btnParentEnregistrerProfil">
		<input type="submit" value="Enregistrer" id="register" class="btnEnregistrerProfil">
		</div>
	</form>
	<form action="<%=request.getContextPath()%>/DeleteProfil" method="post">
	<div class="btnParentSupprimerCompte">
		<input type="submit" value="Supprimer mon compte" id="delete" class="btnSupprimerCompte">
	</div>
	</form>

	<footer class="piedPageConnexion">
		<%@ include file="/WEB-INF/JSP/PiedDePage.jsp" %>
	</footer>

</body>
</html>
