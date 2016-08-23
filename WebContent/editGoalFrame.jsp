<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script src="js/scriptMenu.js"></script>
</head>
<body>
	<div id="header">
		<h4 align="center" style="font: bolder; font-style: italic;">Επεξεργασία
			στοιχείων στόχου με id ${param.id}</h4>
	</div>
	<p style="font-size: 25; color: green; text-align: center;">
		${editGoalSuccessMessage}</p>
	<p style="font-size: 25; color: red; text-align: center;">${editGoalFailMessage}</p>
	<form action="EditGoalInfoServlet" method="POST">
		<input type="hidden" value="${param.id}" name="id">
		<table class="editGoalTable" style="width: 750px; height: 900px;">
			<tr>
				<th>Τίτλος:</th>
				<td><input type="text" name="goalTitleTF"
					value="${param.title}" style="width: 220px;" required></td>
			</tr>
			<tr>
				<th>Προθεσμία Ολοκλήρωσης:</th>
				<td><input type="date" name="goalDeadlineTF"
					value="${param.deadline}" required></td>
			</tr>
			<tr>
				<th>Οδηγίες Εκπλήρωσης:</th>
				<td><textarea rows="4" cols="50" name="goalGuidesTF" required
						style="height: 130px;"> ${param.directions}
									</textarea></td>
			</tr>
			<tr>
				<th>Αναθετούμενοι Πόροι:</th>
				<td><input type="text" name="goalResourcesAssignedTF"
					value="${param.resources_needed}" required style="width: 280x;"></td>
			</tr>
			<tr>
				<th>Πολυπλοκότητα Στόχου:</th>
				<td><input type="radio" name="goalComplexityTF" value="1"
					required="required" ${param.complexity == 1 ? 'checked' : ''}>1
					<input type="radio" name="goalComplexityTF" value="2"
					${param.complexity == 2 ? 'checked' : ''}>2 <input
					type="radio" name="goalComplexityTF" value="3"
					${param.complexity == 3 ? 'checked' : ''}>3 <input
					type="radio" name="goalComplexityTF" value="4"
					${param.complexity == 4 ? 'checked' : ''}>4 <input
					type="radio" name="goalComplexityTF" value="5"
					${param.complexity == 5 ? 'checked' : ''}>5</td>
			</tr>
			<tr>
				<th>Σημαντικότητα Στόχου:</th>
				<td><input type="radio" name="goalSignificanceTF" value="1"
					required="required" ${param.significance == 1 ? 'checked' : ''}>1
					<input type="radio" name="goalSignificanceTF" value="2"
					${param.significance == 2 ? 'checked' : ''}>2 <input
					type="radio" name="goalSignificanceTF" value="3"
					${param.significance == 3 ? 'checked' : ''}>3 <input
					type="radio" name="goalSignificanceTF" value="4"
					${param.significance == 4 ? 'checked' : ''}>4 <input
					type="radio" name="goalSignificanceTF" value="5"
					${param.significance == 5 ? 'checked' : ''}>5</td>
			</tr>
			<tr>
				<th>Τύπος Στόχου:</th>
				<td><input type="radio" name="goalTypeTF" value="short-term"
					${param.type == 'short-term' ? 'checked' : ''} required>Βραχυπρόθεσμος
					<input type="radio" name="goalTypeTF" value="medium-term"
					${param.type == 'medium-term' ? 'checked' : ''}>Μεσοπρόθεσμος
					<input type="radio" name="goalTypeTF" value="long-term"
					${param.type == 'long-term' ? 'checked' : ''}>Μακροπρόθεσμος
				</td>
			</tr>
			<tr>
				<th>Τμήμα Εταιρίας που αφορά:</th>
				<td><select name="concerningDepartmentTF" required>
						<option value="${param.concerning_department}">${param.concerning_department}</option>
						<option value="Marketing">Marketing</option>
						<option value="Production">Production</option>
						<option value="Purchasing">Purchasing</option>
						<option value="Research and Development">Research and
							Development</option>
						<option value="Public Relations">Public Relations</option>
						<option value="Finance and Accounting">Finance and
							Accounting</option>
						<option value="Human Resources">Human Resources</option>
						<option value="Information Technology">Information
							Technology</option>
						<option value="Legal">Legal</option>
				</select></td>
			</tr>
			<tr>
				<th>Ποσό Επιβράβευσης Bonus:</th>
				<td><input type="text" name="goalBonusAmmountTF"
					value="${param.bonus_ammount}" required></td>
			</tr>
			<tr>
				<th>Ανάθεση στους:</th>
				<jsp:include page="/assignedGoals.jsp" />
				<td><c:forEach items="${employees}" var="employee">
						<c:forEach items="${assignedEmployees}" var="assignedEmployee">
							<c:choose>
								<c:when test="${employee.am_ika == assignedEmployee}">
									<input type="checkbox" name="employeeAssignCBOX"
										value="<c:out value="${employee.am_ika}" />"
										${employee.am_ika == assignedEmployee ? 'checked' : ''}>
									<c:out
										value="${employee.am_ika} ${employee.lastname} ${employee.firstname}" />
									<br>
								</c:when>
							</c:choose>
						</c:forEach>
						<c:forEach items="${unassignedEmployees}" var="unassignedEmployee">
							<c:if test="${employee.am_ika == unassignedEmployee}">
								<input type="checkbox" name="employeeAssignCBOX"
									value="<c:out value="${unassignedEmployee}" />">
								<c:out
									value="${employee.am_ika} ${employee.lastname} ${employee.firstname}" />
								<br>
							</c:if>
						</c:forEach>
					</c:forEach></td>
			</tr>
			<tr align="center">
				<td colspan="2" style="height: 50px;"><input id="button"
					type="submit" name="operation" value="Edit"
					style="width: 130px; height: 40px;"> <input id="button"
					type="reset" value="RESET" style="height: 40px;"></td>
			</tr>
		</table>
	</form>
</body>
</html>