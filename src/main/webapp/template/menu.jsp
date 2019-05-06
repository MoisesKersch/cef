<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="bottom-navbar">
    <div class="container">
        <ul class="nav page-navigation">

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/funcionario">
                    <i class="mdi mdi-account-plus menu-icon"></i>
                    <span class="menu-title">Funcionários</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/emprestimo">
                    <i class="mdi mdi-currency-usd menu-icon"></i>
                    <span class="menu-title">Empréstimos</span>
                </a>
            </li>



        </ul>
    </div>
</nav>
