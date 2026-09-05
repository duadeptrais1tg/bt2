<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thông tin cá nhân</title>
    <style>
        .avatar-preview img { width: 120px; height: 120px; border-radius: 50%; object-fit: cover; border: 3px solid #0d6efd; }
    </style>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-12 col-md-7 col-lg-6">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h3 class="card-title text-center mb-4">HỒ SƠ CÁ NHÂN</h3>

                <c:if test="${not empty message}">
                    <div class="alert alert-success">${message}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/user/profile" method="post"
                      enctype="multipart/form-data" novalidate>

                    <div class="avatar-preview text-center mb-4">
                        <c:choose>
                            <c:when test="${not empty user.images}">
                                <img src="${pageContext.request.contextPath}/image?fname=${user.images}" alt="Avatar">
                            </c:when>
                            <c:otherwise>
                                <img src="https://placehold.co/120" alt="Avatar">
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Tên đăng nhập</label>
                        <input type="text" class="form-control" value="${user.username}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" class="form-control" value="${user.email}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Họ và tên</label>
                        <input type="text" name="fullname" value="${user.fullname}"
                               class="form-control ${not empty errors.fullname ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.fullname}</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" name="phone" value="${user.phone}"
                               class="form-control ${not empty errors.phone ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.phone}</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Ảnh đại diện</label>
                        <input type="file" name="images" accept="image/*"
                               class="form-control ${not empty errors.images ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.images}</div>
                    </div>

                    <button type="submit" class="btn btn-success w-100">Lưu thay đổi</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
