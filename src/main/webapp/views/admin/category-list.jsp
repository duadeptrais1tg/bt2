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
            
            <%-- Xử lý đường dẫn ảnh đơn giản, chính xác --%>
            <c:choose>
                <%-- 1. Nếu ảnh là URL online bắt đầu bằng http/https --%>
                <c:when test="${not empty cate.images && (cate.images.startsWith('http://') || cate.images.startsWith('https://'))}">
                    <c:set var="imgUrl" value="${cate.images}" />
                </c:when>
                
                <%-- 2. Nếu là file upload lưu local trong máy --%>
                <c:otherwise>
                    <c:url value="/image" var="imgUrl">
                        <c:param name="fname" value="${cate.images}"/>
                    </c:url>
                </c:otherwise>
            </c:choose>

            <td>
                <img height="100" width="150" src="${imgUrl}" alt="${cate.categoryname}" />
            </td>
            <td>${cate.categoryname}</td>
            <td>${cate.status == 1 ? 'Hoạt động' : 'Khóa'}</td>
            <td>
                <a href="<c:url value='/admin/category/edit?id=${cate.categoryid}'/>">Sửa</a> | 
                <a href="<c:url value='/admin/category/delete?id=${cate.categoryid}'/>" onclick="return confirm('Bạn có chắc chắn muốn xóa category này?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>