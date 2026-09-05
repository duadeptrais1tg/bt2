<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Quên mật khẩu</title></head>
<body>
<div class="row justify-content-center">
    <div class="col-12 col-sm-8 col-md-5">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h3 class="card-title text-center mb-3">Quên mật khẩu</h3>

                <c:if test="${not empty message}">
                    <div class="alert alert-success">${message}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/forgot-password" method="post" novalidate>
                    <div class="mb-3">
                        <label class="form-label">Email / Tên đăng nhập</label>
                        <input type="text" name="email" value="${form.email}"
                               class="form-control ${not empty errors.email ? 'is-invalid' : ''}"
                               ${step2 ? 'readonly' : ''}>
                        <div class="invalid-feedback d-block">${errors.email}</div>
                    </div>

                    <c:if test="${step2}">
                        <div class="mb-3">
                            <label class="form-label">Mã OTP (6 chữ số)</label>
                            <input type="text" name="otp" maxlength="6" inputmode="numeric" value="${form.otp}"
                                   class="form-control ${not empty errors.otp ? 'is-invalid' : ''}">
                            <div class="invalid-feedback d-block">${errors.otp}</div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mật khẩu mới</label>
                            <input type="password" name="newPassword"
                                   class="form-control ${not empty errors.newPassword ? 'is-invalid' : ''}">
                            <div class="invalid-feedback d-block">${errors.newPassword}</div>
                        </div>
                    </c:if>

                    <button type="submit" class="btn btn-primary w-100">
                        ${step2 ? 'Đổi mật khẩu' : 'Gửi mã OTP'}
                    </button>
                </form>

                <p class="text-center mt-3 mb-0">
                    <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
