<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.bids.msg.MessageReader" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>Profil utilisateur</title>
		<%@ include file="../fragment/DefaultHead.jspf"%>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user-profil.css">
		<!-- JS UTILS -->
		<script src="<%=request.getContextPath()%>/js/ajax.js"></script>
		<script src="<%=request.getContextPath()%>/js/utils.js"></script>
		
		<!-- JS PAGE -->
		<script src="<%=request.getContextPath()%>/js/user.js"></script>
	</head>
	
<body>
	<%@include file="../fragment/Header.jspf" %>
	<%@include file="../fragment/Errors.jspf" %>
	<section id="profile" class="dataContainer">
		<div class="group">
			<h1 id="page-title" class="title">Profil de l'utilisateur</h1>
			<div class="container" role="alert" id="errorMsg"></div>
				<form id="formData">
					<div id="left-form">
						<!-- TODO LATER: IMPROVE DESIGN
						<div id="pseudo" class="input-container">
							<label>Pseudo :</label>
							<p class="value"></p>
						</div>
						<div id="firstname" class="input-container">
							<label>Prénom :</label>
							<p class="value"></p>
						</div>
						<div id="phone" class="input-container">
							<label>Pseudo :</label>
							<p class="value"></p>
						</div>
						<div id="zip-code" class="input-container">
							<label>Pseudo :</label>
							<p class="value"></p>
						</div>
						<div id="password" class="input-container">
							<label>Mot de passe :</label>
							<p class="value"></p>
						</div>
						-->
						<h4>Pseudo : <span id="pseudo"></span></h4>
						<h4>Prénom : <span id="firstName"></span></h4>
						<h4>Téléphone : <span id="telephone"></span></h4>
						<h4>Code Postal : <span id="zipCode"></span></h4>
						<h4 id="hpwd">Mot de passe : <span id="pwd"></span></h4>
					</div>
					<div id="right-form">
						<!-- TODO LATER: IMPROVE DESIGN
						<div id="name" class="input-container">
							<label>Nom :</label>
							<p class="value"></p>
						</div>
						<div id="email" class="input-container">
							<label>Email :</label>
							<p class="value"></p>
						</div>
						<div id="town" class="input-container">
							<label>Ville :</label>
							<p class="value"></p>
						</div>
						<div class="empty-input-container"></div>
						<div id="password-confirmation" class="input-container">
							<label>Confirmation :</label>
							<p class="value"></p>
						</div>
						-->
						<h4>Nom : <span id="name"></span></h4>
						<h4>Email : <span id="email"></span></h4>
						<h4>Rue : <span id="street"></span></h4>
						<h4>Ville : <span id="town"></span></h4>
						<div class="empty-input"></div>
						<h4 id="hpwdbis">Confirmation : <span id="pwdbis"></span></h4>
					</div>
				</form>
				<div class="btnActions" id="btnActions">
					<input id="edit" class="btn-accent" type="button" value="Modifier" >
					<input id="add" class="btn-accent" type="button" value="Créer" >
					<input id="save" class="btn-accent" type="button" value="Enregistrer">
					<input id="del" class="btn-danger" type="button" value="Supprimer mon compte" >
					<input id="cancel" class="btn-outline" type="button" value="Annuler">
				</div>
		    </div>
	    </section>	  
		<%@include file="../fragment/Footer.jspf" %>
		<script defer>
			let loginPage = "<%=request.getContextPath()%>/login";
			let addBtn = document.querySelector("#add");
			let delBtn = document.querySelector("#del");
			let cancBtn = document.querySelector("#cancel");
			let saveBtn = document.querySelector("#save");
			let pwdh4 = document.querySelector("#hpwd");
			let pwdspn = document.querySelector("#pwd");
			let pwdbish4 = document.querySelector("#hpwdbis");
			let pwdbisspn = document.querySelector("#pwdbis");
			let editBtn = document.querySelector("#edit");
			let errorMsg = document.querySelector("#errorMsg");
			let form = document.querySelector("#formData");
			var user;
						        
			const connectedUserId = "${sessionScope.connectedUserId}";
			if(connectedUserId != "") {
				getUser(connectedUserId);
			} else {
				const add = "${sessionScope.add}";
				if(add != "") {
					displayProfile4Add();
				}		        		
			}
		</script>
</body>
</html>
