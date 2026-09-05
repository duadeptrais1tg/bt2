<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">
            <i class="bi bi-shop"></i> JPA02 Shop
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMain"
                aria-controls="navbarMain" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarMain">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/product">Sản phẩm</a></li>
            </ul>
            <form class="d-flex me-lg-3 my-2 my-lg-0" role="search"
                  action="${pageContext.request.contextPath}/product" method="get">
                <input class="form-control form-control-sm me-2" type="search" name="q"
                       placeholder="Tìm sản phẩm..." aria-label="Search">
                <button class="btn btn-sm btn-light" type="submit"><i class="bi bi-search"></i></button>
            </form>
            <ul class="navbar-nav mb-2 mb-lg-0">
                <c:choose>
                    <c:when test="${empty sessionScope.account}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">
                                <i class="bi bi-box-arrow-in-right"></i> Đăng nhập
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/register">
                                <i class="bi bi-person-plus"></i> Đăng ký
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${sessionScope.account.roleid == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/admin/categories">
                                    <i class="bi bi-gear"></i> Quản trị
                                </a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/profile">
                                <i class="bi bi-person-circle"></i> ${sessionScope.account.username}
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                                <i class="bi bi-box-arrow-right"></i> Đăng xuất
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
