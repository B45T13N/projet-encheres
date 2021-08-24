<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<h2>ENI-Enchères</h2>
	</header>
	<div>
		<p><a>Pseudo : </a>${pseudo}</p>
		<div>
			<p><a>Nom : </a>${nom}
			<br>
			<a>Prenom : </a>${prenom}
			<br>
			<a>Email : </a>${email}
			<br>
			<a>Teléphone : </a>${telephone}
			<br>
			<a>Rue : </a>${rue}
			<br>
			<a>Code Postal : </a>${code_postal}
			<br>
			<a>Ville : </a>${ville}</p>
			<div>
				<a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Accueil"></a>
			</div>
		</div>
	</div>
</body>
</html>