<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./styles.css" />
<script type="text/javascript">
	function findTotal() {
		var arr = document.getElementsByName('rating');
		var tot = 0;
		for (var i = 0; i < arr.length; i++) {
			if (parseInt(arr[i].value))
				tot += parseInt(arr[i].value);
		}
		document.getElementById('total').value = tot*100/120;
	}
</script>
</head>
<body>
	<div id="header">
		<h3 align="center" style="font: bolder; font-style: italic;">Αξιολόγηση
			εργαζομένου με AM IKA ${param.am_ika}</h3>
	</div>
	<p style="font-size: 25; color: green; text-align: center;">
		${evaluationSuccessMessage}</p>
	<p style="font-size: 25; color: red; text-align: center;">${evaluationFailMessage}</p>
	<div id="header" align="center">
		<h4 style="background-color: highlight;">Αξιολόγηση ανά κατηγορία
		</h4>
		<cite>Παρακαλούμε βαθμολογείστε παρακάτω (0-10):</cite>
	</div>
	<br>
	<form action="EvaluateEmployeeServlet" method="POST">
		<input type="hidden" value="${param.am_ika}" name="am_ika">
		<input type="hidden" name="departmentTF" value="${param.department}">
		<table class="ratingTable"
			style="width: 300px; height: 350px; margin-left: 300px">
			<tr>
				<th>Διαθεσιμότητα: <input type="hidden" name="title"
					value="Διαθεσιμότητα"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Ανεξαρτησία: <input type="hidden" name="title"
					value="Ανεξαρτησία"></th>
				<td style="width: 68px; "><input onblur="findTotal()" type="text" name="rating"
					style="width: 50px;" required="required"></td>
			</tr>
			<tr>
				<th>Πρωτοβουλία: <input type="hidden" name="title"
					value="Πρωτοβουλία"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					style="width: 50px;" required="required"></td>
			</tr>
			<tr>
				<th>Γνώση Εργασίας: <input type="hidden" name="title"
					value="Γνώση Εργασίας"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Κρίση: <input type="hidden" name="title"
					value="Κρίση"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Παραγωγικότητα: <input type="hidden" name="title"
					value="Παραγωγικότητα"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Ποιότητα: <input type="hidden" name="title"
					value="Ποιότητα"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Αξιοπιστία: <input type="hidden" name="title"
					value="Αξιοπιστία"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Εργασιακές Σχέσεις: <input type="hidden" name="title"
					value="Εργασιακές Σχέσεις"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Δημιουργικότητα: <input type="hidden" name="title"
					value="Δημιουργικότητα"></th>
				<td><input onblur="findTotal()" type="text" id="rating"
					name="rating" required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Διαχείριση Στόχων: <input type="hidden" name="title"
					value="Διαχείριση Στόχων"></th>
				<td><input onblur="findTotal()" type="text" id="rating"
					name="rating" required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>Ανάληψη πολύπλοκων στόχων: <input type="hidden" name="title"
					value="Ανάληψη πολύπλοκων στόχων"></th>
				<td><input onblur="findTotal()" type="text" name="rating"
					required="required" style="width: 50px;"></td>
			</tr>
			<tr>
				<th style="text-decoration: underline;">Συνολική Βαθμολογία:</th>
				<td><input type="text" id="total" readonly="readonly"
					name="totalTF" style="width: 50px;">%</td>
			</tr>
		</table>
		<br> <br>
		<table class="listTable"
			style="width: 750px; height: 350px; margin-left: 100px;">
			<tr>
				<th>Ημερομηνία αξιολόγησης:</th>
				<td><input type="date" name="dateTF" required="required"></td>
			</tr>
			<tr>
				<th>Τύπος αξιολόγησης:</th>
				<td><select name="typeTF" required="required">
						<option value="Three-month">Τριμηνιαία</option>
						<option value="Six-month">Εξαμηνιαία</option>
						<option value="Annual">Ετήσια</option>
				</select></td>
			</tr>
			<tr>
				<th>Σχόλια:</th>
				<td><textarea name="commentsTF" placeholder="Προσθέστε σχόλια"
						required="required" style="width: 400px; height: 150px;"></textarea></td>
			</tr>
			<tr>
				<th>Επόμενη Αξιολόγηση:</th>
				<td><input type="date" name="nextEvaluationDateTF"
					required="required"></td>
			</tr>
		</table>
		<br> <input id="button" type="submit" name="operation"
			value="ΥΠΟΒΟΛΗ ΑΞΙΟΛΟΓΗΣΗΣ"
			style="width: 180px; height: 40px; margin-left: 350px;"> <input
			id="button" type="reset" value="RESET" style="height: 40px">
	</form>
</body>
</html>