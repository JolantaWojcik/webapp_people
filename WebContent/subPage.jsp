<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sub page</title>
</head>
<body>
	<div id="resultPage"
		style="width: 550px; margin-left: auto; margin-right: auto; background-color: #EEEEEE; border: 1px solid">
		<table style="width: 550px; margin-left: auto; margin-right: auto;"
			border="1" cellspacing="0" cellpadding="0">
			<tr bgcolor="orange">
				<td>Miesiac: </td>
				<td>Liczba urodzin: </td>
			</tr>
			<c:forEach items="${birthByMonth}" var="b">
				<tr>
				<form action="showDetails" method="get">
					<td>
					<input type="submit" value="${b.key}" name="month"/>
					</td>
					<td>${b.value}</td>
				</tr>
			</c:forEach>
			<form action="">
				<tr>
					<td colspan="5" align="center"><input type="hidden"
						name="saveStatistics" value="save" /> <input type="submit" value="Zapisz"
						onclick="window.location.href='/webapp05/';" /></td>
				</tr>
			</form>
		</table>
	</div>

	<div id="details"
			style="width: 550px; margin-left: auto; margin-right: auto; background-color: #EEEEEE; border: 1px solid">
			<table style="width: 550px; margin-left: auto; margin-right: auto;"
				border="1" cellspacing="0" cellpadding="0">
				<tr bgcolor="orange">
				<td>Imie</td>
				<td>Data urodzenia</td>
				<td>Wiek</td>
				<td>Wzrost</td>
			</tr>
				<c:forEach items="${peopleDetaildList}" var="p">
					<tr>
						<td>${p.name}</td>
						<td>${p.birthdate}</td>
						<td>${p.age}</td>
						<td>${p.height}</td>
					</tr>
				</c:forEach>
			<form action="">
				<tr>
					<td colspan="5" align="center"><input type="hidden"
						name="savePeopleByMonth" value="save" /> <input type="submit" value="Zapisz"
						onclick="window.location.href='/webapp05/';" /></td>
				</tr>
			</form>
		</table>
</body>
</html>