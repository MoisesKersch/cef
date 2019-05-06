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
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Cadastro de Empréstimos</h4>
                                <form:form  modelAttribute="obj" class="cmxform" id="${formId}" >
                                    <form:hidden path="id" id="id" />
                                    <fieldset>
                                        <div class="form-group ">
                                            <label for="data-operacao">Data da Operação*</label>
                                            <form:input path="dataOperacao" type="text" id="data-operacao" placeholder="Data da Operação"  name="dataOperacao" class="form-control input-group datepicker" />
                                        </div>

                                        <div class="form-group">
                                            <label>Funcionário*</label>
                                            <select class="funcionario" style="width:100%"
                                                    name="funcionario">
                                                <option value="">Selecione um Funcionário</option>
                                                <c:forEach items="${funcionarios}" var="funcionario">
                                                    <option value="${funcionario.id}">${funcionario.nome}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="valorEmprestimo">Valor do Empréstimo*</label>
                                            <form:input path="valorEmprestimo" class="form-control required brl" name="valorEmprestimo"  type="text" placeholder="Valor do Empréstimo em R$"/>
                                        </div>

                                        <div class="form-group">
                                            <label>Quantidade de Parcelas**</label>
                                            <select class="quantidadeParcela" style="width:100%"
                                                    name="quantidadeParcela" required>
                                                <option value="">Selecione um Quantidade</option>
                                                <option value="3">3x</option>
                                                <option value="6">6x</option>
                                                <option value="12">12x</option>
                                                <option value="24">24x</option>
                                            </select>
                                        </div>

                                        <div class="form-group ">
                                            <label for="dataPrimeiraParcela">Data Primeira Parcela*</label>
                                            <form:input path="dataPrimeiraParcela" type="text" id="data-primeira-parcela" placeholder="Data Primeira Parcela"  name="dataPrimeiraParcela" class="form-control input-group  datepicker" />
                                        </div>
                                        
                                        <button class="btn btn-primary" type="button" id="detalhes" > Gerar Detalhes </button>
                                        <button class="btn btn-primary" type="button" id="save" disabled> Salvar Empréstimo </button>
                                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/app/emprestimo/list"> Listar </a>
                                    </fieldset>

                                </form:form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Detalhes</h4>


                                    <div class="table-responsive">

                                        <table id="${tableId}" class="table">
                                            <thead>
                                            <tr class="bg-primary text-white">

                                            </tr>
                                            </thead>
                                        </table>
                                    </div>


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
