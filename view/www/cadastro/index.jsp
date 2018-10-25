<!DOCTYPE html>
<%@page import="java.util.*" %>
<%@page import="javax.swing.*" %>
<%@page import="model.Database" %>

<html lang="pt">
	<head>
		<title>e-Game</title>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="../../assets/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="../../assets/css/style.css">
		<link rel="stylesheet" type="text/css" href="../../assets/css/inicio-index.css">
		<link rel="stylesheet" type="text/css" href="../../assets/css/datatables.css">
		<link rel="stylesheet" type="text/css" href="./css/game.css">
		<script src="../../assets/js/jquery-3.3.1.js"></script>
		<script src="../../assets/js/bootstrap.js"></script>
		<script src="../../assets/js/script.js"></script>
		<script src="../../assets/js/inicio-index.js"></script>
		<script src="../../assets/js/datatables.js"></script>
		<script src="./js/game.js"></script>
	</head>
	<body style="height: 100%; width: 100%;">
		<div class="row">
			<div class="col-md-12">
				<div class="header">
					<ul class="nav navbar bg-light">
						<li class="nav-item">
							<a class="nav-link text-dark" href="index.jsp">Início</a>
						</li>
						<li class="nav-item">
							<a class="nav-link text-dark" href="../cadastro/index.jsp">Cadastro</a>
						</li>
						<li class="nav-item">
							<a class="nav-link text-dark" href="../consulta/index.jsp">Consulta</a>
						</li>
						<li class="nav-item">
							<a class="nav-link text-dark" href="../relatorio/index.jsp">Relatórios</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row" style="margin-top: 2vh;">
				<div class="col-md-12">
					<div class="table-responsive table-hover table-light table-striped table-sm">
						<table id="datatable-games" style="width: 100%">
							<thead>
								<th>Código</th>
								<th>Nome</th>
								<th>Gênero</th>
								<th>Plataforma</th>
								<th>Ano de lançamento</th>
								<th>Ações</th>
							</thead>
							<tbody>
								<%
									ArrayList<Object> lista = new ArrayList<Object>();
									lista.add("Acao");
									lista.add(18);
									Database.insert("genero", lista);

									for(int i=0;i<30;i++){
										out.print("<tr>");
										for(int j=0;j<6;j++){
											out.print("<td>"+j+"asdasd"+i+"</td>");
										}
										out.print("</tr>");
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script>
		$(document).ready(function() {
			$('#datatable-games').DataTable();
		} );
	</script>
</html>