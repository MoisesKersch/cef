<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="bottom-navbar">
    <div class="container">
        <ul class="nav page-navigation">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/dashboard">
                    <i class="mdi mdi-view-dashboard-outline menu-icon"></i>
                    <span class="menu-title">Dashboard</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/servico">
                    <i class="mdi mdi-airplay menu-icon"></i>
                    <span class="menu-title">Agendar Serviço</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/animal">
                    <i class="mdi mdi-airplay menu-icon"></i>
                    <span class="menu-title">Cadastrar Pet</span>
                </a>
            </li>

<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="${pageContext.request.contextPath}/app/servico/list">--%>
<%--                    <i class="mdi mdi-airplay menu-icon"></i>--%>
<%--                    <span class="menu-title">Relatorio de Serviços</span>--%>
<%--                </a>--%>
<%--            </li>--%>

            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="mdi mdi-airplay menu-icon"></i>
                    <span class="menu-title">Conta</span>
                </a>
            </li>

        </ul>
    </div>
</nav>
