<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script src="js/scriptMenu.js"></script>
<title>Login</title>
</head>

<body>
	<div id="menu">
		<ul>
			<li  class="active"><a href="login.jsp" accesskey="1">Είσοδος</a></li>
			<li><a href="register.jsp" accesskey="2">Εγγραφή</a></li>
		</ul>
	</div>
	<div id="wrapper">

		<div id="header" align="center" style="width: 100%; ">
			<h2>Είσοδος Μέλους </h2>
		</div>
		<p style="font-size: 25; color: red; text-align: center;">${noSessionMessage}</p>
		<form name="loginform" action="LoginServlet" method="post"
			style="width: 100%; height: 500px;">
			<table title="login" align="center" style="width: 577px; height: 254px;">
				<tbody>
					<tr class="alt">
						<td id="input">Όνομα Χρήστη: </td>
						<td><input id="tf" type="text" name="userTF" placeholder="Πληκτρολογείστε κείμενο" style="height: 25px; " required="required"></td>
					</tr>
					<tr>
						<td id="input">Κωδικός Πρόσβασης: </td>
						<td><input id="tf" type="password" name="passTF" placeholder="Πληκτρολογείστε κωδικό" style="height: 25px; " required="required"></td>
					</tr>
					<tr class="alt">
						<td colspan=2 style="color: red; height: 10px;"><div align="center"><%=(request.getAttribute("errorMessage") == null) ? "" : request.getAttribute("errorMessage")%></div></td>
					</tr>
					<tr align="center">
						<td colspan=2 align="center"> <input type="submit" id="button" value="Login" style="width: 100px; height: 40px;"><input
							type="reset" value="Reset" id="button" style="width: 75px; height: 40px;"></td>
					</tr>
					<tr>
						<td colspan=2 style="height: 3px;"><p align="center">Είστε νέο μέλος; Πραγματοποιείστε εγγραφή <a href="register.jsp">εδώ</a></p>
						</td>
					</tr>

				</tbody>
			</table>
		</form>
		<footer class="footer" style="bottom: 0px;"><jsp:include page="footer.html"/></footer>
		<!--End of div footer-->
	</div>
	<!--End of div wrapper-->
</body>

</html>