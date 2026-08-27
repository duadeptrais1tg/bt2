<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<a href="<c:url value='/admin/category/add'/>">Add Category</a><br><hr>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Images</th>
        <th>Category name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listcate}" var="cate" varStatus="STT">
        <tr>
            <td>${STT.index + 1}</td>
            <c:choose>
                <c:when test="${cate.images != null && cate.images.startsWith('http')}">
                    <c:url value="${cate.images}" var="imgUrl"/>
                </c:when>
                <c:otherwise>
                    <c:url value="/image?fname=${cate.images}" var="imgUrl"/>
                </c:otherwise>
            </c:choose>
            <td><img height="100" width="150" src="${imgUrl}" /></td>
            <td>${cate.categoryname}</td>
            <td>${cate.status == 1 ? 'Hoạt động' : 'Khóa'}</td>
            <td>
                <a href="<c:url value='/admin/category/edit?id=${cate.categoryid}'/>">Sửa</a> | 
                <a href="<c:url value='/admin/category/delete?id=${cate.categoryid}'/>">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>