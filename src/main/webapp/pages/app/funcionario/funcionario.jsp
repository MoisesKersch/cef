<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


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
                                <form:form  modelAttribute="obj" class="cmxform" id="${formId}" >
                                    <form:hidden path="id" id="id" />
                                    <fieldset>
                                        <div class="form-group">
                                            <label for="nome">Nome*</label>
                                            <form:input path="nome" id="nome" class="form-control required" name="nome"  type="text" placeholder="Nome do Funcionário"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="cpf">CPF*</label>
                                            <form:input path="cpf" class="form-control cpf required" name="cpf"  type="text" placeholder="CPF do Funcionário"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="Setor">Setor*</label>
                                            <form:input path="setor" id="setor" class="form-control required" name="setor"  type="text"  placeholder="Setor do Funcionário"/>
                                        </div>

                                        <div class="form-group ">
                                            <label for="cargo">Cargo*</label>
                                            <form:input path="cargo" id="cargo" class="form-control required" name="cargo"  type="text"  placeholder="Cargo do Funcionário"/>
                                        </div>

                                        <div class="form-group ">
                                            <label for="data-admissao">Data de Admisão*</label>
                                            <form:input path="dataAdmissao" type="text" id="data-admissao" placeholder="Data de Admisão do Funcionário"  name="dataAdmissao" class="form-control input-group datepicker" />
                                        </div>

                                        <div class="form-group">
                                            <label for="observacao">Observação</label>
                                            <form:textarea path="observacao" id="observacao" class="form-control" name="observacao"  placeholder="Observação opcional sobre o Funcionário" type="text"/>
                                        </div>

                                        <button class="btn btn-primary" type="submit"> Salvar </button>
                                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/app/funcionario/list"> Listar </a>
                                    </fieldset>
                                </form:form>
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
