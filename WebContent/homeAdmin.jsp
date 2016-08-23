<%@ page language="java" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script src="js/scriptMenu.js"></script>
<title>Καλώς Ήρθατε</title>
</head>

<body>

	<div id="menu">
		<ul>
			<li class="active"><a href="homeAdmin.jsp">Αρχική Σελίδα </a></li>
			<li><a href='acceptRejectRegistrations.jsp'>Έγκριση/Απόρριψη
					Εγγραφών</a></li>
			<li><a href='viewActiveRegistrations.jsp'>Προβολή Ενεργών
					Εγγραφών</a></li>
			<li><a href='logout.jsp'>Αποσύνδεση</a></li>
		</ul>
	</div>

	<p id="welcome">Καλώς Ήρθατε ${username} (<a href='logout.jsp' style="font-style: italic;text-decoration: none;">Αποσύνδεση</a>)</p>

	<div id="contents">
		<jsp:include page="pageTour.jsp"></jsp:include>
		<!-- Οδηγίες χρήσης σε στυλ περιήγησης της εφαρμογής-->
	</div>
	<!--End of div footer-->
</body>

</html>