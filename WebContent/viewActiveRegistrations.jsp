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
<script src="js/scriptMenu.js"></script>
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
<title>Προβολή Ενεργών Εγγραφών</title>
</head>

<body>
	<div id="wrapper">
		<div id="menu">
			<ul>
				<li><a href="homeAdmin.jsp">Αρχική Σελίδα </a></li>
				<li><a href='acceptRejectRegistrations.jsp'>Έγκριση/Απόρριψη
						Εγγραφών</a></li>
				<li class="active"><a href='viewActiveRegistrations.jsp'>Προβολή
						Ενεργών Εγγραφών</a></li>
				<li><a href='logout.jsp'>Αποσύνδεση</a></li>
			</ul>
		</div>
		<br>
		<div id="header" align="center">
			<h3>Διαχείριση Ενεργών Χρηστών</h3>
		</div>

		<jsp:include page="/empList.jsp" />
		<div id="flip-tabs">
			<ul id="flip-navigation">
				<li class="selected"><a href="#" id="tab-0">Διαχείριση
						Ενεργών Εργαζομένων</a></li>
				<li><a href="#" id="tab-1">Διαχείριση Ενεργών Manager</a></li>
			</ul>
			<div id="flip-container">
				<div class="tab">
					<div class="tab-content">
						<div id="#" class="tab-panel fade in active"></div>
						<div id="#" class="tab-pane fade"></div>
						<div id="#" class="tab-pane fade"></div>
					</div>
					<div id="sidebar" style="height: 100%; float: left;">
						<table class="listTable">
							<tr>
								<th>AM IKA</th>
								<th>Επώνυμο</th>
								<th>Όνομα</th>
								<th></th>
								<th></th>
							</tr>
							<c:forEach items="${activeEmployees}" var="activeEmployee">
								<form action="DeleteEmployeeManagerServlet" method="POST"
									target="iframe_a">
									<tr>
										<td><input type="hidden" value="${activeEmployee.am_ika}"
											name="am_ika"> <c:out
												value="${activeEmployee.am_ika}" /></td>
										<td><c:out value="${activeEmployee.lastname}" /></td>
										<td><c:out value="${activeEmployee.firstname}" /></td>
										<td style="font-size: small; word-wrap: break-word;"><c:url
												value="currentEmpFrame.jsp" var="employeeUrl">
												<c:param name="am_ika" value="${activeEmployee.am_ika}" />
												<c:param name="firstname"
													value="${activeEmployee.firstname}" />
												<c:param name="username" value="${activeEmployee.username}" />
												<c:param name="amka" value="${activeEmployee.amka}" />
												<c:param name="afm" value="${activeEmployee.afm}" />
												<c:param name="lastname" value="${activeEmployee.lastname}" />
												<c:param name="birthday" value="${activeEmployee.birthday}" />
												<c:param name="email" value="${activeEmployee.email}" />
												<c:param name="phone_number"
													value="${activeEmployee.phone_number}" />
												<c:param name="department"
													value="${activeEmployee.department}" />
												<c:param name="jobPosition"
													value="${activeEmployee.jobPosition}" />
												<c:param name="photoName" value="${activeEmployee.photoName}" />
												<c:param name="CVName" value="${activeEmployee.CVName}" />
												<c:param name="sex" value="${activeEmployee.sex}" />
												<c:param name="date_of_Recruitment"
													value="${activeEmployee.date_of_Recruitment}" />
												<c:param name="attribute"
													value="${activeEmployee.attribute}" />
											</c:url> <c:url value="editRegistrationsFrame.jsp" var="editUrl">
												<c:param name="am_ika" value="${activeEmployee.am_ika}" />
												<c:param name="firstname"
													value="${activeEmployee.firstname}" />
												<c:param name="username" value="${activeEmployee.username}" />
												<c:param name="lastname" value="${activeEmployee.lastname}" />
												<c:param name="amka" value="${activeEmployee.amka}" />
												<c:param name="afm" value="${activeEmployee.afm}" />
												<c:param name="sex" value="${activeEmployee.sex}" />
												<c:param name="birthday" value="${activeEmployee.birthday}" />
												<c:param name="email" value="${activeEmployee.email}" />
												<c:param name="phone_number"
													value="${activeEmployee.phone_number}" />
												<c:param name="department"
													value="${activeEmployee.department}" />
												<c:param name="jobPosition"
													value="${activeEmployee.jobPosition}" />
												<c:param name="CVName" value="${activeEmployee.CVName}" />
												<c:param name="sex" value="${activeEmployee.sex}" />
												<c:param name="date_of_Recruitment"
													value="${activeEmployee.date_of_Recruitment}" />
												<c:param name="attribute"
													value="${activeEmployee.attribute}" />
											</c:url> <!-- view --> <a href="${employeeUrl}" target="iframe_a">view</a>/<!--edit-->
											<a href="${editUrl}" target="iframe_a">edit</a></td>
										<td>
											<!--delete-->
											<button type="submit" name="operation" value="DeleteEmployee">
												<img src="./images/Delete_icon.png" class="delete_image">
											</button>
										</td>
									</tr>
								</form>
							</c:forEach>
						</table>
					</div>

					<div id="main" style="position: relative; left: 430px; width: 70%;">
						<iframe id="emp" width="100%" height="800px" src=""
							name="iframe_a"> </iframe>
					</div>
					<!--End of div main-->
				</div>
				<!--End of div tab-->
				<div>
					<!--Put Content for second tab here-->
					<jsp:include page="editActiveManagers.jsp" />
					<!--End of div main-->
				</div>
			</div>
			<!--End of div flip-container-->
		</div>
		<!--End of div flip-tabs-->
	</div>
	<!--End of div wrapper-->
</body>

</html>