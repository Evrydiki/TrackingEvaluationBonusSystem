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
			<li class='has-sub'><a href='employeeProfile.jsp'>Προφίλ
					${username}</a>
				<ul>
					<li><a href='evaluationsEmployeeMode.jsp'>Αξιολογήσεις</a></li>
					<li><a href='changePassword.jsp'>Αλλαγή Κωδικού</a></li>
					<li><a href='logout.jsp'>Αποσύνδεση</a></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>