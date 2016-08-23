<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
	<c:if test="${role == 'manager'}">
		<div id="menu">
			<ul>
				<li><a href="homeManager.jsp">Αρχική Σελίδα </a></li>
				<li class='has-sub'><a href='company.jsp'>Η Εταιρία</a>
					<ul>
						<li class='has-sub'><a href='company.jsp'>Προβολή
								Στοιχείων</a>
							<ul>
								<li><a href='businessStrategy.jsp'>Επιχειρησιακή
										Στρατηγική</a></li>
							</ul></li>
						<li><a href='businessInfo.jsp'>Πληροφορίες</a></li>
					</ul></li>
				<li><a href='employeesTabbed.jsp'>Εργαζόμενοι</a></li>
				<li class='active'><a href='goalsTabbed.jsp'>Στόχοι</a></li>
				<li class='has-sub'><a href='managerProfile.jsp'>Προφίλ
						${username}</a>
					<ul>
						<li><a href='changePassword.jsp'>Αλλαγή Κωδικού</a></li>
						<li><a href='logout.jsp'>Αποσύνδεση</a></li>
					</ul></li>
			</ul>
		</div>
	</c:if>
	<br>
	<div id="header" align="center">
		<h3>Διαχείριση Στόχων</h3>
	</div>
	<br>
	<div id="flip-tabs">
		<ul id="flip-navigation">
			<li class="selected"><a href="#" id="tab-0">Εισαγωγή Νέου
					Στόχου</a></li>
			<li><a href="#" id="tab-1">Επεξεργασία Στοιχείων Στόχων</a></li>
		</ul>
		<div id="flip-container">
			<div class="tab">
				<div class="tab-content">
					<div id="#" class="tab-panel fade in active"></div>
					<div id="#" class="tab-pane fade"></div>
					<div id="#" class="tab-pane fade"></div>
				</div>
				<jsp:include page="insertGoal.jsp" />
			</div>
			<!--End of div tab-->

			<div>
				<jsp:include page="/goalList.jsp" />
				<div id="sidebar"
					style="height: 120%; width: 550x; overflow: auto; float: left;">
					<table class="listTable">
						<tr>
							<th>ID</th>
							<th>Τίτλος</th>
							<th>Προθεσμία</th>
							<th>Status</th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach items="${allGoals}" var="goal">
							<form action="DeleteServlet" method="POST" target="iframe_a">
								<tr>
									<td><input type="hidden" value="${goal.id}" name="id">
										<c:out value="${goal.id}" /></td>
									<td style="width: 150px; word-wrap: break-word;"><c:out
											value="${goal.title}" /></td>
									<td><c:out value="${goal.deadline}" /></td>
									<td><c:choose>
											<c:when test="${goal.in_progress == 'YES'}">
												<img alt="" src="./images/pending.png" width="50"
													height="50">
											</c:when>
											<c:when test="${goal.completed == 'YES'}">
												<img alt="" src="./images/status_icons-complete.jpg"
													width="50" height="50">
											</c:when>
											<c:otherwise>
												<img alt="" src="./images/120px-Icon-cross.png" width="50"
													height="50">
											</c:otherwise>
										</c:choose></td>
									<td style="font-size: small; word-wrap: break-word;"><c:url
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
										</c:url> <c:url value="allGoalsFrame.jsp" var="allGoalsUrl">
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
										</c:url> <!-- view --> <a href="${allGoalsUrl}" target="iframe_a">view</a>/<!--edit-->
										<a href="${editGoallUrl}" target="iframe_a">edit</a></td>
									<td>
										<!--delete-->
										<button type="submit" name="operation" value="Delete">
											<img src="./images/Delete_icon.png" class="delete_image">
										</button>
									</td>
								</tr>
							</form>
						</c:forEach>
					</table>
				</div>
				<div id="main"
					style="left: 300px; position: absolute; width: 70%; height: 120%; margin-left: 200px;">
					<iframe id="emp" width="100%" height="100%" src="" name="iframe_a">
					</iframe>
				</div>
				<!--End of div main-->
			</div>
			<div></div>
			<!--End of div flip-container-->
		</div>
		<!--End of div flip-tabs-->
	</div>
</body>
</html>