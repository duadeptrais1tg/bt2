<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Quản lý danh mục</title></head>
<body>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h4 class="mb-0">Danh sách danh mục</h4>
    <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary btn-sm">
        <i class="bi bi-plus-lg"></i> Thêm danh mục
    </a>
</div>

<div class="table-responsive">
<table class="table table-bordered table-hover align-middle">
    <thead class="table-light">
        <tr>
            <th style="width:60px;">STT</th>
            <th style="width:180px;">Ảnh</th>
            <th>Tên danh mục</th>
            <th style="width:120px;">Trạng thái</th>
            <th style="width:140px;">Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listcate}" var="cate" varStatus="STT">
            <tr>
                <td>${STT.index + 1}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty cate.images && (cate.images.startsWith('http://') || cate.images.startsWith('https://'))}">
                            <c:set var="imgUrl" value="${cate.images}" />
                        </c:when>
                        <c:otherwise>
                            <c:url value="/image" var="imgUrl"><c:param name="fname" value="${cate.images}"/></c:url>
                        </c:otherwise>
                    </c:choose>
                    <img src="${imgUrl}" alt="${cate.categoryname}" class="img-thumbnail" style="height:80px;">
                </td>
                <td>${cate.categoryname}</td>
                <td>
                    <span class="badge ${cate.status == 1 ? 'text-bg-success' : 'text-bg-secondary'}">
                        ${cate.status == 1 ? 'Hoạt động' : 'Khóa'}
                    </span>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.categoryid}"
                       class="btn btn-sm btn-outline-primary">Sửa</a>
                    <a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.categoryid}"
                       class="btn btn-sm btn-outline-danger"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
