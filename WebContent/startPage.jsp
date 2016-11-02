<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start page</title>
</head>
<body>
	<div style="width: 600px; margin-left: auto; margin-right: auto;">
		<div id="addForm"
			style="width: 550px; margin-left: auto; margin-right: auto; background-color: #EEEEEE; border: 1px solid">
			<form action="addPerson" method="post">
				<table style="width: 500px; margin-left: auto; margin-right: auto;">
					<tr>
						<td>Wpisz imie</td>
						<td><input type="text" name="name" /> <c:if
								test="${error_name!=null}">
								<font color="red">${error_name}</font>
							</c:if></td>
					</tr>
					<tr>
						<td>Wpisz date</td>
						<td><input type="text" name="datepicker" /><c:if
								test="${error_date!=null}">
								<font color="red">${error_date}</font>
							</c:if></td>
					</tr>
					<tr>
						<td>Wpisz wzrost</td>
						<td><input type="text" name="height" />
						<c:if test="${error_height!=null}">
								<font color="red">${error_height}</font>
							</c:if></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Dodaj" /></td>
					</tr>
				</table>
			</form>
		</div>

		<div id="resultPage"
			style="width: 550px; margin-left: auto; margin-right: auto; background-color: #EEEEEE; border: 1px solid">
			<table style="width: 550px; margin-left: auto; margin-right: auto;"
				border="1" cellspacing="0" cellpadding="0">
				<tr bgcolor="orange">
					<td onclick="document.location.href='/webapp05/?sortBy=name'"
						onmouseover="this.style.cursor='pointer'"
						onmouseout="this.style.cursor='default'">Imie</td>
						<td onclick="document.location.href='/webapp05/?sortBy=birthdate'"
						onmouseover="this.style.cursor='pointer'"
						onmouseout="this.style.cursor='default'">Data urodzenia</td>
					<td onclick="document.location.href='/webapp05/?sortBy=age'"
						onmouseover="this.style.cursor='pointer'"
						onmouseout="this.style.cursor='default'">Wiek</td>
						<td onclick="document.location.href='/webapp05/?sortBy=height'"
						onmouseover="this.style.cursor='pointer'"
						onmouseout="this.style.cursor='default'">Wzrost</td>
				</tr>
				<c:forEach items="${peopleList}" var="p">
					<tr>
						<td>${p.name}</td>
						<td>${p.birthdate}</td>
						<td>${e.age}</td>
						<td>${e.height}</td>
						<td><input type="submit" value="Usun" /></td>
					</tr>
					<td><input type="submit" value="Zapisz" /></td>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>