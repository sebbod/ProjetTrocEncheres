<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.bids.bll.UserMessageReader" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css" type="text/css" />
		<title>Profil utilisateur</title>
	</head>
	
<body>
	<header>
		<c:choose>
			<c:when test="${sessionScope.connectedUser!=null}">
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
		
			<c:choose>
				<c:when test="${sessionScope.action==\"insert\"}">
					<%@include file="../fragment/User/UserProfil4Insert.jspf" %>
				</c:when> 
				<c:when test="${sessionScope.action==\"update\"}">
					<%@include file="../fragment/User/UserProfil4Update.jspf" %>
				</c:when>
				<c:otherwise >
					<%@include file="../fragment/User/UserProfil4Select.jspf" %>
				</c:otherwise>
			</c:choose>		
		</div>
		
		<footer class="footer" id="footerCell">
			<%@include file="../fragment/Footer.jspf" %>
		</footer>

	</div>
</body>
</html>
