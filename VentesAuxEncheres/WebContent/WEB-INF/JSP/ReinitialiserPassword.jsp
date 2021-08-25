<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email Confirmer</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/ReinitialiserPassword" method="post">
		Email : 
		<input type="text" name="email" required>
		Nouveau mot de passe : 
		<input type="text" name="newPassword" required>
		Confirmer le nouveau mot de passe : 
		<input type="text" name="confirmPassword" required>
		<a href="<%=request.getContextPath()%>/PageDeConnexion"><input type="submit" value="Confirmer"></a>
	</form>
</body>
</html>