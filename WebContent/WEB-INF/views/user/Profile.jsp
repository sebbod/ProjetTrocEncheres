<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.bids.msg.MessageReader" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css" type="text/css" />
		<title>Profil utilisateur</title>
		<script src="<%=request.getContextPath()%>/js/ajax.js"></script>
		<script src="<%=request.getContextPath()%>/js/user.js"></script>
	</head>
	
<body>
	<header>
		<c:choose>
			<c:when test="${sessionScope.connectedUserId!=null}">
				<%@include file="../fragment/HeaderConnected.jspf" %>
			</c:when> 
			<c:otherwise >
				<%@include file="../fragment/HeaderDisconnected.jspf" %>
			</c:otherwise>
		</c:choose>		
	</header>	
		<%@include file="../fragment/Errors.jspf" %>
		<section id="profile" class="dataContainer">
			<div class="group">
				<h1 class="title">Profil utilisateur</h1>	
				
					<div class="displayLine" role="alert" id="errorMsg"></div>
					<form id="formData">
						<div class="left">
						  	<h4>Pseudo: <span id="pseudo"></span></h4>	
						</div>
						<div class="right">					
						  	<h4>Nom: <span id="name"></span></h4>
						</div> 				  
						<div class="left">
							<h4>Prénom: <span id="firstName"></span></h4>			
						</div>
						<div class="right">		
							<h4>Email: <span id="email"></span></h4>
						</div>
						<div class="left">
							<h4>Téléphone: <span id="telephone"></span></h4>
						</div>
						<div class="right">
							<h4>Rue: <span id="street"></span></h4>
						</div>		
						<div class="left">
							<h4>Code Postal: <span id="zipCode"></span></h4>
						</div>
						<div class="right">
							<h4>Ville: <span id="town"></span></h4>
						</div>		
						<div class="left">
							<h4 id="hpwd">Mot de passe: <span id="pwd"></span></h4>
						</div>		
						<div class="right">						
							<h4 id="hpwdbis">Confirmation: <span id="pwdbis"></span></h4>
						</div>
					</form>
					<div class="btnActions" id="btnActions">
						<input id="edit" type="button" value="Modifier" >
						<input id="add" type="button" value="Créer" >
						<input id="save" type="button" value="Enregistrer">
						<input id="del" type="button" value="Supprimer" >
						<input id="cancel" type="button" value="Annuler">
					</div>
				
				<section id="scripts">
			        <script defer>
			        let loginPage = "<%=request.getContextPath()%>/login";
			        let addBtn = document.querySelector("#add")
			        let delBtn = document.querySelector("#del")
			        let cancBtn = document.querySelector("#cancel")
			        let saveBtn = document.querySelector("#save")
			        let pwdh4 = document.querySelector("#hpwd")
			        let pwdspn = document.querySelector("#pwd")
			        let pwdbish4 = document.querySelector("#hpwdbis")
			        let pwdbisspn = document.querySelector("#pwdbis")
			        let editBtn = document.querySelector("#edit")  
			        let errorMsg = document.querySelector("#errorMsg")
			        
			        	const connectedUserId = "${sessionScope.connectedUserId}";
			        	if(connectedUserId != ""){
				        	getUser(connectedUserId);
			        	}else{
			        		const add = "${sessionScope.add}";
				        	if(add != ""){
				        		displayProfile4Add();
				        	}		        		
			        	}		        	
			        </script>
			        <script>
	
			        </script>
			    </section>
		    </div>
	    </section>
		<footer class="text-center mx-auto">		  
		  <%@include file="../fragment/Footer.jspf" %>
		</footer>	
</body>
</html>
