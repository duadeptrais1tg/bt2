<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data">
    <label>Category name:</label><br>
    <input type="text" id="categoryname" name="categoryname"><br>
    
    <label>Link images:</label><br>
    <input type="text" id="images" name="images"><br>

    <label>Upload images:</label><br>
    <input type="file" id="images1" name="images1"><br>

    <label>Status</label><br>
    <input type="radio" id="ston" name="status" value="1" checked>
    <label for="ston">Hoạt động</label><br>
    <input type="radio" id="stoff" name="status" value="0">
    <label for="stoff">Khóa</label><br><br>

    <input type="submit" value="Insert">
</form>