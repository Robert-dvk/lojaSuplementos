<%@	page contentType="text/html;charset=UTF-8" language="java" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Brasil Suplements</title>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="paginaInicial.jsp">Brasil Suplements</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="/lojaSuplementos/clientes-visualizar">Clientes</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/lojaSuplementos/produtos-visualizar">Produtos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/lojaSuplementos/categorias-visualizar">Categorias</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/lojaSuplementos/fornecedores-visualizar">Fornecedores</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Vendas</a>
        </li>
      </ul>
      <form class="d-flex" role="search">
        <button class="btn btn-outline-danger" type="submit">Sair</button>
      </form>
    </div>
  </div>
</nav>
<div class="container" style="margin-top: 50px; width: 500px">
  <form method="POST" action="fornecedor-controller">
    <div class="mb-3">
      <label for="nome" class="form-label">Nome:</label>
      <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite o nome do fornecedor...">
    </div>
    <div style="text-align: center">
      <input type="submit" class="btn btn-primary" name="opcao" value="cadastrar">
    </div>
  </form>
  <table class="table">
    <thead>
    <tr style="text-align: center;">
      <th scope="col">Nome</th>
      <th scope="col" colspan="2">Acoes</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="f" items="${Fornecedores}">
      <tr style="text-align: center">
        <td>${f.nome}</td>
        <td><a href="http://localhost:8080/lojaSuplementos/fornecedor-controller?opcao=editar&&id=${f.id}">Editar</a></td>
        <td><a href="http://localhost:8080/lojaSuplementos/fornecedor-controller?opcao=excluir&&id=${f.id}">Excluir</a></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>