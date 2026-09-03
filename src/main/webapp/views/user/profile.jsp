<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin cá nhân</title>
    <style>
        .profile-container { max-width: 500px; margin: 30px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; }
        .avatar-preview { text-align: center; margin-bottom: 20px; }
        .avatar-preview img { width: 120px; height: 120px; border-radius: 50%; object-fit: cover; border: 2px solid #007bff; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group input { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; cursor: pointer; }
        .alert-success { color: green; margin-bottom: 15px; text-align: center; }
        .alert-error { color: red; margin-bottom: 15px; text-align: center; }
    </style>
</head>
<body>

<div class="profile-container">
    <h2 style="text-align: center;">HỒ SƠ CÁ NHÂN</h2>

    <c:if test="${not empty message}">
        <div class="alert-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert-error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/user/profile" method="post" enctype="multipart/form-data">
        <div class="avatar-preview">
            <c:choose>
                <c:when test="${not empty user.images}">
                    <img src="${pageContext.request.contextPath}/image?fname=${user.images}" alt="Avatar">
                </c:when>
                <c:otherwise>
                    <img src="https://via.placeholder.com/120" alt="Default Avatar">
                </c:otherwise>
            </c:choose>
        </div>

        <div class="form-group">
            <label>Tên đăng nhập:</label>
            <input type="text" value="${user.username}" readonly style="background-color: #eee;">
        </div>

        <div class="form-group">
            <label>Email:</label>
            <input type="email" value="${user.email}" readonly style="background-color: #eee;">
        </div>

        <div class="form-group">
            <label>Họ và tên:</label>
            <input type="text" name="fullname" value="${user.fullname}" required>
        </div>

        <div class="form-group">
            <label>Số điện thoại:</label>
            <input type="text" name="phone" value="${user.phone}">
        </div>

        <div class="form-group">
            <label>Ảnh đại diện:</label>
            <input type="file" name="images" accept="image/*">
        </div>

        <button type="submit" class="btn">Lưu thay đổi</button>
    </form>
</div>

</body>
</html>