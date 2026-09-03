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

    <!-- Header dùng chung cho User -->
    <header>
        <%@ include file="/common/header.jsp" %>
    </header>

    <!-- Nội dung riêng của trang profile.jsp sẽ được chèn vào đây -->
    <main>
        <sitemesh:write property="body"/>
    </main>

    <!-- Footer dùng chung -->
    <footer>
        <%@ include file="/common/footer.jsp" %>
    </footer>

</body>
</html>