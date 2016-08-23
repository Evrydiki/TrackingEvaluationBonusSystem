<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/scriptMenu.js"></script>
</head>
<body>
	<jsp:include page="/empList.jsp" />
	<br>
	<div align="center">
		<div id="header">
			<h4 style="font: bolder; font-style: italic;">Εισάγετε
				τα στοιχεία του νέου στόχου</h4>
		</div>
		<br>
		<p style="font-size: 25; color: green; text-align: center;">
			${submitSuccessGoalMessage}</p>
		<p style="font-size: 25; color: red; text-align: center;">${submitFailGoalMessage}</p>
		<form action="InsertNewGoalServlet" method="post">
			<table class="insertGoalTable" style="width: 840px; height: 535px;">
				<tr>
					<td>Τίτλος στόχου: <span>*</span></td>
					<td><input type="text" name="goalTitleTF" required></td>
				</tr>
				<tr>
					<td>Προθεσμία Ολοκλήρωσης: <span>*</span></td>
					<td><input type="date" name="goalDeadlineTF" required></td>
				</tr>
				<tr>
					<td>Οδηγίες Εκπλήρωσης: <span>*</span></td>
					<td><textarea rows="4" cols="50" name="goalGuidesTF" required>
									</textarea></td>
				</tr>
				<tr>
					<td>Αναθετούμενοι Πόροι: <span>*</span></td>
					<td><input type="text" name="goalResourcesAssignedTF" required></td>
				</tr>
				<tr>
					<td>Πολυπλοκότητα Στόχου: <span>*</span></td>
					<td><input type="radio" name="goalComplexityTF" value="1"
						required="required">1 <input type="radio"
						name="goalComplexityTF" value="2">2 <input type="radio"
						name="goalComplexityTF" value="3">3 <input type="radio"
						name="goalComplexityTF" value="4">4 <input type="radio"
						name="goalComplexityTF" value="5">5</td>
				</tr>
				<tr>
					<td>Σημαντικότητα Στόχου: <span>*</span></td>
					<td><input type="radio" name="goalSignificanceTF" value="1"
						required="required">1 <input type="radio"
						name="goalSignificanceTF" value="2">2 <input type="radio"
						name="goalSignificanceTF" value="3">3 <input type="radio"
						name="goalSignificanceTF" value="4">4 <input type="radio"
						name="goalSignificanceTF" value="5">5</td>
				</tr>
				<tr>
					<td>Τύπος Στόχου: <span>*</span></td>
					<td><input type="radio" name="goalTypeTF" value="short-term"
						required>Βραχυπρόθεσμος <input type="radio"
						name="goalTypeTF" value="medium-term">Μεσοπρόθεσμος <input
						type="radio" name="goalTypeTF" value="long-term">Μακροπρόθεσμος</td>
				</tr>
				<tr>
					<td>Τμήμα Εταιρίας που αφορά: <span>*</span></td>
					<td><select name="concerningDepartmentTF">
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
					<td>Ποσό Επιβράβευσης Bonus: <span>*</span></td>
					<td><input type="text" name="goalBonusAmmountTF" required></td>
				</tr>
				<tr>
					<th>Ανάθεση σε: *</th>
					<td><c:forEach items="${employees}" var="employee">
							<input type="checkbox" name="employeeAssignCBOX"
								value="<c:out value="${employee.am_ika}" />">
							<c:out
								value="${employee.am_ika} ${employee.lastname} ${employee.firstname}" />
							<br>
						</c:forEach></td>
				</tr>
				<tr>
					<td colspan="2" style="height: 50px; text-align: center;"><input
						id="button" type="submit" value="ΥΠΟΒΟΛΗ"
						style="width: 130px; height: 40px;"> <input id="button"
						type="reset" value="RESET" style="height: 40px;"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>