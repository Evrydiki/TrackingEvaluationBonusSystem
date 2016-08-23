<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="js/scriptMenu.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
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
<script>
	function checkEmail() {
		var email1 = document.getElementById('email1');
		var email2 = document.getElementById('email2');
		var message = document.getElementById('confirmEmailMessage');
		var goodColor = "#66cc66";
		var badColor = "#ff6666";
		if (email1.value == email2.value) {
			email2.style.backgroundColor = goodColor;
			message.style.color = goodColor;
			message.innerHTML = "Τα e-mail ταιριάζουν"
		} else {
			email2.style.backgroundColor = badColor;
			message.style.color = badColor;
			message.innerHTML = "Ελέγξτε αν τα e-mail ταιριάζουν"
		}
	}
</script>
<title>Εγγραφή</title>
</head>

<body>
	<div id="menu">
		<ul>
			<li><a href="login.jsp" accesskey="1">Είσοδος</a></li>
			<li class="active"><a href="register.jsp" accesskey="2">Εγγραφή</a></li>
		</ul>
	</div>


	<div id="header">
		<h1 align="center">Εισαγωγή στοιχείων εργαζομένου</h1>
	</div>

	<p style="color: blue" align="center">
		<%=(request.getAttribute("registerMessage") == null) ? "" : request.getAttribute("registerMessage")%>
	</p>

	<form action="RegisterServlet" method="post"
		enctype="multipart/form-data">

		<table align="center" class="alt">
			<tr>
				<td>Ιδιότητα: <span>*</span></td>
				<td><input type="radio" name="attributeTF" value="manager"
					required="required">Προϊστάμενος<br> <input
					type="radio" name="attributeTF" value="employee">Εργαζόμενος</td>
			</tr>
			<tr>
				<td>AM IKA: <span>*</span></td>
				<td><input type="text" name="amikaTF" required></td>
			</tr>
			<tr>
				<td>ΑΜΚΑ: <span>*</span></td>
				<td><input type="text" name="amkaTF" required></td>
			</tr>
			<tr>
				<td>ΑΦΜ: <span>*</span></td>
				<td><input type="text" name="afmTF" required></td>
			</tr>
			<tr>
				<td>Όνομα Χρήστη: <span>*</span></td>
				<td><input type="text" name="userTF" required></td>
			</tr>
			<tr>
				<td>Κωδικός Πρόσβασης: <span>*</span></td>
				<td><input type="password" id="pass1" placeholder="Password"
					name="passTF" required></td>
			</tr>
			<tr>
				<td>Ξανά πληκτρολογήστε τον κωδικό πρόσβασης: <span>*</span></td>
				<td><input type="password" id="pass2"
					placeholder="Confirm Password" name="confirmpassTF"
					onkeyup="checkPassword(); return false;"> <span
					id="confirmPassMessage" class="confirmPassMessage"></span></td>
			</tr>
			<tr>
				<td>Όνομα: <span>*</span></td>
				<td><input type="text" name="firstnameTF" required></td>
			</tr>
			<tr>
				<td>Επίθετο: <span>*</span></td>
				<td><input type="text" name="lastnameTF" required></td>
			</tr>
			<tr>
				<td>Φύλο: <span>*</span></td>
				<td><input type="radio" name="sexTF" value="male" required>Άντρας<br>
					<input type="radio" name="sexTF" value="female">Γυναίκα</td>
			</tr>
			<tr>
				<td>Ημερομηνία Γέννησεως: <span>*</span></td>
				<td><input type="date" name="birthdayTF" required></td>
			</tr>
			<tr>
				<td>Φωτογραφία εργαζομένου:</td>
				<td><input type="file" name="photo" accept="image/*" required></td>
			</tr>
			<tr>
				<td>CV:</td>
				<td><input type="file" name="CV"
					accept="application/msword, text/plain, application/pdf" required></td>
			</tr>
			<tr>
				<td>e-mail: <span>*</span></td>
				<td><input id="email1" type="email" name="emailTF" required></td>
			</tr>
			<tr>
				<td>Ξανά πληκτρολογήστε το e-mail: <span>*</span></td>
				<td><input id="email2" type="email" name="confirmemailTF"
					required onkeyup="checkEmail(); return false;"> <span
					id="confirmEmailMessage" class="confirmEmailMessage"></span></td>
			</tr>
			<tr>
				<td>Αριθμός Τηλεφώνου: <span>*</span></td>
				<td><input type="text" name="phoneNumberTF" required></td>
			</tr>
			<tr>
				<td>Τμήμα: <span>*</span></td>
				<td><select name="departmentTF">
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
				<td>Τίτλος θέσης: <span>*</span></td>
				<td><input type="text" name="jobPositionTF" required></td>
			</tr>
			<tr>
				<td>Μισθός: <span>*</span></td>
				<td><input type="text" name="salaryTF" required></td>
			</tr>
			<tr>
				<td>Ημερομηνία Πρόσληψης: <span>*</span></td>
				<td><input type="date" name="dateOfRecruitmentTF" required></td>
			</tr>
			<tr align="center">
				<td colspan="2" style="height: 50px;"><input id="button"
					type="submit" value="ΕΓΓΡΑΦΗ" style="width: 130px; height: 40px;">
					<input id="button" type="reset" value="RESET" style="height: 40px;"></td>
			</tr>
		</table>
	</form>
	<br>
</body>
</html>