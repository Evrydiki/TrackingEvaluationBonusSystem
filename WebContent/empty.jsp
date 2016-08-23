<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<br>
	<p style="font-size: large; color: blue; text-align: center;">${message}</p>
	<p style="font-size: large; color: green; text-align: center;">
		${editGoalSuccessMessage}</p>
	<p style="font-size: large; color: red; text-align: center;">${editGoalFailMessage}</p>

	<p style="font-size: large; color: green; text-align: center;">
		${deleteSuccessMessage}</p>
	<p style="font-size: large; color: red; text-align: center;">${deleteFailMessage}</p>
</body>
</html>