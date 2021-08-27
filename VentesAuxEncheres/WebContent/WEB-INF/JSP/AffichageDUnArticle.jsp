<h3 class="colonne1">${currentArticle.getNomArticle()}</h3>
<img src="<%=request.getContextPath()%>/Images/${currentArticle.getUrlPhoto()}" alt="Descriptif de l'image pour mal voyant"  class="imgArticle">

	<p class="colonne1">	
		Description : 	
		<span class="colonne2">${currentArticle.getDescription()}</span>
	</p>
	<p class="colonne1">
		Catégorie : <span class="colonne2">${currentArticle.getlibelle()}</span>
	</p>
	<p class="colonne1">
		Meilleure offre : <span class="colonne2">${currentArticle.getPrixVente()}</span>
	</p>
	<p class="colonne1">
		Mise à prix : <span class="colonne2">${currentArticle.getMiseAPrix()}</span>
	</p>
	<p class="colonne1">
		Fin de l'enchère : <span class="colonne2">${currentArticle.getDateFinEncheres()}</span>
	</p>
	<p class="colonne1">
		Retrait : <span class="colonne2">${currentArticle.getLieuRetrait()}</span>
	</p>
	<p class="colonne1">
		Vendeur : <span class="colonne2"><a href="<%=request.getContextPath()%>/Profil?idVendeur=${seller.getNoUtilisateur()}">${seller.getPseudo()}</a></span>
	</p>