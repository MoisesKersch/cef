<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="../../../template/head.jsp"></jsp:include>
    <jsp:include page="../../../template/css.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/serein/template/css/horizontal-layout/style.css">
</head>

<body>
<div class="container-scroller">
    <div class="horizontal-menu">
    <jsp:include page="../../../template/header.jsp"></jsp:include>
    <jsp:include page="../../../template/menu.jsp"></jsp:include>
    </div>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row grid-margin">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Cadastro de Funcionários</h4>
                                <form class="cmxform" id="${formId}">
                                    <fieldset>
                                        <div class="form-group">
                                            <label for="nome">Nome*</label>
                                            <input id="nome" class="form-control" name="nome"  type="text" required placeholder="Nome do Funcionário">
                                        </div>

                                        <div class="form-group">
                                            <label for="cpf">CPF*</label>
                                            <input id="cpf" class="form-control cpf" name="cpf"  type="text" placeholder="CPF do Funcionário">
                                        </div>

                                        <div class="form-group">
                                            <label for="Setor">Setor*</label>
                                            <input id="setor" class="form-control" name="setor"  type="text" required placeholder="Setor do Funcionário">
                                        </div>

                                        <div class="form-group ">
                                            <label for="cargo">Cargo*</label>
                                            <input id="cargo" class="form-control" name="cargo"  type="text" required placeholder="Cargo do Funcionário">
                                        </div>


                                        <div class="form-group ">
                                            <label for="data-admissao">Data de Admisão*</label>
                                                <input type="text" class="form-control" id="data-admissao" placeholder="Data de Admisão do Funcionário"  name="dataAdmissao" class="input-group date datepicker" required >
                                        </div>


                                        <div class="form-group">
                                            <label for="observacao">Observação</label>
                                            <textarea id="observacao" class="form-control" name="observacao"  placeholder="Observação opcional sobre o Funcionário" type="text"></textarea>
                                        </div>

                                        <button class="btn btn-primary" type="submit"> Salvar </button>
                                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/app/animal/list"> Listar </a>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="../../../template/footer.jsp"></jsp:include>
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>

<jsp:include page="../../../template/js.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/serein/template/js/pages/${js}"></script>
</body>

</html>
