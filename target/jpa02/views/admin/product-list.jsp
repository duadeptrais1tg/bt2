<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Quản lý sản phẩm</title></head>
<body>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h4 class="mb-0">Danh sách sản phẩm</h4>
    <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-primary btn-sm">
        <i class="bi bi-plus-lg"></i> Thêm sản phẩm
    </a>
</div>

<form class="row g-2 mb-3" method="get" action="${pageContext.request.contextPath}/admin/products">
    <div class="col-sm-8 col-md-6">
        <div class="input-group">
            <input type="text" name="q" value="${q}" class="form-control" placeholder="Tìm theo tên sản phẩm...">
            <button class="btn btn-outline-primary" type="submit"><i class="bi bi-search"></i> Tìm</button>
            <c:if test="${not empty q}">
                <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/admin/products">Xóa lọc</a>
            </c:if>
        </div>
    </div>
</form>

<c:if test="${not empty q}">
    <p class="text-muted small">Kết quả cho "<strong>${q}</strong>": ${productList.size()} sản phẩm</p>
</c:if>

<div class="table-responsive">
<table class="table table-bordered table-hover align-middle">
    <thead class="table-light">
        <tr>
            <th style="width:60px;">ID</th>
            <th style="width:120px;">Ảnh</th>
            <th>Tên sản phẩm</th>
            <th style="width:140px;">Giá</th>
            <th style="width:150px;">Danh mục</th>
            <th style="width:140px;">Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${productList}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty p.images && (p.images.startsWith('http://') || p.images.startsWith('https://'))}">
                            <c:set var="imgUrl" value="${p.images}" />
                        </c:when>
                        <c:otherwise>
                            <c:url value="/image" var="imgUrl"><c:param name="fname" value="${p.images}"/></c:url>
                        </c:otherwise>
                    </c:choose>
                    <img src="${imgUrl}" alt="${p.title}" class="img-thumbnail" style="height:70px;">
                </td>
                <td>${p.title}</td>
                <td class="text-danger fw-bold">${p.priceFormatted} VNĐ</td>
                <td>${p.category.categoryname}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.id}"
                       class="btn btn-sm btn-outline-primary">
                        <i class="bi bi-pencil"></i> Sửa
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.id}"
                       class="btn btn-sm btn-outline-danger"
                       onclick="return confirm('Xóa sản phẩm &quot;${p.title}&quot;?');">
                        <i class="bi bi-trash"></i> Xóa
                    </a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty productList}">
            <tr><td colspan="6" class="text-center text-muted py-4">Không có sản phẩm nào</td></tr>
        </c:if>
    </tbody>
</table>
</div>
</body>
</html>
