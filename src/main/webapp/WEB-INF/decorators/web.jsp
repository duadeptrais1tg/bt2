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
    
    <div>
        <sitemesh:write property="body"/>
    </div>
    
    <div>
        <%@ include file="/common/footer.jsp"%>
    </div>
</body>
</html>