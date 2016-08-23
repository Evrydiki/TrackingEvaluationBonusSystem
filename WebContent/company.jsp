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
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery.quickflip.js"></script>
<script src="js/scriptMenu.js"></script>
<link rel="stylesheet" type="text/css" href="./styles.css" />

<script type="text/javascript">
	$('document').ready(function() {
		$('#flip-container').quickFlip();

		$('#flip-navigation li a').each(function() {
			$(this).click(function() {
				$('#flip-navigation li').each(function() {
					$(this).removeClass('selected');
				});
				$(this).parent().addClass('selected');
				var flipid = $(this).attr('id').substr(4);
				$('#flip-container').quickFlipper({}, flipid, 1);

				return false;
			});
		});
	});
</script>
<title>Η Εταιρία</title>
</head>

<body>
	<div id="menu">
		<c:if test="${role == 'employee'}">
			<ul>
				<li><a href="homeEmployee.jsp" accesskey="1">Αρχική Σελίδα
				</a></li>
				<li class='active has-sub'><a href='company.jsp'>Η Εταιρία</a>
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
		</c:if>
		<c:if test="${role == 'manager'}">
			<ul>
				<li><a href="homeManager.jsp">Αρχική Σελίδα </a></li>
				<li class='active has-sub'><a href='company.jsp'>Η Εταιρία</a>
					<ul>
						<li class='active has-sub'><a href='company.jsp'>Προβολή
								στοιχείων</a>
							<ul>
								<li><a href='businessStrategy.jsp'>Επιχειρησιακή
										Στρατηγική</a></li>
							</ul></li>
						<li><a href='businessInfo.jsp'>Πληροφορίες</a></li>
					</ul></li>
				<li><a href='employeesTabbed.jsp'>Εργαζόμένοι</a></li>
				<li><a href='goalsTabbed.jsp'>Στόχοι</a></li>
				<li class='has-sub'><a href='managerProfile.jsp'>Προφίλ
						${username}</a>
					<ul>
						<li><a href='changePassword.jsp'>Αλλαγή Κωδικού</a></li>
						<li><a href='logout.jsp'>Αποσύνδεση</a></li>
					</ul></li>
			</ul>
		</c:if>
	</div>

	<div id="contents">
		<table class="companyTable"
			style="text-align: center; width: 840px; height: 535px; margin-left: 280px;">
			<tr>
				<th>Λογότυπο:</th>
				<td><img src="./images/melilogo.png" height="250" width="550">
				</td>
			</tr>
			<tr>
				<th>Τίτλος εταιρίας:</th>
				<td>Μέλι Χαλκιδική</td>
			</tr>
			<tr>
				<th>Τομέας Δραστηριοποίησης:</th>
				<td>Αγροτική Ανάπτυξη Τροφίμων</td>
			</tr>
			<tr>
				<th>Εύρος Προϊόντων:</th>
				<td>Μέλι Ερείκης, Πευκόμελο, Ανθόμελο, Γύρη, Πρόπολη, Βασιλικός
					Πολτός</td>
			</tr>
			<tr>
				<th>Μέγεθος Εταιρίας:</th>
				<td>Μεσαία Επιχείρηση</td>
			</tr>
			<tr>
				<th>Αριθμός υπαλλήλων:</th>
				<td>28</td>
			</tr>
			<tr>
				<th>Έδρα:</th>
				<td>Χαλκιδική</td>
			</tr>
			<tr>
				<th>Τηλέφωνο επικοινωνίας:</th>
				<td>6947000374</td>
			</tr>
		</table>

	</div>
</body>

</html>