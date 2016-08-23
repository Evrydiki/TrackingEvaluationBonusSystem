<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/scriptMenu.js"></script>
<title></title>
</head>
<body>
	<div id="sidebar" style="height: 100%; float: left;">
		<table class="listTable">
			<tr>
				<th>AM IKA</th>
				<th>Επώνυμο</th>
				<th>Όνομα</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${activeManagers}" var="activeManager">
				<form action="DeleteEmployeeManagerServlet" method="POST" target="iframe_b">
					<tr>
						<td><input type="hidden" value="${activeManager.am_ika}"
							name="am_ika"> <c:out value="${activeManager.am_ika}" /></td>
						<td><c:out value="${activeManager.lastname}" /></td>
						<td><c:out value="${activeManager.firstname}" /></td>
						<td style="font-size: small; word-wrap: break-word;"><c:url
								value="currentEmpFrame.jsp" var="managerUrl">
								<c:param name="am_ika" value="${activeManager.am_ika}" />
								<c:param name="firstname" value="${activeManager.firstname}" />
								<c:param name="username" value="${activeManager.username}" />
								<c:param name="amka" value="${activeManager.amka}" />
								<c:param name="afm" value="${activeManager.afm}" />
								<c:param name="lastname" value="${activeManager.lastname}" />
								<c:param name="birthday" value="${activeManager.birthday}" />
								<c:param name="email" value="${activeManager.email}" />
								<c:param name="phone_number"
									value="${activeManager.phone_number}" />
								<c:param name="department" value="${activeManager.department}" />
								<c:param name="jobPosition" value="${activeManager.jobPosition}" />
								<c:param name="photoName" value="${activeManager.photoName}" />
								<c:param name="CVName" value="${activeManager.CVName}" />
								<c:param name="sex" value="${activeManager.sex}" />
								<c:param name="date_of_Recruitment"
									value="${activeManager.date_of_Recruitment}" />
								<c:param name="attribute" value="${activeManager.attribute}" />
							</c:url> <c:url value="editRegistrationsFrame.jsp" var="editManagerUrl">
								<c:param name="am_ika" value="${activeManager.am_ika}" />
								<c:param name="firstname" value="${activeManager.firstname}" />
								<c:param name="username" value="${activeManager.username}" />
								<c:param name="amka" value="${activeManager.amka}" />
								<c:param name="afm" value="${activeManager.afm}" />
								<c:param name="lastname" value="${activeManager.lastname}" />
								<c:param name="birthday" value="${activeManager.birthday}" />
								<c:param name="email" value="${activeManager.email}" />
								<c:param name="phone_number"
									value="${activeManager.phone_number}" />
								<c:param name="department" value="${activeManager.department}" />
								<c:param name="jobPosition" value="${activeManager.jobPosition}" />
								<c:param name="CVName" value="${activeManager.CVName}" />
								<c:param name="sex" value="${activeManager.sex}" />
								<c:param name="date_of_Recruitment"
									value="${activeManager.date_of_Recruitment}" />
								<c:param name="attribute" value="${activeManager.attribute}" />
							</c:url> <!-- view --> <a href="${managerUrl}" target="iframe_b">view</a>/<!--edit-->
							<a href="${editManagerUrl}" target="iframe_b">edit</a></td>
						<td>
							<!--delete-->
							<button type="submit" name="operation" value="DeleteManager">
								<img src="./images/Delete_icon.png" class="delete_image">
							</button>
						</td>
					</tr>
				</form>
			</c:forEach>
		</table>
	</div>

	<div id="main" style="position: relative; left: 420px; width: 1000px;">
		<iframe id="emp" width="100%" height="800px" src="" name="iframe_b">
		</iframe>
	</div>
</body>
</html>