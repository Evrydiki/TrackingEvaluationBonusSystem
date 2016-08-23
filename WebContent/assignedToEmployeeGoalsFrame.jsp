<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script src="js/scriptMenu.js"></script>
<script type="text/javascript" src="js/jquery.quickflip.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
</head>
<body>
	<div id="header">
		<h4 align="center" style="font: bolder; font-style: italic;">Προβολή
			στοιχείων στόχου</h4>
	</div>
	<form action="EmployeeGoalServlet">
		<table title="allGoalsFrame" style="width: 750px; height: 870px;">
			<tr>
				<th>ID:</th>
				<td><input type="hidden" value="${param.id}" name="id">
					<c:out value="${param.id}" /></td>
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
			<tr>
				<th>Ανατέθηκε από το manager:</th>
				<td><c:out value="${param.manager}" /> <c:out
						value="${param.manager_name}" /></td>
			</tr>
			<tr>
				<th>Ο στόχος βρίσκεται σε εξέλιξη:</th>
				<td><input type="radio" name="inprogressTF" value="YES">Ναι<br>
					<input type="radio" name="inprogressTF" value="NO">Όχι</td>
			</tr>
			<tr>
				<th>Ολοκληρώθηκε:</th>
				<td><input type="radio" name="completedTF" value="YES">Ναι<br>
					<input type="radio" name="completedTF" value="NO">Όχι</td>
		</table>
			<input type="submit" value="ΥΠΟΒΟΛΗ ΑΛΛΑΓΩΝ" name="operation" 
						style="margin-left: 390px">
	</form>
</body>
</html>