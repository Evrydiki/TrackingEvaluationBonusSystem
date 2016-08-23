<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery.quickflip.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script src="js/scriptMenu.js"></script>
</head>
<body>
	<form action="assignGoalServlet" method="post">
		<table title="currentInactiveGoal"
			style="width: 500px; height: 650px;">
			<tr>
				<th>ID:</th>
				<td><c:out value="${param.id}" /></td>
			</tr>
			<tr>
				<th>Τίτλος:</th>
				<td><c:out value="${param.title}" /></td>
			</tr>
			<tr>
				<th>Οδηγίες:</th>
				<td><c:out value="${param.directions}" /></td>
			</tr>
			<tr>
				<th>Πόροι που απαιτούνται:</th>
				<td><c:out value="${param.resources_needed}" /></td>
			</tr>
			<tr>
				<th>Πολυπλοκότητα:</th>
				<td class="complexity" style="font-size: x-large;"><c:if
						test="${param.complexity == 1}">
					 ❶
					</c:if> <c:if test="${param.complexity == 2}">
					 ❶ ❷
					</c:if> <c:if test="${param.complexity == 3}">
					 ❶ ❷ ❸ 
					</c:if> <c:if test="${param.complexity == 4}">
					 ❶ ❷ ❸ ❹ 				
					</c:if> <c:if test="${param.complexity == 5}">
					 ❶ ❷ ❸ ❹ ❺ 
					</c:if></td>
			</tr>
			<tr>
				<th>Σημαντικότητα:</th>
				<td class="stars" style="font-size: x-large;"><c:if
						test="${param.significance == 1}">
						<span>★</span>
						<span>☆</span>
						<span>☆</span>
						<span>☆</span>
						<span>☆</span>
					</c:if> <c:if test="${param.significance == 2}">
						<span>★</span>
						<span>★</span>
						<span>☆</span>
						<span>☆</span>
						<span>☆</span>
					</c:if> <c:if test="${param.significance == 3}">
						<span class="star">★</span>
						<span>★</span>
						<span>★</span>
						<span>☆</span>
						<span>☆</span>
					</c:if> <c:if test="${param.significance == 4}">
						<span class="star">★</span>
						<span>★</span>
						<span>★</span>
						<span>★</span>
						<span>☆</span>
					</c:if> <c:if test="${param.significance == 5}">
						<span class="star">★</span>
						<span>★</span>
						<span>★</span>
						<span>★</span>
						<span>★</span>
					</c:if></td>
			</tr>
			<tr>
				<th>Τμήμα Εταιρίας που αφορά:</th>
				<td><c:out value="${param.concerning_department}" /></td>
			</tr>
			<tr>
				<th>Τύπος Στόχου:</th>
				<td><c:out value="${param.type}" /></td>
			</tr>
			<tr>
				<th>Πίστωση Bonus:</th>
				<td><c:out value="${param.bonus_ammount}" /></td>
			</tr>
			<tr>
				<th>Προθεσμία:</th>
				<td><c:out value="${param.deadline}" /></td>
			</tr>
		</table>
		<input id="addOneMoreButton" type="button"
			value="Αναθέστε έναν ακόμα υπάλληλο." /> <input
			id="submitAllAssignings" type="button" value="Υποβολή" />
	</form>
</body>
</html>