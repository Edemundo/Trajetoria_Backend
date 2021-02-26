<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Listagem do DW</title>
</head>
<body>
	<div align="center">
		<h1>Lista de Subprefeituras</h1>
		<table border="1" cellpadding="5">
			<tr>
				<th>Código</th>
				<th>Subprefeitura</th>
				<th>Sigla</th>
				<th>Status</th>
				<th>Data Carga</th>
				<th>Origem do Dado</th>
			</tr>
			<c:forEach items="${listContact}" var="contact">
			<tr>
				<td>${contact.cd_subprefeitura}</td>
				<td>${contact.nm_subprefeitura}</td>
				<td>${contact.cd_sigla}</td>
				<td>${contact.ci_ativo_inativo}</td>
				<td>${contact.dt_carga}</td>
				<td>${contact.origem_dado}</td>
			</tr>				
			</c:forEach>
		</table>
	</div>
</body>
</html>