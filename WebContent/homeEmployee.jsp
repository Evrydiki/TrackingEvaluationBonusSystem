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
<script src="js/scriptMenu.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<title>Καλώς Ήρθατε</title>
</head>

<body>

	<c:if test="${role == 'employee'}">
		<div id="menu">
			<ul>
				<li class='active'><a href="homeEmployee.jsp" accesskey="1">Αρχική Σελίδα
				</a></li>
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
	</c:if>

	<p id="welcome">
		Καλώς Ήρθατε ${username} (<a href='logout.jsp'
			style="font-style: italic; text-decoration: none;">Αποσύνδεση</a>)
	</p>

	<div id="contents">
		<jsp:include page="pageTour.jsp"></jsp:include>
		<!-- Οδηγίες χρήσης σε στυλ περιήγησης της εφαρμογής-->
	</div>
	<!--End of div footer-->
</body>

</html>