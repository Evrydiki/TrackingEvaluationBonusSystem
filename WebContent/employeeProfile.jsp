<%@ page language="java" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery.quickflip.js"></script>
<script src="js/script.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script src="js/scriptMenu.js"></script>
<title>Προφίλ</title>
</head>
<body>
	<c:if test="${role == 'employee'}">
		<div id="menu">
		<ul>
			<li><a href="homeEmployee.jsp" accesskey="1">Αρχική Σελίδα </a></li>
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
	</div>
	</c:if>

	<jsp:include page="/empList.jsp" />
	<jsp:include page="/goalList.jsp" />
	<p></p>

	<table align="center" title="employeeProfile">
		<tr>
			<th>Φωτογραφία:</th>
			<td><img height="200" width="200" title="profilePicture"
				src="${pageContext.request.contextPath}/profile-photo.jsp/${employee.photoName}"
				alt="Δεν έχετε ανεβάσει κάποια φωτογραφία" /></td>
		</tr>
		<tr>
			<th colspan="1">Σύνολο bonus που σας έχει αποδοθεί:</th>
			<td> <c:if test="${total_bonus > 0}"> <strong>${total_bonus}</strong> Ευρώ  <img height="30" width="35"
				src="./images/images.jpg" /></c:if> <c:if test="${total_bonus == 0}"> Δεν σας έχει αποδοθεί ακόμα Bonus <img height="25" width="25"
				src="./images/Sad_Emoticon.png" /></c:if></td>
		</tr>
		<tr>
			<th>CV:</th>
			<td><a href="profileCV.jsp/${employee.CVName}"><img
					height="20" width="20" src="./images/Floppy-Small-icon.png" /></a></td>
		</tr>
		<tr>
			<th>ΑΜ ΙΚΑ:</th>
			<td><c:out value="${employee.am_ika}" /></td>
		</tr>
		<tr>
			<th>ΑΜΚΑ:</th>
			<td><c:out value="${employee.amka}" /></td>
		</tr>
		<tr>
			<th>ΑΦΜ:</th>
			<td><c:out value="${employee.afm}" /></td>
		</tr>
		<tr>
			<th>Επώνυμο:</th>
			<td><c:out value="${employee.lastname}" /></td>
		</tr>
		<tr>
			<th>Όνομα:</th>
			<td><c:out value="${employee.firstname}" /></td>
		</tr>
		<tr>
			<th>Ημερομηνία Γέννησης:</th>
			<td><c:out value="${employee.birthday}" /></td>
		</tr>
		<tr>
			<th>E-mail:</th>
			<td><c:out value="${employee.email}" /></td>
		</tr>
		<tr>
			<th>Τηλέφωνο:</th>
			<td><c:out value="${employee.phone_number}" /></td>
		</tr>
		<tr>
			<th>Τμήμα:</th>
			<td><c:out value="${employee.department}" /></td>
		</tr>
		<tr>
			<th>Τίτλος θέσης:</th>
			<td><c:out value="${employee.jobPosition}" /></td>
		</tr>
		<tr>
			<th>Φύλο:</th>
			<td><c:out value="${employee.sex}" /></td>
		</tr>
		<tr>
			<th>Ημερομηνία Πρόσληψης:</th>
			<td><c:out value="${employee.date_of_Recruitment}" /></td>
		</tr>
		<tr>
			<th>Μισθός:</th>
			<td><c:out value="${employee.salary}" /></td>
		</tr>
	</table>
	<p align="center">*Αν επιθυμείτε κάποια αλλαγή στα στοιχεία σας
		παρακαλούμε επικοινωνείστε με το διαχειριστή του συστήματος στο:
		admin@yahoo.com</p>
</body>
</html>