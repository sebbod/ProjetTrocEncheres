<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.bids.bll.UserMessageReader" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ClassStyle.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ButtonStyle.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/GeneralStyle.css" type="text/css" />
		<title>Profil utilisateur</title>
	</head>
	
<body>
	<header>
		<c:choose>
			<c:when test="${sessionScope.user!=null}">
				<%@include file="../fragment/HeaderConnected.jspf" %>
			</c:when> 
			<c:otherwise >
				<%@include file="../fragment/HeaderDisconnected.jspf" %>
			</c:otherwise>
		</c:choose>
		
	</header>
	
	<div class="body">
			
		<%@include file="../fragment/Errors.jspf" %>
		
		<h1 class="title">Profil utilisateur</h1>
		
		<div class="divProfile">
			
			<p class="labelProfil"> Pseudo : </p>
			<p class= "infoProfil"> ${user.getPseudo()}</p>
			
			<p class="labelProfil"> Nom : </p>
			<p class= "infoProfil"> ${user.getName()}</p>

			<p class="labelProfil"> Prénom : </p>
			<p class= "infoProfil"> ${user.getFirstName()}</p>
			
			<p class="labelProfil"> Email : </p>
			<p class= "infoProfil"> ${user.getEmail()}</p>

			<p class="labelProfil"> Téléphone : </p>
			<p class= "infoProfil"> ${user.getTelephone()}</p>
			
			<p class="labelProfil"> Rue : </p>
			<p class= "infoProfil"> ${user.getStreet()}</p>

			<p class="labelProfil"> Code postal : </p>
			<p class= "infoProfil"> ${user.getZipCode()}</p>

			<p class="labelProfil"> Ville : </p>
			<p class= "infoProfil"> ${user.getTown()}</p>
			
			<c:if test="${profile.getUser().equals(sessionScope.user)}">
			<p class="labelProfil"> Crédit : </p>
			<p class= "infoProfil"> ${user.getCredit()}</p>
			</c:if>
		
		</div>

		<c:if test="${profile.getUser().equals(sessionScope.user)}">
			<div>
				<a href="<%=request.getContextPath()%>/User/Update?profile=${sessionScope.user}">
					<input type="button" name="modify" value="Modifier mon profil" class="profileButton"/>
				</a>
				
				<a onclick="showPopUp();">
					<input type="button" name="delete" value="Supprimer mon profil" class="profileButton"/>
				</a>	
			</div>
		</c:if>
		
		<div id="confirmationPopUp" class="confirmationPopUp">
			<p class="textConfirmationPopUp"> Êtes-vous sûr(e) de vouloir supprimer votre profil ?</p>
			<a onclick="hidePopUp();">
				<input type="button" name="cancel" value="Annuler" class="buttonPopUp"/>
			</a>
				
			<a href="<%=request.getContextPath()%>/User/Disable">
				<input type="button" name="validate" value="Supprimer mon profil" class="buttonPopUp"/>
			</a>		
		</div>
		
		<script type="text/javascript">
			function hidePopUp(){
				document.getElementById("confirmationPopUp").style.display = 'none';
			}
			
			function showPopUp(){
				document.getElementById("confirmationPopUp").style.display = 'block';
			}
		</script>
		
		
	<footer class="footer" id="footerCell">
		<%@include file="../fragment/Footer.jspf" %>
	</footer>

</div>
</body>
</html>