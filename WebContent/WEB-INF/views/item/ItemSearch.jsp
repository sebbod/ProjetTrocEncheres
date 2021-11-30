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
		<title>Liste des enchères</title>
		<script src="<%=request.getContextPath()%>/js/ajax.js"></script>
		<script src="<%=request.getContextPath()%>/js/itemSearch.js"></script>
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
		<section id="itemSearch" class="dataContainer">
			<div class="group">
				<h1 class="title">Liste des enchères</h1>	
				
				<div class="displayLine" role="alert" id="errorMsg"></div>
				<form id="formData">
					<label>Filtres :</label>
					  <div>
					    <input type="search" placeholder="Recherche" aria-label="Search" id="search">
					    <button type="submit" style="cursor: not-allowed" disabled>Recherche</button>
					  </div>
					  <div >
					    <label for="categories">Catégorie</label>
					    <select id="categories">
					      <option selected value ="">Toutes</option>
					    </select>
					  </div>					
				</form>
				<div class="container">
				  <div id="items"></div>
				</div>
			
				<section id="scripts">
			        <script defer>
			        //let loginPage = "<%=request.getContextPath()%>/login";
			        let searchBtn = document.querySelector("#search")
			        let divItems = document.querySelector("#items")
			        let cateLst = document.querySelector("#categories")			
			        /*
		        	const connectedUserId = "${sessionScope.connectedUserId}";
		        	if(connectedUserId != ""){
			        	getUser(connectedUserId);
		        	}else{
		        		const add = "${sessionScope.add}";
			        	if(add != ""){
			        		displayProfile4Add();
			        	}		        		
		        	}
		        	*/
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
