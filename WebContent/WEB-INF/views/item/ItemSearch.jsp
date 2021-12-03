<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<%@ include file="../fragment/DefaultHead.jspf"%>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user-profil.css">
		<!-- JS UTILS -->
		<script src="<%=request.getContextPath()%>/js/ajax.js"></script>
		<script src="<%=request.getContextPath()%>/js/utils.js"></script>
		<script src="<%=request.getContextPath()%>/js/date-tools.js"></script>
		
		<!-- JS PAGE -->
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
			<div class="dateContainer">
				<h1 class="title">Liste des enchères</h1>	
				
				<div class="container-error" role="alert" id="errorMsg"></div>
				<form id="formData" class="search-mode">
					<label>Filtres :</label>
					  <div >
					    <input type="search" placeholder="Recherche" aria-label="Search" id="search">
					    <button type="submit" style="cursor: not-allowed" disabled>Recherche</button>
					  </div>
					  <div >
					    <label for="category">Catégorie</label>
					    <select id="category">
					      <option selected value ="">Toutes</option>
					    </select>
					  </div>	
					  <table id="filters" class="">
					    <tr>
					      <td>
					        <label>
					          <input type="radio" name="filter"  value="buy" checked />
					          Achats
					        </label>
					        <br />
					        <input type="checkbox" id="saleIsOpen"><label for="saleIsOpen">Enchères ouvertes</label>
					        <br />
					        <input type="checkbox" id="isCurrentUser"><label for="isCurrentUser">Mes enchères en cours</label>
					        <br />
					        <input type="checkbox" id="saleIsWon"><label for="saleIsWon">Mes enchères remportées</label>
					        <br />
					      </td>
					      <td>
					        <label>
					          <input type="radio" name="filter"  value="sell" />
					          Ventes
					        </label>
					        <br />
					        <input type="checkbox" id="saleIsOnGoing"><label for="saleIsOnGoing">Mes ventes en cours</label>
					        <br />
					        <input type="checkbox" id="saleIsCreated"><label for="saleIsCreated">Ventes non débutées</label>
					        <br />
					        <input type="checkbox" id="saleIsOver"><label for="saleIsOver">Ventes terminées</label>
					        <br />
					      </td>
					    </tr>
					</table>				
				</form>
				<div class="container">
				  <div id="items" class="items"></div>
				</div>
			
				<section id="scripts">
			        <script defer>
			       
			        const connectedUserId = "${sessionScope.connectedUserId}";
		        	if(connectedUserId == ""){
		        		document.querySelector("#filters").style.display = "none";	
		        	}
			        let searchInput = document.querySelector("#search")
			        let userSearch = searchInput.value;			        
			        searchInput.oninput = $event => {
			        	userSearch = $event.target.value;
			        	search();
			        }
			        
			        
			        let cateLst = document.querySelector("#category")	
			        let category = cateLst.value;
			        cateLst.onchange = $event => {
			        	category = $event.target.value;
			        	search();
			        }
			        
			        let checkboxes = {
		                saleIsOpen: false,
		                isCurrentUser: false,
		                saleIsWon: false,
		                saleIsOnGoing: false,
		                saleIsCreated: false,
		                saleIsOver: false
		            };
			        
		        	if(connectedUserId == ""){
		        		document.querySelector("#filters").style.display = "none";
		        		checkboxes.saleIsOpen = true;
		        	}
			        let filter = document.querySelector('input[name="filter"]:checked').value;
			        document.querySelectorAll("input[type=radio]").forEach(radio => {
			            radio.onchange = $event => {
			                filter = $event.target.value;
			                updateCheckboxes();
			                search();
			            }
			        })
					activeCheckbox();
			        
			        let divItems = document.querySelector("#items")
			        let itemLst = [];

			        loadCategories();
			        refresh();
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
