<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="categoryid" value="${cate.categoryid}">
    
    <label>Category name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
    
    <label>Link images:</label><br>
    <input type="text" id="images" name="images" value="${cate.images}"><br>

    <c:choose>
        <c:when test="${cate.images != null && cate.images.startsWith('http')}">
            <c:url value="${cate.images}" var="imgUrl"/>
        </c:when>
        <c:otherwise>
            <c:url value="/image?fname=${cate.images}" var="imgUrl"/>
        </c:otherwise>
    </c:choose>
    <img height="100" width="150" src="${imgUrl}" /><br>

    <label>Upload images:</label><br>
    <input type="file" id="images1" name="images1"><br>

    <label>Status</label><br>
    <input type="radio" id="ston" name="status" value="1" ${cate.status == 1 ? 'checked' : ''}>
    <label for="ston">Hoạt động</label><br>
    <input type="radio" id="stoff" name="status" value="0" ${cate.status != 1 ? 'checked' : ''}>
    <label for="stoff">Khóa</label><br><br>

    <input type="submit" value="Update">
</form>