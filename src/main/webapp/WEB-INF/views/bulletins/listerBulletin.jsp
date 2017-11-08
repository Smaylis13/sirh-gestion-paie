<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dev.paie.entite.ResultatCalculRemuneration"%>
<%@ page import="dev.paie.service.CalculerRemunerationService"%>"
<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Remplacer la ligne du dessus par celle-ci pour désativer le zoom -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<meta name="description" content="">
<meta name="author" content="">
<!-- Permet d'afficher un icône dans la barre d'adresse -->
<!-- <link rel="shortcut icon" href="image/favicon.png"> -->
<title>Liste des bulletins</title>

<!-- css Bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css"
	rel="stylesheet">

<!-- HTML5 Shim et Respond.js permet à IE8 de supporter les éléments du HTML5 -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light"
		style="margin-left: 12%;">
		<a class="navbar-brand" href="listeEmploye.html">Employé</a> <a
			class="navbar-brand" href="listeBulletin.html">Bulletin</a>
	</nav>



	<div class="container">
		<!-- <a href="index.html"><i class="icon icon-arrow-left" aria-hidden="true" style="font-size:100px;color:black;"></i></a> -->
		<div class="text-center">
			<h1>Liste des Bulletin</h1>
		</div>

		<a href="creerBulletin.html" class="btn btn-info" role="button"
			style="margin-bottom: 2%; margin-left: 84%;">Crée un nouveau
			bulletin</a>

		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th width="20%;">Date / Heure de création</th>
					<th>Période</th>
					<th>Matricule</th>
					<th>Salaire Brut</th>
					<th>Net Impossable</th>
					<th>Net A Payer</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${bulletinSalaires}" var="bulletin">
					<tr>
						<td>${bulletin.dateCreation}</td>
						<td>${bulletin.periode}</td>
						<td>${bulletin.remunerationEmploye.matricule}</td>

						<c:forEach items="${resultats}" var="resultat">
								<c:if test="${resultat.key==bulletin}">

									<td>${resultat.value.salaireBrut}</td>
									<td>${resultat.value.netImposable}</td>
									<td>${resultat.value.netAPayer}</td>

								</c:if>
							</c:forEach>
							<td><a href="<c:url value="/mvc/bulletins/visualiser/${bulletin.id}"/>">Visualiser</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>