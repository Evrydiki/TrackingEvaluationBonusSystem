<%@ page language="java" pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./styles.css" />
</head>
<body>
	<jsp:include page="/goalList.jsp" />
	<div id="sidebar" style="width: 30%;">
		<div id="header">
			<h4 align="center" style="font: bolder; font-style: italic;">Προβολή
				στοιχείων στόχου</h4>
		</div>
		<table class="incompletedGoalsListTable"
			style="width: 750px; height: 900px;">
			<tr>
				<th align="center">ID</th>
				<th align="center">Τίτλος</th>
				<th align="center">Προθεσμία</th>
				<th align="center">Σε εξέλιξη</th>
				<th></th>
			</tr>
			<c:forEach items="${incompletedGoals}" var="incompletedGoal">
				<tr>
					<td><c:out value="${incompletedGoal.id}" /></td>
					<td style="width: 150px; word-wrap: break-word;"><c:out
							value="${incompletedGoal.title}" /></td>
					<td><c:out value="${incompletedGoal.deadline}" /></td>
					<td><c:out value="${incompletedGoal.in_progress}" /></td>
					<td style="font-size: small;"><c:url
							value="currentInactiveGoalFrame.jsp" var="myUrl">
							<c:param name="currentInactiveGoal" value="${incompletedGoal}" />
							<c:param name="id" value="${incompletedGoal.id}" />
							<c:param name="title" value="${incompletedGoal.title}" />
							<c:param name="directions" value="${incompletedGoal.directions}" />
							<c:param name="resources_needed"
								value="${incompletedGoal.resources_needed}" />
							<c:param name="complexity" value="${incompletedGoal.complexity}" />
							<c:param name="significance"
								value="${incompletedGoal.significance}" />
							<c:param name="concerning_department"
								value="${incompletedGoal.concerning_department}" />
							<c:param name="type" value="${incompletedGoal.type}" />
							<c:param name="bonus_ammount"
								value="${incompletedGoal.bonus_ammount}" />
							<c:param name="deadline" value="${incompletedGoal.deadline}" />
						</c:url><a href="${myUrl}" target="iframe_a">view</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="main"
		style="left: 15%; position: absolute; width: 75%; height: 120%">
		<iframe id="emp" width="100%" height="100%" src="" name="iframe_a">
		</iframe>
	</div>
	<!--End of div main-->
</body>
</html>