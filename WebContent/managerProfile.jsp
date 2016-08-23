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
<script src="script.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script src="js/scriptMenu.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#menu li").on("click", function() {
        $("#menu li").removeClass("active");
        $(this).addClass("active");
    });
});
</script>
<title>Προφίλ</title>
</head>

<body>
	<c:if test="${role == 'manager'}">
		<div id="menu">
		<ul>
			<li><a href="homeManager.jsp">Αρχική Σελίδα </a></li>
			<li class='has-sub'><a href='company.jsp'>Η Εταιρία</a>
				<ul>
					<li class='has-sub'><a href='company.jsp'>Προβολή
							Στοιχείων</a>
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
	</div>
	</c:if>

	<jsp:include page="/empList.jsp" />
	<table align="center" title="managerProfile"
		style="width: 635px; height: 919px;">
		<tr>
			<th>Φωτογραφία:</th>
			<td><img height="200" width="200" title="profilePicture"
				src="${pageContext.request.contextPath}/profile-photo.jsp/${manager.photoName}" alt="Δεν έχετε ανεβάσει κάποια φωτογραφία" /></td>
		</tr>
		<tr>
			<th>CV:</th>
			<td><a href="profileCV.jsp/${manager.CVName}"><img
					height="20" width="20" src="./images/Floppy-Small-icon.png" /></a></td>
		</tr>
		<tr>
			<th>ΑΜ ΙΚΑ:</th>
			<td><c:out value="${manager.am_ika}" /></td>
		</tr>
		<tr>
			<th>ΑΜΚΑ:</th>
			<td><c:out value="${manager.amka}" /></td>
		</tr>
		<tr>
			<th>ΑΦΜ:</th>
			<td><c:out value="${manager.afm}" /></td>
		</tr>
		<tr>
			<th>Επώνυμο:</th>
			<td><c:out value="${manager.lastname}" /></td>
		</tr>
		<tr>
			<th>Όνομα:</th>
			<td><c:out value="${manager.firstname}" /></td>
		</tr>
		<tr>
			<th>Ημερομηνία Γέννησης:</th>
			<td><c:out value="${manager.birthday}" /></td>
		</tr>
		<tr>
			<th>E-mail:</th>
			<td><c:out value="${manager.email}" /></td>
		</tr>
		<tr>
			<th>Τηλέφωνο:</th>
			<td><c:out value="${manager.phone_number}" /></td>
		</tr>
		<tr>
			<th>Τμήμα:</th>
			<td><c:out value="${manager.department}" /></td>
		</tr>
		<tr>
			<th>Τίτλος θέσης:</th>
			<td><c:out value="${manager.jobPosition}" /></td>
		</tr>
		<tr>
			<th>Φύλο:</th>
			<td><c:out value="${manager.sex}" /></td>
		</tr>
		<tr>
			<th>Ημερομηνία Πρόσληψης:</th>
			<td><c:out value="${manager.date_of_Recruitment}" /></td>
		</tr>
		<tr>
			<th>Μισθός:</th>
			<td><c:out value="${manager.salary}" /></td>
		</tr>
	</table>
	<p align="center">*Αν επιθυμείτε κάποια αλλαγή στα στοιχεία σας παρακαλούμε επικοινωνείστε με το διαχειριστή του συστήματος στο: admin@yahoo.com</p>
</body>