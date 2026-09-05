<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Trang chủ</title></head>
<body>
<h2 class="mb-4">10 Sản Phẩm Mới Nhất</h2>

<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-5 g-3">
    <c:forEach items="${top10Products}" var="p">
        <div class="col">
            <div class="card h-100 shadow-sm">
                <c:url value="/image" var="imgUrl"><c:param name="fname" value="${p.images}"/></c:url>
                <img src="${imgUrl}" class="card-img-top" style="height:160px;object-fit:cover;" alt="${p.title}">
                <div class="card-body d-flex flex-column">
                    <h6 class="card-title">
                        <a href="${pageContext.request.contextPath}/product/detail?id=${p.id}"
                           class="text-decoration-none">${p.title}</a>
                    </h6>
                    <p class="text-danger fw-bold mt-auto mb-0">${p.priceFormatted} VNĐ</p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
