<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
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
					<li><a href='businessInfo.html'>Πληροφορίες</a></li>
				</ul></li>
			<li><a href='employeesTabbed.jsp'>Εργαζόμενοι</a></li>
			<li><a href='goalsTabbed.jsp'>Στόχοι</a></li>
			<li class='has-sub'><a href='managerProfile.jsp'>Προφίλ
					${username}</a>
				<ul>
					<li><a href='changePassword.jsp'>Αλλαγή Κωδικού</a></li>
					<li><a href='logout.jsp'>Αποσύνδεση</a></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>