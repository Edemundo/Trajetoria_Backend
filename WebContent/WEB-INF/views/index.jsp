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
			<c:forEach items="${listContact}" var="Dspt">
			<tr>
				<td>${Dspt.cd_subprefeitura}</td>
				<td>${Dspt.nm_subprefeitura}</td>
				<td>${Dspt.cd_sigla}</td>
				<td>${Dspt.ci_ativo_inativo}</td>
				<td>${Dspt.dt_carga}</td>
				<td>${Dspt.origem_dado}</td>
			</tr>				
			</c:forEach>
		</table>
		<br><br><br><br><br>
		<h1>Serviço de Hospedagem de um Cidadão</h1>
		<table border="1" cellpadding="5">
			<tr>
				<th>Código do Cidadão</th>
				<th>Código do Serviço de Procura</th>
				<th>Código do Motivo de Procura</th>
				<th>Origem da Procura</th>
				<th>Data do Desligamento</th>
			</tr>
		</table>
	</div>
</body>
</html>