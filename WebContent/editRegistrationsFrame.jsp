<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./styles.css" />
</head>
<body>
	<div id="header">
		<h3 align="center" style="font: bolder; font-style: italic;">Επεξεργασία
			στοιχείων εργαζομένου με AM IKA ${param.am_ika}</h3>
	</div>
	<p style="font-size: 25; color: green; text-align: center;">
		${editEmployeeSuccessMessage}</p>
	<p style="font-size: 25; color: red; text-align: center;">${editEmployeeFailMessage}</p>
	<form action="EditRegistrations" method="POST">
		<input type="hidden" value="${param.am_ika}" name="am_ika">
		<table class="editGoalTable" style="width: 750px; height: 900px;">
			<tr>
				<th>ΑΜΚΑ:</th>
				<td><input type="text" name="amkaTF" value="${param.amka}"></td>
			</tr>
			<tr>
				<th>ΑΦΜ:</th>
				<td><input type="text" name="afmTF" value="${param.afm}"></td>
			</tr>
			<tr>
				<th>Όνομα Χρήστη:</th>
				<td><input type="text" name="userTF" value="${param.username}"></td>
			</tr>
			<tr>
				<th>Όνομα:</th>
				<td><input type="text" name="firstnameTF"
					value="${param.firstname}"></td>
			</tr>
			<tr>
				<th>Επίθετο:</th>
				<td><input type="text" name="lastnameTF"
					value="${param.lastname}"></td>
			</tr>
			<tr>
				<th>Ημερομηνία Γέννησεως:</th>
				<td><input type="date" name="birthdayTF"
					value="${param.birthday}"></td>
			</tr>
			<tr>
				<th>e-mail:</th>
				<td><input type="text" name="emailTF" value="${param.email}"></td>
			</tr>
			<tr>
				<th>Αριθμός Τηλεφώνου:</th>
				<td><input type="text" name="phoneNumberTF"
					value="${param.phone_number}"></td>
			</tr>
			<tr>
				<th>Τμήμα:</th>
				<td><select name="departmentTF">
						<option value="${param.department}">${param.department}</option>
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
				<th>Τίτλος θέσης:</th>
				<td><input type="text" name="jobPositionTF"
					value="${param.jobPosition}"></td>
			</tr>
			<tr>
				<th>Ημερομηνία Πρόσληψης:</th>
				<td><input type="date" name="dateOfRecruitmentTF"
					value="${param.date_of_Recruitment}"></td>
			</tr>
			<tr align="center">
				<td colspan="2" style="height: 50px;"><c:if
						test="${param.attribute == 'employee'}">
						<input id="button" type="submit" name="operation"
							value="Edit Employee" style="width: 130px; height: 40px;">
					</c:if> <c:if test="${param.attribute == 'manager'}">
						<input id="button" type="submit" name="operation"
							value="Edit Manager" style="width: 130px; height: 40px;">
					</c:if> <input id="button" type="reset" value="RESET"
					style="height: 40px;"></td>
			</tr>
		</table>
	</form>
</body>
</html>