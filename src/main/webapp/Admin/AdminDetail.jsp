<%--
    Document   : EditAdminView
    Created on : Feb 19, 2021, 3:48:40 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin tài khoản admin</title>
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
                    <h4 class="text-uppercase text-danger font-weight-bold text-center">Thông tin tài khoản admin: #${admin.adminId}</h4>
                    <hr />
                    <div>
                        <div class="form-group">
                            <label for="adminName" class="font-weight-bold">Tên admin <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="adminName" name="name"  placeholder="Tên admin" value="${admin.adminName}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="numberPhoneAdmin" class="font-weight-bold">Số điện thoại<span class="text-danger">*</span>
                            </label>
                            <input type="tel" class="form-control" id="phoneNumberAdmin" name="numberPhone" placeholder="Số điện thoại" value="${admin.numberPhone}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="sexAdmin" class="font-weight-bold">Giới tính<span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="sexAdmin" name="sexAdmin" placeholder="" value="${admin.sex}" readonly/>
                        </div>
                            <div class="form-group">
                                <label for="addressAdmin" class="font-weight-bold">Địa chỉ<span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="addressAdmin" name="address"  placeholder="Địa chỉ" value="${admin.address}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="emailAdmin" class="font-weight-bold">Email <span class="text-danger">*</span>
                            </label>
                            <input type="email" class="form-control" id="emailAdmin" aria-describedby="emailHelp" name="email" placeholder="Email admin" value="${admin.email}" required readonly/>
                        </div>
                        <div class="form-group">
                            <label for="avatarAdmin" class="font-weight-bold">Hình ảnh
                            </label>
                            <img id="avatarAdmin" class="avatar" src="
                                 <c:choose>
                                     <c:when test = "${admin.image == null}">img/no_avatar.png</c:when>
                                     <c:when test = "${admin.image != null}">img/${admin.image}</c:when>
                                 </c:choose>
                                 " alt="Avatar admin" class="img-thumbnail" >
                        </div>
                        <a class="btn btn-dark my-4" href="Admin?chucNang=hienThi" role="button"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a>
                    </div>
                </div>
            </main>
            <!-- page-content" -->
        </div>
    </body>
</html>