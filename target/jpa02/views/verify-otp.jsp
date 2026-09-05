<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Xác minh OTP</title></head>
<body>
<div class="row justify-content-center">
    <div class="col-12 col-sm-8 col-md-5">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h3 class="card-title text-center mb-3">Xác minh mã OTP</h3>
                <p class="text-muted small">
                    Mã OTP đã được gửi đến email của bạn (có hiệu lực trong 5 phút).
                </p>

                <c:if test="${not empty sessionScope.otpMailWarning}">
                    <div class="alert alert-warning">${sessionScope.otpMailWarning}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/verify-otp" method="post" novalidate>
                    <div class="mb-3">
                        <label class="form-label">Nhập mã OTP (6 chữ số)</label>
                        <input type="text" name="otp" maxlength="6" inputmode="numeric" value="${form.otp}"
                               class="form-control text-center fs-4 ${not empty errors.otp ? 'is-invalid' : ''}">
                        <div class="invalid-feedback d-block">${errors.otp}</div>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Xác nhận kích hoạt</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
