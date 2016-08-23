<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="js/jquery.quickflip.js"></script>
<script src="js/scriptMenu.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<title>Έγκριση/Απόρριψη Εγγραφών</title>
</head>

<body>
	<jsp:include page="/empList.jsp" />
	<div id="menu">
		<ul>
			<li><a href="homeAdmin.jsp">Αρχική Σελίδα </a></li>
			<li class="active"><a href='acceptRejectRegistrations.jsp'>Έγκριση/Απόρριψη
					Εγγραφών</a></li>
			<li><a href='viewActiveRegistrations.jsp'>Προβολή Ενεργών
					Εγγραφών</a></li>
			<li><a href='logout.jsp'>Αποσύνδεση</a></li>
		</ul>
	</div>

	<div id="header">
		<h2>Αποδοχή/Απόρριψη Εγγραφών</h2>
	</div>

	<div id="contents">
		<p style="font-size: 25; color: green; text-align: center;">
			${editSuccessMessage}</p>
		<p style="font-size: 25; color: red; text-align: center;">${editFailMessage}</p>
		<p style="font-size: 25; color: blue; text-align: center;">${message}</p>
		<c:choose>
			<c:when test="${fn:length(inactiveEmployees)==0 and fn:length(inactiveManagers)==0}">
				<p style="font-size: 25; color: blue; text-align: center;">Δεν
					υπάρχουν αιτήματα εγγραφής στο σύστημα την τρέχουσα στιγμή.</p>
			</c:when>
			<c:otherwise>
				<form action="ApproveRejectRegistrations" method="POST">
					<c:forEach items="${inactiveEmployees}" var="inactiveEmployee">
						<input type="checkbox" name="inactiveEmployeesCBOX"
							value="<c:out value="${inactiveEmployee.am_ika}" />">
						<c:out
							value="${inactiveEmployee.am_ika} ${inactiveEmployee.lastname} ${inactiveEmployee.firstname}" />
						<br>
					</c:forEach>
					<c:forEach items="${inactiveManagers}" var="inactiveManager">
						<input type="checkbox" name="inactiveManagersCBOX"
							value="<c:out value="${inactiveManager.am_ika}" />">
						<c:out
							value="${inactiveManager.am_ika} ${inactiveManager.lastname} ${inactiveManager.firstname}" />
						<br>
					</c:forEach>
					<div onclick="this.style.visibility= 'hidden';">
						<input id="submit_button" type="submit" value="ΕΓΚΡΙΣΗ"
							name="operation"> <input type="reset" value="RESET">
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>

</body>

</html>