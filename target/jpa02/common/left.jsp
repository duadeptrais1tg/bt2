<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="list-group shadow-sm">
    <div class="list-group-item active fw-bold">
        <i class="bi bi-columns-gap"></i> Menu Admin
    </div>
    <a href="${pageContext.request.contextPath}/admin/home" class="list-group-item list-group-item-action">
        <i class="bi bi-house"></i> Trang chủ Admin
    </a>
    <a href="${pageContext.request.contextPath}/admin/categories" class="list-group-item list-group-item-action">
        <i class="bi bi-tags"></i> Quản lý Danh mục
    </a>
    <a href="${pageContext.request.contextPath}/admin/products" class="list-group-item list-group-item-action">
        <i class="bi bi-box-seam"></i> Quản lý Sản phẩm
    </a>
</div>
