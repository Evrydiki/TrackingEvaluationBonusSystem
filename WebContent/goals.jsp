<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="js/jquery.quickflip.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script type="text/javascript">
	$('document').ready(function() {
		$('#flip-container').quickFlip();

		$('#flip-navigation li a').each(function() {
			$(this).click(function() {
				$('#flip-navigation li').each(function() {
					$(this).removeClass('selected');
				});
				$(this).parent().addClass('selected');
				var flipid = $(this).attr('id').substr(4);
				$('#flip-container').quickFlipper({}, flipid, 1);

				return false;
			});
		});
	});
</script>
<title>Στόχοι Οργανισμού</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<c:if test="${role == 'employee'}">
		<div id="menu">
				<ul>
					<li><a href="homeEmployee.jsp" accesskey="1">Αρχική Σελίδα
					</a></li>
					<li class='has-sub'><a href='company.jsp'>Η Εταιρία</a>
						<ul>
							<li class='has-sub'><a href='company.jsp'>Προβολή
									στοιχείων</a>
								<ul>
									<li><a id="link" href='businessStrategy.jsp'>Επιχειρησιακή
											Στρατηγική</a></li>
								</ul></li>
							<li><a href='businessInfo.jsp'>Πληροφορίες</a></li>
						</ul></li>
					<li><a href='employeesTabbed-employeeMode.jsp'>Εργαζόμενοι</a></li>
					<li class='active'><a href='goals.jsp'>Στόχοι</a></li>
					<li class='has-sub'><a href='employeeProfile.jsp'>Προφίλ
							${username}</a>
						<ul>
							<li><a href='evaluationsEmployeeMode.jsp'>Αξιολογήσεις</a></li>
							<li><a href='changePassword.jsp'>Αλλαγή Κωδικού</a></li>
							<li><a href='logout.jsp'>Αποσύνδεση</a></li>
						</ul></li>
				</ul>
			</div>
	</c:if>
	<br>
	<div id="header" align="center">
		<h3>Προβολή αναθετημένων στόχων</h3>
	</div>
	<br>

	<jsp:include page="/empList.jsp" />
	<jsp:include page="/goalList.jsp" />
	<c:choose>
		<c:when
			test="${fn:length(goalsAssignedToEmployees) == 0 && fn:length(goalsAssignedToEmployees) == 0}">
			<p style="font-size: 25; color: blue; text-align: center;">Δεν
				υπάρχουν αναθέσεις στόχων την τρέχουσα στιγμή.</p>
		</c:when>
		<c:otherwise>
			<div id="sidebar"
				style="height: 1000px; width: 550x; overflow: auto; float: left;">
				<table class="listTable">
					<tr>
						<th>ID</th>
						<th>Τίτλος</th>
						<th>Προθεσμία</th>
						<th>Status</th>
						<th>Ολοκληρώθηκε</th>
						<th>Σε εξέλιξη</th>
						<th></th>
					</tr>
					<c:forEach items="${goalsAssignedToEmployees}" var="goal">
						<tr>
							<td><input type="hidden" value="${goal.id}" name="id">
								<c:out value="${goal.id}" /></td>
							<td style="width: 150px; word-wrap: break-word;"><c:out
									value="${goal.title}" /></td>
							<td style="word-wrap: break-word;"><c:out
									value="${goal.deadline}" /></td>
							<td><c:choose>
									<c:when test="${goal.completed == 'YES'}">
										<img alt="" src="./images/status_icons-complete.jpg"
											width="50" height="50">
									</c:when>
									<c:when test="${goal.in_progress == 'YES'}">
										<img alt="" src="./images/pending.png" width="50" height="50">
									</c:when>
									<c:otherwise>
										<img alt="" src="./images/120px-Icon-cross.png" width="50"
											height="50">
									</c:otherwise>
								</c:choose></td>
							<td style="text-align: center;">
								<!--mark in progress--> <input type="checkbox"
								name="goalsInProgressCBOX" value="<c:out value="YES" />"
								${goal.completed == 'YES' ? 'checked' : ''}>
							</td>
							<td style="text-align: center;">
								<!--mark as completed--> <input type="checkbox"
								name="goalsCompletedCBOX" value="<c:out value="YES" />"
								${goal.in_progress == 'YES' ? 'checked' : ''}>
							</td>
							<td style="font-size: small; width: 150; word-wrap: break-word;"><c:url
									value="editGoalFrame.jsp" var="editGoallUrl">
									<c:param name="id" value="${goal.id}" />
									<c:param name="title" value="${goal.title}" />
									<c:param name="directions" value="${goal.directions}" />
									<c:param name="resources_needed"
										value="${goal.resources_needed}" />
									<c:param name="complexity" value="${goal.complexity}" />
									<c:param name="significance" value="${goal.significance}" />
									<c:param name="concerning_department"
										value="${goal.concerning_department}" />
									<c:param name="type" value="${goal.type}" />
									<c:param name="bonus_ammount" value="${goal.bonus_ammount}" />
									<c:param name="deadline" value="${goal.deadline}" />
								</c:url> <c:url value="assignedToEmployeeGoalsFrame.jsp"
									var="allGoalsUrl">
									<c:param name="id" value="${goal.id}" />
									<c:param name="title" value="${goal.title}" />
									<c:param name="directions" value="${goal.directions}" />
									<c:param name="resources_needed"
										value="${goal.resources_needed}" />
									<c:param name="complexity" value="${goal.complexity}" />
									<c:param name="significance" value="${goal.significance}" />
									<c:param name="concerning_department"
										value="${goal.concerning_department}" />
									<c:param name="type" value="${goal.type}" />
									<c:param name="bonus_ammount" value="${goal.bonus_ammount}" />
									<c:param name="deadline" value="${goal.deadline}" />
									<c:param name="manager" value="${goal.manager_am_ika}" />
									<c:param name="manager_name" value="${goal.manager_name}" />
								</c:url> <!-- view --> <a href="${allGoalsUrl}" target="iframe_a">view</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="main"
				style="left: 415px; position: absolute; width: 70%; height: 1000px; margin-left: 200px;">
				<iframe id="emp" width="100%" height="100%" src="" name="iframe_a">
				</iframe>
			</div>
			<!--End of div main-->
		</c:otherwise>
	</c:choose>
</body>
</html>