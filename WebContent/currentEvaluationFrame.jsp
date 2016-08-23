<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="./styles.css" />
</head>
<body>
	<div id="header" align="center">
		<h3>Προβολή αξιολόγησης με id ${param.id}</h3>
	</div>
	<jsp:include page="/ratingsView.jsp"/>
	<table title="currentEmpProfile"
		style="height: 250px; margin-left: 200px">
		<tr>
			<th>Ημερομηνία Αξιολόγησης:</th>
			<td><c:out value="${param.date}" /></td>
		</tr>
		<tr>
			<th>Τύπος:</th>
			<td><c:out value="${param.type}" /></td>
		</tr>
		<tr>
			<th>Σχόλια:</th>
			<td><c:out value="${param.comments}" /></td>
		</tr>
		<tr>
			<th>Επόμενη Αξιολόγηση:</th>
			<td><c:out value="${param.next_evaluation_date}" /></td>
		</tr>
	</table>
	<br>
	<div id="header" align="center">
		<h4>Βαθμολογίες ανά κατηγορία:</h4>
	</div>
	<c:if test="${role == 'employee'}">
		<table class="ratingTable"
			style="width: 300px; height: 95px; margin-left: 250px">
			<c:forEach items="${ratings}" var="rating">
				<tr>
					<th>${rating.category_title}</th>
					<td>${rating.rating}</td>
				</tr>
			</c:forEach>
			<tr>
				<th style="text-decoration: underline;">Συνολική Βαθμολογία:</th>
				<td><c:out value="${param.total_rating}" />%</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${role == 'manager'}">
		<table class="ratingTable"
			style="width: 300px; height: 95px; margin-left: 250px">
			<c:forEach items="${viewRatings}" var="viewRating">
				<tr>
					<th>${viewRating.category_title}</th>
					<td>${viewRating.rating}</td>
				</tr>
			</c:forEach>
			<tr>
				<th style="text-decoration: underline;">Συνολική Βαθμολογία:</th>
				<td><c:out value="${param.total_rating}" />%</td>
			</tr>
		</table>
	</c:if>
</body>
</html>
