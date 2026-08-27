<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<style>
    body { font-family: Arial, sans-serif; margin: 50px; }
    .form-container { width: 350px; padding: 20px; border: 1px solid #ccc; border-radius: 5px; margin: auto; }
    .form-group { margin-bottom: 15px; }
    .form-group label { display: block; margin-bottom: 5px; }
    .form-group input { width: 100%; padding: 8px; box-sizing: border-box; }
    .btn { width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; cursor: pointer; }
    .alert { color: red; margin-bottom: 15px; }
</style>
</head>
<body>
<div class="form-container">
    <h2>ĐĂNG KÝ TÀI KHOẢN</h2>
    <c:if test="${not empty alert}">
        <div class="alert">${alert}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <div class="form-group">
            <label>Tên đăng nhập:</label>
            <input type="text" name="username" required>
        </div>
        <div class="form-group">
            <label>Mật khẩu:</label>
            <input type="password" name="password" required>
        </div>
        <div class="form-group">
            <label>Họ và tên:</label>
            <input type="text" name="fullname" required>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" required>
        </div>
        <div class="form-group">
            <label>Số điện thoại:</label>
            <input type="text" name="phone">
        </div>
        <button type="submit" class="btn">Đăng ký</button>
    </form>
    <p style="margin-top: 15px; text-align: center;">
        Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
    </p>
</div>
</body>
</html>