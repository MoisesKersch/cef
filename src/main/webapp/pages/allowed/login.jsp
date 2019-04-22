<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Dogs And Serelepes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/serein/template/vendors/iconfonts/mdi/font/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/serein/template/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/serein/template/vendors/css/vendor.bundle.addons.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/serein/template/css/horizontal-layout/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/serein/template/images/favicon.png" />
</head>
<body>
<div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="main-panel">
            <div class="content-wrapper d-flex align-items-center auth">
                <div class="row w-100">
                    <div class="col-lg-4 mx-auto">
                        <div class="auth-form-light text-left p-5">
                            <div class="brand-logo">
                                <img src="${pageContext.request.contextPath}/resources/serein/template/images/logo.svg" alt="logo">
                            </div>
                            <h4>Olá! Seja bem vindo ao Dog's and Serelep's</h4>
                            <h6 class="font-weight-light">Faça seu login para continuar.</h6>
                            <form class="pt-3" action="${pageContext.request.contextPath}/login" method="post" id="${formId}">
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-lg" id="exampleInputEmail1" name="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" name="senha" placeholder="Senha">
                                </div>
                                <div class="mt-3">
                                    <button class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">ENTRAR</button>
                                </div>
                                <div class="my-2 d-flex justify-content-between align-items-center">
                                    <div class="form-check">
                                        <label class="form-check-label text-muted">
                                            <input type="checkbox" class="form-check-input">
                                            Lembrar-me
                                        </label>
                                    </div>
                                    <a href="#" class="auth-link text-black">Esqueceu a Senha?</a>
                                </div>
                                <div class="text-center mt-4 font-weight-light">
                                    Não possui um conta ainda? <a href="${pageContext.request.contextPath}/allowed/register" class="text-primary">Criar</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>
<jsp:include page="../../template/js.jsp"></jsp:include>
</body>

</html>
