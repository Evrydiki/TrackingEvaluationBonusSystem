<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link rel="stylesheet" type="text/css" href="./styles.css" />
<title>Επιχειρησιακή Στρατηγική</title>
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
				<li><a href='employeesTabbed.jsp'>Εργαζόμενοι</a></li>
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
	<div id="header">
		<h3>Επιχειρησιακή Στρατηγική, Όραμα, Αποστολή και Φιλοσοφία</h3>
	</div>
	<p>
		<strong>Στρατηγική</strong> της επιχείρησης μας είναι η προσφορά
		μεγάλου εύρους προϊόντων διαδικτυακά αλλά και σε φυσικό κατάστημα και
		η άμεση παράδοση τους στους πελάτες μας. Η υψηλή ποιότητα των
		προϊόντων μας είναι μείζονος σημασία για την πορεία της επιχείρησης
		μας και έτσι χρησιμοποιούμε τη γνώση εργασίας, την πείρα και τα
		κατάλληλα συστήματα παραγωγής σε συνδυασμό με το κατάλληλο ανθρώπινο
		δυναμικό για το τελικό αποτέλεσμα των προϊόντων.
	</p>
	<p>
		<strong>Όραμα</strong> της εταιρίας μας είναι η προσφορά των προϊόντων
		σε όλη την έκταση της χώρας μας αλλά και της Ευρώπης παραμένοντας σε
		υψηλό ποσοστό στις προτιμήσεις των Ευρωπαίων πολιτών όσον αφορά τα
		μελισσοκομικά προϊόντα.
	</p>
	<p>
		<strong>Αποστολή</strong> της εταιρίας μας είναι η παροχή υψηλής
		ποιότητας και ποικιλίας αγαθών στους πελάτες μας και η άμεση
		εξυπηρέτηση τους όσον αφορά την παράδοση των προϊόντων με παραγγελία
		που πραγματοποιήθηκε από τους ίδιους.
	</p>
	<p>
		<strong>Φιλοσοφία</strong> στην εταιρία μας αποτελεί το άριστο κλίμα
		εργασίας, η διατμηματική συνεργασία και η ομαδικότητα που υπάρχει για
		την επίτευξη των στόχων. Ακόμα, ο ορισμός και η παρακολούθηση των
		στόχων είναι ένα πολύ σημαντικό κομμάτι στην επιχείρηση μας διότι
		εκπληρώνοντας έναν-έναν τους στόχους αυτούς θέλουμε να εκπληρώσουμε
		και το όραμα μας. Ακόμα, επιτελείται συνεργασία με τον μελισσοκομικό
		συναιτερισμό της Χαλκιδικής για την θέσπιση εκδηλώσεων και εκπαίδευση
		των εργαζομένων.
	</p>

</body>
</html>