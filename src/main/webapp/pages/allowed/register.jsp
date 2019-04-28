<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="../../template/head.jsp"></jsp:include>
    <jsp:include page="../../template/css.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/serein/template/css/vertical-layout-light/style.css">
</head>

<body>
<div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
            <div class="row w-100">
                <div class="col-lg-4 mx-auto">
                    <div class="auth-form-light text-left p-5">
                        <div class="brand-logo">
                            <img src="${pageContext.request.contextPath}/resources/serein/template/images/logo.svg" alt="logo">
                        </div>
                        <h4>Novo por aqui?</h4>
                        <h6 class="font-weight-light">Cadastre-se, é muito simples e rápido.</h6>
                        <form class="pt-3" id="${formId}">
                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="nome" name="nome" placeholder="Nome*" required>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Email*" required>
                            </div>


                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg phone_with_ddd" id="telefone" name="telefone" placeholder="Telefone*" required>
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg cep" id="cep" name="cep" placeholder="Cep*" required>
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg typeahead-basic" id="cidades" name="cidade" placeholder="Começe a digitar o nome de sua cidade..." required>
                            </div>

                            <input type="hidden" id="cidadeId">

                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="endereco" name="endereco" placeholder="Endereço" required>
                            </div>

                            <div class="form-group">
                                <input type="password" class="form-control form-control-lg" id="senha" name="senha" placeholder="Senha*" required>
                             </div>

                            <div class="form-group">
                                <input type="password" class="form-control form-control-lg" id="senha2" name="senha2" placeholder="Repetir Senha" required>
                            </div>
                            <div class="mb-4">
                                <div class="form-check">
                                    <label class="form-check-label text-muted">
                                        <input type="checkbox" class="form-check-input">
                                        Eu concordo com os Termos & Condições
                                    </label>
                                </div>
                            </div>
                            <div class="mt-3">
                                <button type="submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">Enviar</button>
                            </div>
                            <div class="text-center mt-4 font-weight-light">
                                Já está cadastrado? <a href="${pageContext.request.contextPath}/allowed/login" class="text-primary">Login</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>
<jsp:include page="../../template/js.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/serein/template/vendors/js/typehead/typeahead.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/serein/template/vendors/js/typehead/bloodhound.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/serein/template/vendors/js/typehead/handlebars.js"></script>
<script src="${pageContext.request.contextPath}/resources/serein/template/js/pages/${js}"></script>
</body>

</html>

