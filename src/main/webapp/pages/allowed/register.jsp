<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="../../template/head.jsp"></jsp:include>
    <jsp:include page="../../template/css.jsp"></jsp:include>
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
                        <form class="pt-3">
                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="exampleInputUsername1" placeholder="Nome">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Email">
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="zipcode" placeholder="Cep">
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="city" placeholder="Começe a digitar o nome de sua cidade...">
                            </div>

                            <div class="form-group">
                                <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Senha">
                             </div>

                            <div class="form-group">
                                <input type="password" class="form-control form-control-lg" id="exampleInputPassword1=2" placeholder="Password">
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
                                <a class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" href="../../index.html">Enviar</a>
                            </div>
                            <div class="text-center mt-4 font-weight-light">
                                Já está cadastrado? <a href="login.html" class="text-primary">Login</a>
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
<script src="${pageContext.request.contextPath}/resources/serein/template/js/${js.jsp}"></script>
</body>

</html>
