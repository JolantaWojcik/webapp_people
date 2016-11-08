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
						<td><input type="text" name="birthdate" /><c:if
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
			<form action="showDetails" method="post" align="right">
				<tr align="right">
					<td colspan="2" align="right"><input type="submit"
						value="Pokaz raport urodzen" /></td>
				</tr>
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
						<td>Akcja</td>
				</tr>
				<c:forEach items="${peopleList}" var="p">
					<tr>
						<td align="center">${p.name}</td>
						<td align="center">${p.birthdate}</td>
						<td align="center">${p.age}</td>
						<td align="center">${p.height}</td>
						<form action="removePerson" method="post">
						 <input type="hidden" name="id" value="${p.id}" />
						<td align="center"><input type="submit" value="Usun" /></td>
						</form>
					</tr>
				</c:forEach>
				<form action=""> 
				<tr>
					<td colspan="5" align="center">
					<input type="hidden" name="save" value="save"/>
					<input type="submit" value="Zapisz" onclick="window.location.href='/webapp05/';"/></td>
				</tr>
				</form>
			</table>
		</div>
	</div>
</body>
</html>