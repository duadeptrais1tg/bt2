<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/></title>
    <sitemesh:write property="head"/>
</head>
<body>
    <div>
        <%@ include file="/common/header.jsp"%>
    </div>
    
    <div style="display: flex;">
        <!-- Sidebar Menu bên trái -->
        <aside style="width: 200px;">
            <%@ include file="/common/left.jsp"%>
        </aside>
        
        <!-- Nội dung chính của trang Admin -->
        <section style="flex: 1; padding: 15px;">
            <sitemesh:write property="body"/>
        </section>
    </div>
    
    <div>
        <%@ include file="/common/footer.jsp"%>
    </div>
</body>
</html>