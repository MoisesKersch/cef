<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="../../../template/head.jsp"></jsp:include>
    <jsp:include page="../../../template/css.jsp"></jsp:include>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/serein/template/css/horizontal-layout/style.css">
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
                                <div class="container text-center pt-5">
                                    <h4 class="mb-3 mt-5">Serviços Disponíveis</h4>
                                    <p class="w-75 mx-auto mb-5">Os serviços estão sendo listados pela menor distância
                                        em relação ao local que você cadastrou o seu perfil no sistema. Ao contratar um
                                        serviço o cliente que solicitou será notificado, e seu contato será enviado para
                                        o mesmo para dar continuidade.</p>

                                    <div class="row pricing-table">
                                        <c:forEach items="${servicos}" var="servico">
                                            <div class="col-md-4 grid-margin stretch-card pricing-card">
                                                <div class="card border border-success pricing-card-body">
                                                    <div class="text-center pricing-card-head">
                                                        <h3 class="text-success">Serviço</h3>
                                                        <p>Valor</p>
                                                        <h1 class="font-weight-normal mb-4">
                                                            R$ ${servico.tipoServico.valor}</h1>
                                                    </div>
                                                    <ul class="list-unstyled plan-features">
                                                        <li>Nome: <strong>${servico.tipoServico.nome}</strong></li>
                                                        <li>Nome do Cliente: <strong>${servico.user.nome}</strong></li>
                                                        <li>Tipo do Pet: <strong>${servico.animal.tipo}</strong></li>
                                                        <li>Distância: <strong>
                                                            <fmt:formatNumber type="number"

                                                                              minFractionDigits="2"
                                                                              maxFractionDigits="2"
                                                                              value="${servico.distancia}"/> KM
                                                        </strong>
                                                        <li>Localização:
                                                            <button type="button"
                                                                    class="btn btn-success btn-rounded btn-icon"
                                                                    onclick="window.open('http://www.google.com/maps/place/${servico.user.latitude},${servico.user.longitude}', '_blank')"
                                                                    data-toggle="tooltip" data-placement="bottom"
                                                                    title="Abrir no Google Maps">
                                                                <i class="mdi mdi-map-marker"></i>
                                                            </button>

                                                        </li>
                                                        <li>Data:
                                                            <fmt:formatDate value="${servico.data}" type="date"
                                                                            pattern="dd/MM/yyyy hh:mm a"/>
                                                        </li>
                                                    </ul>
                                                    <div class="wrapper">
                                                        <a href="#" class="btn btn-success btn-block">Contratar</a>
                                                    </div>

                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
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
