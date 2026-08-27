<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<style>
    body { font-family: Arial, sans-serif; margin: 50px; }
    .form-container { width: 350px; padding: 20px; border: 1px solid #ccc; border-radius: 5px; margin: auto; }
    .form-group { margin-bottom: 15px; }
    .form-group label { display: block; margin-bottom: 5px; }
    .form-group input { width: 100%; padding: 8px; box-sizing: border-box; }
    .btn { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; cursor: pointer; }
    .alert { color: red; margin-bottom: 15px; }
</style>
</head>
<body>
<div class="form-container">
    <h2>ĐĂNG NHẬP</h2>
    <c:if test="${not empty alert}">
        <div class="alert">${alert}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label>Tên đăng nhập:</label>
            <input type="text" name="username" required>
        </div>
        <div class="form-group">
            <label>Mật khẩu:</label>
            <input type="password" name="password" required>
        </div>
        <button type="submit" class="btn">Đăng nhập</button>
    </form>
    <p style="margin-top: 15px; text-align: center;">
        Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
    </p>
</div>
</body>
</html>