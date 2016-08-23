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

<title>Εργαζόμενοι και Managers</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<div id="wrapper">
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
					<li class='active'><a href='employeesTabbed-employeeMode.jsp'>Εργαζόμενοι</a></li>
					<li><a href='goals.jsp'>Στόχοι</a></li>
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
			<h3>Προβολή Στοιχείων Εργαζομένων και Manager</h3>
		</div>
		<br>

		<jsp:include page="/empList.jsp" />
		<div id="flip-tabs">
			<ul id="flip-navigation">
				<li class="selected"><a href="#" id="tab-0">Προβολή
						Στοιχείων Εργαζομένων</a></li>
				<li><a href="#" id="tab-1">Προβολή Στοιχείων Manager</a></li>
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
								<th>Επώνυμο</th>
								<th>Όνομα</th>
								<th></th>
							</tr>
							<c:forEach items="${activeEmployees}" var="activeEmployee">
								<tr>
									<td><c:out value="${activeEmployee.lastname}" /></td>
									<td><c:out value="${activeEmployee.firstname}" /></td>
									<td style="font-size: small;"><c:url
											value="currentEmpFrame.jsp" var="myUrl">
											<c:param name="attribute" value="${activeEmployee.attribute}" />
											<c:param name="username" value="${activeEmployee.username}" />
											<c:param name="am_ika" value="${activeEmployee.am_ika}" />
											<c:param name="photoName" value="${activeEmployee.photoName}" />
											<c:param name="amka" value="${activeEmployee.amka}" />
											<c:param name="afm" value="${activeEmployee.afm}" />
											<c:param name="lastname" value="${activeEmployee.lastname}" />
											<c:param name="firstname" value="${activeEmployee.firstname}" />
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
										</c:url><a href="${myUrl}" target="iframe_a">view</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>

					<div id="main" style="position: relative; left: 280px; width: 70%;">
						<iframe id="emp" width="100%" height="800px" src=""
							name="iframe_a"> </iframe>
					</div>
					<!--End of div main-->
				</div>
				<!--End of div tab-->
				<div>
					<!--Put Content for second tab here-->
					<jsp:include page="/viewManagers.jsp" />
				</div>
			</div>
			<!--End of div flip-container-->
		</div>
		<!--End of div flip-tabs-->
	</div>
	<!--End of div wrapper-->
</body>
</html>