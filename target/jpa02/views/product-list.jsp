<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Danh sách sản phẩm</title></head>
<body>
<div class="d-flex flex-wrap justify-content-between align-items-center mb-4 gap-2">
    <h2 class="mb-0">Tất Cả Sản Phẩm</h2>
    <form class="d-flex" method="get" action="${pageContext.request.contextPath}/product">
        <div class="input-group">
            <input type="text" name="q" value="${q}" class="form-control" placeholder="Tìm sản phẩm...">
            <button class="btn btn-primary" type="submit"><i class="bi bi-search"></i></button>
            <c:if test="${not empty q}">
                <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/product">Xóa</a>
            </c:if>
        </div>
    </form>
</div>

<c:if test="${not empty q}">
    <p class="text-muted">Tìm thấy <strong>${totalProducts}</strong> kết quả cho "<strong>${q}</strong>".</p>
</c:if>

<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
    <c:forEach items="${productList}" var="p">
        <div class="col">
            <div class="card h-100 shadow-sm">
                <c:url value="/image" var="imgUrl"><c:param name="fname" value="${p.images}"/></c:url>
                <img src="${imgUrl}" class="card-img-top" style="height:200px;object-fit:cover;" alt="${p.title}">
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title">
                        <a href="${pageContext.request.contextPath}/product/detail?id=${p.id}"
                           class="text-decoration-none">${p.title}</a>
                    </h5>
                    <p class="text-danger fw-bold fs-5 mt-auto mb-0">${p.priceFormatted} VNĐ</p>
                </div>
            </div>
        </div>
    </c:forEach>
    <c:if test="${empty productList}">
        <div class="col-12"><div class="alert alert-info">Không tìm thấy sản phẩm nào.</div></div>
    </c:if>
</div>

<nav class="mt-4">
    <ul class="pagination justify-content-center">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <li class="page-item ${i == currentPage ? 'active' : ''}">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/product?page=${i}<c:if test='${not empty q}'>&q=${q}</c:if>">${i}</a>
            </li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>
