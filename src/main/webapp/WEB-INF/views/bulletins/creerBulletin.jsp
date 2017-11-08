<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Remplacer la ligne du dessus par celle-ci pour désativer le zoom -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<meta name="description" content="">
<meta name="author" content="">
<!-- Permet d'afficher un icône dans la barre d'adresse -->
<!-- <link rel="shortcut icon" href="image/favicon.png"> -->
<title>Créer Bulletin</title>

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
		<a class="navbar-brand" href="../employes/creer">Employé</a> <a
			class="navbar-brand" href="../bulletins/creer">Bulletin</a>
	</nav>



	<div class="container">
		<a href=""><i class="icon icon-arrow-left" aria-hidden="true"
			style="font-size: 100px; color: black;"></i></a>
		<div class="text-center" style="margin-top: -8%;">
			<h1>Ajouter un bulletin</h1>
		</div>
		<form class="form-horizontal" role="form" name="form" action=""
			method="post">
			<div class="form-group">
				<label for="inputPeriode" class="col-sm-7 control-label">Période</label>
				<div class="col-sm-10">
					<select class="form-control" name="periode">
						<option>Veuillez choisir la période</option>
						<c:forEach var="periode" items="${periodes}">
							<option>${periode.dateDebut}- ${periode.dateFin}</option>
						</c:forEach>

					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="inputMatricule" class="col-sm-7 control-label">Matricule</label>
				<div class="col-sm-10">
					<select class="form-control" name="matricule">
						<option>Veuillez choisir le matricule</option>
						<c:forEach var="matricule" items="${matricules}">
							<option>${matricule}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="inputPrime" class="col-sm-7 control-label">Prime
					Exceptionnelle</label>
				<div class="col-sm-10">
					<input type="text" name="prime" class="form-control" id="prime">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-20">
					<button type="submit" class="btn btn-primary"
						style="margin-left: 54.5%;">
						<i class="icon icon-check icon-lg"></i> Ajouter
					</button>
				</div>
			</div>
			<sec:csrfInput />
		</form>

	</div>
</body>

</html>