<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html style="height: 532px; ">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="js/scriptMenu.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<title>Αποσύνδεση χρήστη</title>
</head>
<body>
<div>
	<%  request.logout();
session.invalidate();%>
	<jsp:include page="/login.jsp"/>
	</div>
	</body>
</html>