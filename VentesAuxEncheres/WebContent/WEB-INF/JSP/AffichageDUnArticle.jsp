	<h4>${currentArticle.getNomArticle()}</h4>
	<p>	
		Description : 	
		${currentArticle.getDescription()}
	</p>
	<p>
		Catégorie : ${currentArticle.getlibelle()}
	</p>
	<p>
		Meilleure offre : ${currentArticle.getPrixVente()}
	</p>
	<p>
		Mise à prix : ${currentArticle.getMiseAPrix()}
	</p>
	<p>
		Fin de l'enchère : ${currentArticle.getDateFinEncheres()}
	</p>
	<p>
		Retrait : ${currentArticle.getLieuRetrait()}
	</p>
	<p>
		Vendeur : <a href="<%=request.getContextPath()%>/Profil">${seller.getNom()}</a>
	</p>