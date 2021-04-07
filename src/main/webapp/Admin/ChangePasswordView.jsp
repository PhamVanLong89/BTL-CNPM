<%-- 
    Document   : ChangePasswordView
    Created on : Feb 20, 2021, 10:45:46 AM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi mật khẩu</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" href="Admin/CSS/style2.css" />
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
        <script src="https://kit.fontawesome.com/b86b756e64.js" crossorigin="anonymous"></script>
        <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
        <script type="text/javascript" src="JS/jquery-3.3.1.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script type="text/javascript" src="JS/popper.min.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="Admin/JS/script.js"></script>
    </head>
    <body>
        <div class="page-wrapper chiller-theme toggled">
            <jsp:include page="SidebarAdmin.jsp"></jsp:include>
                <main class="page-content">
                    <div class="container">
                        <h4 class="text-uppercase text-danger font-weight-bold text-center">Đổi mật khẩu</h4>
                        <hr />
                    <p class="
                        <c:choose>
                            <c:when test = "${message == 'Đổi mật khẩu thành công'}">success</c:when>
                            <c:when test = "${message == 'Đổi mật khẩu thất bại'}">error2</c:when>
                        </c:choose>
                        ">${message}</p>
                    <form class="m-md-4" action="${pageContext.request.contextPath}/Admin?chucNang=doi-mat-khau" method="post">
                        <div class="form-group">
                            <label for="currentPassword" class="font-weight-bold">Mật khẩu hiện tại <span class="text-danger">*</span>
                                <p class="error">${errors.get('currentPassword')}</p>
                            </label>
                            <input type="password" class="form-control" id="currentPassword" placeholder="Mật khẩu hiện tại" name="currentPassword" value="${admin.password}"/>
                        </div>
                        <div class="form-group">
                            <label for="newPassword" class="font-weight-bold">Mật khẩu mới <span class="text-danger">*</span>
                                <p class="error">${errors.get('newPassword')}</p>
                            </label>
                            <input type="password" class="form-control" id="newPassword" placeholder="Mật khẩu mới" name="newPassword" value="${admin.newPassword}"/>
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword" class="font-weight-bold">Nhập lại mật khẩu <span class="text-danger">*</span>
                                <p class="error">${errors.get('confirmPassword')}</p>
                            </label>
                            <input type="password" class="form-control" id="confirmPassword" placeholder="Nhập lại mật khẩu" name="confirmPassword" value="${admin.confirmPassword}"/>
                        </div>

                        <button type="submit" class="btn btn-info text-white my-3 mt-md-5">Đổi mật khẩu</button>
                        <button type="button" class="btn btn-dark my-3 mt-md-5"><a class="text-light" href="">Hủy</a></button>
                    </form>
                </div>
            </main>
            <!-- page-content" -->
        </div>
    </body>
</html>
