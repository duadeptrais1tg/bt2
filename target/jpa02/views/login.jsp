<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Đăng nhập</title></head>
<body>
<div class="row justify-content-center">
    <div class="col-12 col-sm-8 col-md-5">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h3 class="card-title text-center mb-4">ĐĂNG NHẬP</h3>

                <c:if test="${not empty message}">
                    <div class="alert alert-success">${message}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/login" method="post" novalidate>
                    <div class="mb-3">
                        <label class="form-label">Tên đăng nhập</label>
                        <input type="text" name="username" value="${form.username}"
                               class="form-control ${not empty errors.username ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.username}</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mật khẩu</label>
                        <input type="password" name="password"
                               class="form-control ${not empty errors.password ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.password}</div>
                    </div>
                    <button type="submit" class="btn btn-success w-100">Đăng nhập</button>
                </form>

                <p class="text-center mt-3 mb-0">
                    <a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
                </p>
                <p class="text-center mb-0">
                    Chưa có tài khoản?
                    <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
