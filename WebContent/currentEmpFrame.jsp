<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script src="js/scriptMenu.js"></script>
</head>
<body>
	<form action="post">
		<table title="currentEmpProfile">
			<tr>
				<th>Φωτογραφία:</th>
				<td><img title="profilePicture" height="150" width="150"
					src="PhotoServlet?photoName=${param.photoName}&username=${param.username}&attribute=${param.attribute}"
					style="width: 185px; height: 197px;"
					alt="Αυτός ο χρήστης δεν έχει ανεβάσει κάποια φωτογραφία." /></td>
			</tr>
			<c:if test="${role == 'manager'}">
				<tr>
					<th>CV:</th>
					<td><a
						href="CVServlet?CVName=${param.CVName}&username=${param.username}&attribute=${param.attribute}"><img
							height="20" width="20" src="./images/Floppy-Small-icon.png" /></a></td>
				</tr>
				<tr>
					<th>ΑΜ ΙΚΑ:</th>
					<td><c:out value="${param.am_ika}" /></td>
				</tr>
			</c:if>
			<tr>
				<th>Επώνυμο:</th>
				<td><c:out value="${param.lastname}" /></td>
			</tr>
			<tr>
				<th>Όνομα:</th>
				<td><c:out value="${param.firstname}" /></td>
			</tr>
			<tr>
				<th>Ημερομηνία Γέννησης:</th>
				<td><c:out value="${param.birthday}" /></td>
			</tr>
			<tr>
				<th>E-mail:</th>
				<td><c:out value="${param.email}" /></td>
			</tr>
			<tr>
				<th>Τηλέφωνο:</th>
				<td><c:out value="${param.phone_number}" /></td>
			</tr>
			<tr>
				<th>Τμήμα:</th>
				<td><c:out value="${param.department}" /></td>
			</tr>
			<tr>
				<th>Τίτλος θέσης:</th>
				<td><c:out value="${param.jobPosition}" /></td>
			</tr>
			<tr>
				<th>Φύλο:</th>
				<td><c:out value="${param.sex}" /></td>
			</tr>
			<tr>
				<th>Ημερομηνία Πρόσληψης:</th>
				<td><c:out value="${param.date_of_Recruitment}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>