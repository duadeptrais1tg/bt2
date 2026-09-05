<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Sửa sản phẩm</title></head>
<body>
<c:set var="vTitle" value="${not empty form.title ? form.title : product.title}"/>
<c:set var="vPrice" value="${not empty form.price ? form.price : product.price}"/>
<c:set var="vDesc" value="${not empty form.description ? form.description : product.description}"/>
<c:set var="vImages" value="${not empty form.images ? form.images : product.images}"/>
<c:set var="vCat" value="${not empty form.categoryId ? form.categoryId : product.category.categoryid}"/>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h4 class="mb-0">Sửa sản phẩm #${product.id}</h4>
    <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-sm btn-secondary">
        <i class="bi bi-arrow-left"></i> Danh sách
    </a>
</div>

<form action="${pageContext.request.contextPath}/admin/product/update" method="post"
      enctype="multipart/form-data" novalidate>
    <input type="hidden" name="id" value="${product.id}">

    <div class="mb-3">
        <label class="form-label">Tên sản phẩm</label>
        <input type="text" name="title" value="${vTitle}"
               class="form-control ${not empty errors.title ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.title}</div>
    </div>

    <div class="row">
        <div class="col-md-6 mb-3">
            <label class="form-label">Giá (VNĐ)</label>
            <input type="number" step="1000" min="0" name="price" value="${vPrice}"
                   class="form-control ${not empty errors.price ? 'is-invalid' : ''}">
            <div class="invalid-feedback d-block">${errors.price}</div>
        </div>
        <div class="col-md-6 mb-3">
            <label class="form-label">Danh mục</label>
            <select name="categoryId" class="form-select ${not empty errors.categoryId ? 'is-invalid' : ''}">
                <option value="">-- Chọn danh mục --</option>
                <c:forEach items="${categories}" var="c">
                    <option value="${c.categoryid}" ${vCat == c.categoryid ? 'selected' : ''}>${c.categoryname}</option>
                </c:forEach>
            </select>
            <div class="invalid-feedback d-block">${errors.categoryId}</div>
        </div>
    </div>

    <div class="mb-3">
        <label class="form-label">Mô tả</label>
        <textarea name="description" rows="4"
                  class="form-control ${not empty errors.description ? 'is-invalid' : ''}">${vDesc}</textarea>
        <div class="invalid-feedback d-block">${errors.description}</div>
    </div>

    <c:choose>
        <c:when test="${not empty vImages && (vImages.startsWith('http://') || vImages.startsWith('https://'))}">
            <c:set var="imgUrl" value="${vImages}"/>
        </c:when>
        <c:otherwise>
            <c:url value="/image" var="imgUrl"><c:param name="fname" value="${vImages}"/></c:url>
        </c:otherwise>
    </c:choose>
    <div class="mb-3">
        <img src="${imgUrl}" alt="${vTitle}" class="img-thumbnail" style="height:120px;">
    </div>

    <div class="mb-3">
        <label class="form-label">Link ảnh (http/https - tuỳ chọn)</label>
        <input type="text" name="images" value="${vImages}"
               class="form-control ${not empty errors.images ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.images}</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Hoặc tải ảnh mới lên</label>
        <input type="file" name="imageFile" accept="image/*"
               class="form-control ${not empty errors.imageFile ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.imageFile}</div>
    </div>

    <button type="submit" class="btn btn-primary">Cập nhật</button>
</form>
</body>
</html>
