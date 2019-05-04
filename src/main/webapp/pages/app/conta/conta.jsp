<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="../../../template/head.jsp"></jsp:include>
    <jsp:include page="../../../template/css.jsp"></jsp:include>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/serein/template/css/horizontal-layout/style.css">

    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet/v1.0.0-rc.1/leaflet.css">
    <style>
        #map {
            height: 200px;
            border: 1px solid black;
        }
    </style>
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
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="border-bottom text-center pb-4">
                                            <img src="${pageContext.request.contextPath}/resources/serein/template/images/faces/avatar2.png"
                                                 alt="profile"
                                                 class="img-lg rounded-circle mb-3"/>
                                        </div>

                                        <div class="py-4">
                                            <p class="clearfix">
                                              <span class="float-left">
                                                Nome
                                              </span>
                                                <span class="float-right text-muted">
                                                    ${usuario.nome}
                                                </span>
                                            </p>

                                            <p class="clearfix">
                                              <span class="float-left">
                                                Email
                                              </span>
                                                <span class="float-right text-muted">
                                                    ${usuario.email}
                                                </span>
                                            </p>

                                            <p class="clearfix">
                                              <span class="float-left">
                                                Cep
                                              </span>
                                                <span class="float-right text-muted">
                                                    ${usuario.cep}
                                                </span>
                                            </p>

                                            <p class="clearfix">
                                          <span class="float-left">
                                            Telefone
                                          </span>
                                                <span class="float-right text-muted">
                                                    ${usuario.telefone}
                                                </span>
                                            </p>

                                            <p class="clearfix">
                                          <span class="float-left">
                                            Rua
                                          </span>
                                                <span class="float-right text-muted">
                                                    ${usuario.rua}
                                                </span>
                                        </div>

                                    </div>
                                    <div class="col-lg-8">
                                        <div class="d-flex justify-content-between">
                                            <div>
                                                <h3>${usuario.nome}</h3>
                                                <div class="d-flex align-items-center">
                                                    <h5 class="mb-0 mr-2 text-muted">${usuario.cidade}</h5>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mt-4 py-2 border-top border-bottom">
                                            <ul class="nav profile-navbar">
                                                <li class="nav-item">
                                                    <a class="nav-link" href="#">
                                                        <i class="mdi mdi mdi-map-marker"></i>
                                                        Localização
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="profile-feed">
                                        <div id="map">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- page-body-wrapper ends -->
        </div>
            <jsp:include page="../../../template/footer.jsp"></jsp:include>
        <jsp:include page="../../../template/js.jsp"></jsp:include>
            <script src="http://cdn.leafletjs.com/leaflet/v1.0.0-rc.1/leaflet.js"></script>
            <script src="${pageContext.request.contextPath}/resources/serein/template/js/helpers/usuario.js"></script>
            <script src="${pageContext.request.contextPath}/resources/serein/template/js/helpers/address.js"></script>
            <script>
                let usuario = new Usuario();
                usuario.nome = "${usuario.nome}";
                usuario.email = "${usuario.email}";
                usuario.cep = "${usuario.cep}";
                usuario.cidade = "${usuario.cidade}";
                usuario.telefone = "${usuario.telefone}";
                usuario.rua = "${usuario.rua}";
                usuario.latitude = "${usuario.latitude}";
                usuario.longitude = "${usuario.longitude}";
            </script>
        <script src="${pageContext.request.contextPath}/resources/serein/template/js/pages/${js}"></script>
</body>

</html>
