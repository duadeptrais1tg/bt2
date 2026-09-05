<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin - <sitemesh:write property="title"/></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">

    <sitemesh:write property="head"/>
</head>
<body class="d-flex flex-column min-vh-100 bg-body-tertiary">

    <%@ include file="/common/header.jsp"%>

    <div class="container-fluid flex-grow-1 my-4">
        <div class="row g-4">
            <aside class="col-12 col-md-3 col-lg-2">
                <%@ include file="/common/left.jsp"%>
            </aside>
            <section class="col-12 col-md-9 col-lg-10">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <sitemesh:write property="body"/>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <%@ include file="/common/footer.jsp"%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
