<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Thêm sản phẩm</title></head>
<body>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h4 class="mb-0">Thêm sản phẩm</h4>
    <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-sm btn-secondary">
        <i class="bi bi-arrow-left"></i> Danh sách
    </a>
</div>

<form action="${pageContext.request.contextPath}/admin/product/insert" method="post"
      enctype="multipart/form-data" novalidate>

    <div class="mb-3">
        <label class="form-label">Tên sản phẩm</label>
        <input type="text" name="title" value="${form.title}"
               class="form-control ${not empty errors.title ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.title}</div>
    </div>

    <div class="row">
        <div class="col-md-6 mb-3">
            <label class="form-label">Giá (VNĐ)</label>
            <input type="number" step="1000" min="0" name="price" value="${form.price}"
                   class="form-control ${not empty errors.price ? 'is-invalid' : ''}">
            <div class="invalid-feedback d-block">${errors.price}</div>
        </div>
        <div class="col-md-6 mb-3">
            <label class="form-label">Danh mục</label>
            <select name="categoryId" class="form-select ${not empty errors.categoryId ? 'is-invalid' : ''}">
                <option value="">-- Chọn danh mục --</option>
                <c:forEach items="${categories}" var="c">
                    <option value="${c.categoryid}" ${form.categoryId == c.categoryid ? 'selected' : ''}>
                        ${c.categoryname}
                    </option>
                </c:forEach>
            </select>
            <div class="invalid-feedback d-block">${errors.categoryId}</div>
        </div>
    </div>

    <div class="mb-3">
        <label class="form-label">Mô tả</label>
        <textarea name="description" rows="4"
                  class="form-control ${not empty errors.description ? 'is-invalid' : ''}">${form.description}</textarea>
        <div class="invalid-feedback d-block">${errors.description}</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Link ảnh (http/https - tuỳ chọn)</label>
        <input type="text" name="images" value="${form.images}"
               class="form-control ${not empty errors.images ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.images}</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Hoặc tải ảnh lên</label>
        <input type="file" name="imageFile" accept="image/*"
               class="form-control ${not empty errors.imageFile ? 'is-invalid' : ''}">
        <div class="invalid-feedback d-block">${errors.imageFile}</div>
    </div>

    <button type="submit" class="btn btn-primary">Thêm mới</button>
</form>
</body>
</html>
