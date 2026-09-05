<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>${product.title}</title></head>
<body>
<div class="row g-4">
    <div class="col-12 col-md-5">
        <c:url value="/image" var="imgUrl"><c:param name="fname" value="${product.images}"/></c:url>
        <img src="${imgUrl}" class="img-fluid rounded border" alt="${product.title}">
    </div>
    <div class="col-12 col-md-7">
        <h2>${product.title}</h2>
        <h4 class="text-danger">Giá: ${product.priceFormatted} VNĐ</h4>
        <p><strong>Danh mục:</strong> ${product.category.categoryname}</p>
        <p><strong>Mô tả:</strong> ${product.description}</p>
        <a href="${pageContext.request.contextPath}/product" class="btn btn-secondary">
            <i class="bi bi-arrow-left"></i> Quay lại danh sách
        </a>
    </div>
</div>
</body>
</html>
