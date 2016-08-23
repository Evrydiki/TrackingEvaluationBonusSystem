<%@ page language="java" pageEncoding="UTF-8"%>
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
<title></title>
</head>
<body>
	<jsp:include page="/empList.jsp" />
	<jsp:include page="/evaluationsView.jsp" />
	<c:choose>
		<c:when
			test="${fn:length(employeeEvaluations) == 0}">
			<p style="font-size: 25; color: blue; text-align: center;">Δεν
				υπάρχουν αξιολογήσεις για αυτόν τον υπάλληλο.</p>
		</c:when>
		<c:otherwise>
			<table class="employeesListTable">
				<tr>
					<th>ID Αξιολόγησης</th>
					<th>Ημερομηνία</th>
					<th>Συνολική Βαθμολογία</th>
					<th></th>
				</tr>
				<c:forEach items="${employeeEvaluations}" var="employeeEvaluation">
					<tr>
						<td align="center"><c:out value="${employeeEvaluation.id}" /></td>
						<td><c:out value="${employeeEvaluation.date}" /></td>
						<td align="center"><c:out
								value="${employeeEvaluation.total_rating}" />%</td>
						<td style="font-size: small;"><c:url
								value="currentEvaluationFrame.jsp" var="myUrl">
								<c:param name="id" value="${employeeEvaluation.id}" />
								<c:param name="type" value="${employeeEvaluation.type}" />
								<c:param name="date" value="${employeeEvaluation.date}" />
								<c:param name="comments" value="${employeeEvaluation.comments}" />
								<c:param name="next_evaluation_date"
									value="${employeeEvaluation.next_evaluation_date}" />
								<c:param name="total_rating"
									value="${employeeEvaluation.total_rating}" />
								<c:param name="employee_am_ika"
									value="${employeeEvaluation.employee_am_ika}" />
							</c:url><a href="${myUrl}" target="iframe_b">view</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>