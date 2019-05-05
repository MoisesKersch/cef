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
                                <h4 class="card-title">Funcionários</h4>

                                <div class="row">
                                    <div class="col-12">
                                        <div class="table-responsive">

<%--                                            <div class="btn-group mr-2">--%>
<%--                                                <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/app/funcionario"><i class="mdi mdi-plus-circle-outline"></i> Adicionar </a>--%>
<%--                                            </div>--%>

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
<script src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/select/1.3.0/js/dataTables.select.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/serein/template/js/pages/${js}"></script>
</body>

</html>
