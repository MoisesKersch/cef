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

    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.0/css/select.dataTables.min.css">

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
                                <h4 class="card-title">Empréstimos</h4>

                                <form class="form-inline repeater" id="${formId}">
                                    <div data-repeater-list="group-a">
                                        <div data-repeater-item class="d-flex mb-2">
                                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                                <div class="">
                                                    <span class="input-group-text" style="background-color: white; border: none;">Data de Vencimento:</span>
                                                </div>
                                            </div>
                                            <label class="sr-only" for="data-inicial">Users</label>
                                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text"><i class="mdi mdi-calendar-range menu-icon"></i></span>
                                                </div>
                                                <input type="text" class="form-control form-control-sm" id="data-inicial" name="dataInicial" placeholder="Data Inicial">
                                            </div>
                                            <label class="sr-only" for="data-final">Users</label>
                                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text"><i class="mdi mdi-calendar-range menu-icon"></i></span>
                                                </div>
                                                <input type="text" class="form-control form-control-sm" name="dataFinal" id="data-final" placeholder="Até">
                                            </div>

                                            <button type="button" class="btn btn-success btn-sm" id="pesquisar">Pesquisar</button>
                                        </div>
                                    </div>

                                </form>

                                <div class="row">
                                    <div class="col-12">
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
