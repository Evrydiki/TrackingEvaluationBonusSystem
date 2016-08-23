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
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery.quickflip.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<title>Οι αξιολογήσεις μου</title>
</head>
<body>
	<jsp:include page="/empList.jsp" />
	<jsp:include page="/evaluationsView.jsp" />
	<div id="wrapper" style="position: static;">
	<div id="menu">
		<c:if test="${role == 'employee'}">
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
				<li><a href='goals.jsp'>Στόχοι</a></li>
				<li class='active has-sub'><a href='employeeProfile.jsp'>Προφίλ
						${username}</a>
					<ul>
						<li><a href='evaluationsEmployeeMode.jsp'>Αξιολογήσεις</a></li>
						<li><a href='changePassword.jsp'>Αλλαγή Κωδικού</a></li>
						<li><a href='logout.jsp'>Αποσύνδεση</a></li>
					</ul></li>
			</ul>
		</c:if>
		</div>

		<br>
		<div id="header" align="center">
			<h2>Προβολή των αξιολογήσεων μου</h2>
		</div>
		<br>
		<c:choose>
			<c:when test="${fn:length(myEvaluations) == 0}">
				<p style="font-size: 25; color: blue; text-align: center;">Δεν
					υπάρχουν αξιολογήσεις.</p>
			</c:when>
			<c:otherwise>
				<div id="sidebar" style="height: 100%; float: left;">
					<table class="listTable">
						<tr>
							<th>ID Αξιολόγησης</th>
							<th>Ημερομηνία</th>
							<th>Συνολική Βαθμολογία</th>
							<th></th>
						</tr>
						<c:forEach items="${myEvaluations}" var="myEvaluation">
							<tr>
								<td><c:out value="${myEvaluation.id}" /></td>
								<td><c:out value="${myEvaluation.date}" /></td>
								<td><c:out value="${myEvaluation.total_rating}" />%</td>
								<td style="font-size: small;"><c:url
										value="currentEvaluationFrame.jsp" var="myUrl">
										<c:param name="id" value="${myEvaluation.id}" />
										<c:param name="type" value="${myEvaluation.type}" />
										<c:param name="date" value="${myEvaluation.date}" />
										<c:param name="comments" value="${myEvaluation.comments}" />
										<c:param name="next_evaluation_date"
											value="${myEvaluation.next_evaluation_date}" />
										<c:param name="total_rating"
											value="${myEvaluation.total_rating}" />
										<c:param name="employee_am_ika"
											value="${myEvaluation.employee_am_ika}" />
									</c:url><a href="${myUrl}" target="iframe_a">view</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<div id="main"
					style="position: absolute; left: 440px; width: 1000px;">
					<iframe id="emp" width="100%" height="800px" src="" name="iframe_a">
					</iframe>
				</div>
				<!--End of div main-->
			</c:otherwise>
		</c:choose>
	</div>
	<!--End of div wrapper-->
</body>
</html>