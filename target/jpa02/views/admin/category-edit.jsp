<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Sửa danh mục</title></head>
<body>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h4 class="mb-0">Sửa danh mục</h4>
    <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-sm btn-secondary">
        <i class="bi bi-arrow-left"></i> Danh sách
    </a>
</div>

<form action="${pageContext.request.contextPath}/admin/category/update" method="post"
      enctype="multipart/form-data" novalidate>
    <input type="hidden" name="categoryid" value="${cate.categoryid}">

    <div class="mb-3">
        <label class="form-label">Tên danh mục</label>
        <input type="text" name="categoryname" value="${cate.categoryname}"
               class="form-control ${not empty errors.categoryname ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.categoryname}</div>
    </div>

    <c:choose>
        <c:when test="${not empty cate.images && (cate.images.startsWith('http://') || cate.images.startsWith('https://'))}">
            <c:set var="imgUrl" value="${cate.images}" />
        </c:when>
        <c:otherwise>
            <c:url value="/image" var="imgUrl"><c:param name="fname" value="${cate.images}"/></c:url>
        </c:otherwise>
    </c:choose>
    <div class="mb-3">
        <img src="${imgUrl}" alt="${cate.categoryname}" class="img-thumbnail" style="height:120px;">
    </div>

    <div class="mb-3">
        <label class="form-label">Link ảnh (http/https - tuỳ chọn)</label>
        <input type="text" name="images" value="${cate.images}"
               class="form-control ${not empty errors.images ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.images}</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Hoặc tải ảnh mới lên</label>
        <input type="file" name="images1" accept="image/*"
               class="form-control ${not empty errors.images1 ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.images1}</div>
    </div>

    <div class="mb-3">
        <label class="form-label d-block">Trạng thái</label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="status" id="ston" value="1"
                   ${cate.status == 1 ? 'checked' : ''}>
            <label class="form-check-label" for="ston">Hoạt động</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="status" id="stoff" value="0"
                   ${cate.status != 1 ? 'checked' : ''}>
            <label class="form-check-label" for="stoff">Khóa</label>
        </div>
    </div>

    <button type="submit" class="btn btn-primary">Cập nhật</button>
</form>
</body>
</html>
