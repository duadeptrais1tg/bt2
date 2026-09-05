<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Đăng ký tài khoản</title></head>
<body>
<div class="row justify-content-center">
    <div class="col-12 col-sm-10 col-md-6">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h3 class="card-title text-center mb-4">ĐĂNG KÝ TÀI KHOẢN</h3>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/register" method="post" novalidate>
                    <div class="mb-3">
                        <label class="form-label">Tên đăng nhập</label>
                        <input type="text" name="username" value="${form.username}"
                               class="form-control ${not empty errors.username ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.username}</div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Mật khẩu</label>
                            <input type="password" name="password"
                                   class="form-control ${not empty errors.password ? 'is-invalid' : ''}">
                            <div class="invalid-feedback d-block">${errors.password}</div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Nhập lại mật khẩu</label>
                            <input type="password" name="confirmPassword"
                                   class="form-control ${not empty errors.confirmPassword ? 'is-invalid' : ''}">
                            <div class="invalid-feedback d-block">${errors.confirmPassword}</div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Họ và tên</label>
                        <input type="text" name="fullname" value="${form.fullname}"
                               class="form-control ${not empty errors.fullname ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.fullname}</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" value="${form.email}"
                               class="form-control ${not empty errors.email ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.email}</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" name="phone" value="${form.phone}"
                               class="form-control ${not empty errors.phone ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.phone}</div>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Đăng ký</button>
                </form>

                <p class="text-center mt-3 mb-0">
                    Đã có tài khoản?
                    <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
