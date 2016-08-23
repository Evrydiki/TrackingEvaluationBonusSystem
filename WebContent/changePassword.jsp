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
<title>Αλλαγή κωδικού</title>
<script>
		function checkPassword() {
			var pass1 = document.getElementById('pass1');
			var pass2 = document.getElementById('pass2');
			var message = document.getElementById('confirmPassMessage');
			var goodColor = "#66cc66";
			var badColor = "#ff6666";
			if (pass1.value == pass2.value) {
				pass2.style.backgroundColor = goodColor;
				message.style.color = goodColor;
				message.innerHTML = "Τα passwords ταιριάζουν"
			} else {
				pass2.style.backgroundColor = badColor;
				message.style.color = badColor;
				message.innerHTML = "Τα passwords δεν ταιριάζουν"
			}
		}
</script>
</head>

<body>
	<div id="menu">
		<c:if test="${role == 'employee'}">
		<ul>
			<li><a href="homeEmployee.jsp" accesskey="1">Αρχική Σελίδα </a></li>
			<li class='has-sub'><a href='company.jsp'>Η Εταιρία</a>
				<ul>
					<li class='active has-sub'><a href='company.jsp'>Προβολή
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
		<c:if test="${role == 'manager'}">
			<ul>
				<li><a href="homeManager.jsp">Αρχική Σελίδα </a></li>
				<li class='has-sub'><a href='company.jsp'>Η Εταιρία</a>
					<ul>
						<li class='has-sub'><a href='company.jsp'>Προβολή
								στοιχείων</a>
							<ul>
								<li><a href='businessStrategy.jsp'>Επιχειρησιακή
										Στρατηγική</a></li>
							</ul></li>
						<li><a href='businessInfo.jsp'>Πληροφορίες</a></li>
					</ul></li>
				<li><a href='employeesTabbed.jsp'>Εργαζόμενοι</a></li>
				<li><a href='goalsTabbed.jsp'>Στόχοι</a></li>
				<li class='active has-sub'><a href='managerProfile.jsp'>Προφίλ
						${username}</a>
					<ul>
						<li><a href='changePassword.jsp'>Αλλαγή Κωδικού</a></li>
						<li><a href='logout.jsp'>Αποσύνδεση</a></li>
					</ul></li>
			</ul>
		</c:if>
	</div>
	<jsp:include page="/empList.jsp"/>
	<form action="ChangePasswordServlet" method="post">
		<table title="login" align="center"
			style="width: 577px; height: 254px;">
			<tbody>
			<tr><td colspan="2">
			<p style="font-size: 25; color: green; text-align: center;">
			${successMessage}</p>
		<p style="font-size: 25; color: red; text-align: center;">${errorMessage}</p>
			
			</td>
			</tr>
				<tr>
					<td align="center">Κωδικός Πρόσβασης:</td>
					<td><input type="password" name="passwordTF"
						placeholder="Πληκτρολογείστε κωδικό" style="height: 25px;"
						required="required"></td>
				</tr>
				<tr>
					<th>Νέος Κωδικός Πρόσβασης:</th>
					<td><input type="password" id="pass1" placeholder="Password"
						name="passTF" required="required"></td>
				</tr>
				<tr>
					<th>Ξανά πληκτρολογήστε τον νέο κωδικό πρόσβασης:</th>
					<td><input type="password" id="pass2"
						placeholder="Confirm Password" name="confirmpassTF"
						onkeyup="checkPassword(); return false;"> <span
						id="confirmPassMessage" class="confirmPassMessage"></span></td>
				</tr>
				<tr align="center">
					<td colspan=2 align="center"><input type="submit" id="button"
						value="ΑΛΛΑΓΗ ΚΩΔΙΚΟΥ" style="width: 133px; height: 40px;"><input
						type="reset" value="RESET" id="button"
						style="width: 75px; height: 40px;"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>