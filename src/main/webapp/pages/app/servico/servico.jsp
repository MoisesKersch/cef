<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    </div>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Agendamento de Serviços</h4>
                                <form id="${formId}">
                                    <input type="hidden" name="user" value="${user}">
                                    <div>
                                        <h3>Serviço</h3>
                                        <section>
                                            <h3>Tipo do Serviço</h3>
                                            <div class="form-group">
                                                <label>Escolha um Serviço</label>
                                                <select class="tipo-servico" style="width:100%"
                                                        name="tipoServico">
                                                    <c:forEach items="${tipoServicos}" var="tipoServico">
                                                        <option value="${tipoServico.id}">${tipoServico.nome} R$
                                                            <fmt:formatNumber pattern="#,###,###.00"
                                                                              value="${tipoServico.valor}"
                                                                              type="currency"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <label>Deseja atribuir para qual pet?</label>
                                                <select class="animal" style="width:100%"
                                                        name="animal">
                                                    <c:forEach items="${animais}" var="animal">
                                                        <option value="${animal.id}">${animal.nome}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </section>
                                        <h3>Local</h3>
                                        <section>
                                            <h3>Local do Serviço</h3>
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Escolha aonde você quer que o
                                                    serviço seja feito</label>
                                                <div class="col-sm-4">
                                                    <div class="form-check">
                                                        <label class="form-check-label">
                                                            <input type="radio" class="form-check-input"
                                                                   name="aonde" id="aonde1" required
                                                                   value="Sua Casa" checked>
                                                            Sua Casa
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col-sm-5">
                                                    <div class="form-check">
                                                        <label class="form-check-label">
                                                            <input type="radio" class="form-check-input"
                                                                   name="aonde" id="aonde2"
                                                                   value="Casa do Prestador de Serviço">
                                                            Casa do Prestador de Serviço
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-sm-12">
                                                    <label>Endereço: <span id="endereco"></span></label>
                                                </div>

                                                <div class="col-sm-12">
                                                    <br>
                                                    <label>Ajustar o Endereço</label>
                                                    <br>
                                                    <div id="map"></div>
                                                </div>
                                            </div>

                                        </section>
                                        <h3>Data/Hora</h3>
                                        <section>
                                            <h3>Hora</h3>
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" class="timepicker form-control" name="time" id="time"
                                                       required>
                                            </div>
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" class="datepicker form-control" name="date" id="date"
                                                       required>
                                            </div>
                                        </section>
                                        <h3>Finalizar</h3>
                                        <section>
                                            <div class="col-lg-12">
                                                <div class="card px-2">
                                                    <div class="card-body">
                                                        <div class="container-fluid mt-5 d-flex justify-content-center w-100">
                                                            <div class="table-responsive w-100">
                                                                <table class="table">
                                                                    <tbody>
                                                                    <tr class="text-right">
                                                                        <td class="text-left">1</td>
                                                                        <td class="text-left">Serviço</td>
                                                                        <td id="tipo-servico-form"></td>
                                                                    </tr>
                                                                    <tr class="text-right">
                                                                        <td class="text-left">2</td>
                                                                        <td class="text-left">Local</td>
                                                                        <td id="local-form"></td>
                                                                    </tr>
                                                                    <tr class="text-right">
                                                                        <td class="text-left">3</td>
                                                                        <td class="text-left">Data</td>
                                                                        <td id="data-hora-form"></td>
                                                                    </tr>
                                                                    <tr class="text-right">
                                                                        <td class="text-left">4</td>
                                                                        <td class="text-left">Endereço</td>
                                                                        <td id="endereco-form">1</td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <input type="hidden" class="form-control" name="data" id="data">
                                                    <input type="hidden" class="form-control" name="latitude"
                                                           id="latitude">
                                                    <input type="hidden" class="form-control" name="longitude"
                                                           id="longitude">
                                        </section>
                                    </div>
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
<script src="http://www.urbanui.com/serein/template/js/select2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
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
