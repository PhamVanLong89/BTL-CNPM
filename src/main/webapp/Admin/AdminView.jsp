<%-- 
    Document   : AdminView
    Created on : Feb 19, 2021, 3:05:36 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách tài khoản admin</title>
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
                    <div class="container-fluid">
                        <h4 class="text-uppercase text-danger font-weight-bold text-center">Danh sách tài khoản admin</h4>
                        <hr />
                        <form action="searchAdmin.php" method="get" class="input-group col-lg-4 p-0 pl-md-3 float-right" enctype="multipart/form-data">
                            <input type="text" class="form-control" name="search" placeholder="Tìm kiếm..." />
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit"><i class="fas fa-search"></i></button>
                            </div>
                        </form>
                        <div class="row table">
                            <table class="table mt-4" id="" name="tableAdmins">
                                <thead>
                                    <tr>
                                        <th scope="col">Mã tài khoản</th>
                                        <th scope="col">Tên admin</th>
                                        <th scope="col">Số điện thoại</th>
                                        <th scope="col">Giới tính</th>
                                        <th scope="col">Địa chỉ</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Hình ảnh</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listAdmin}" var="admin" >
                                    <tr>
                                        <td>${admin.adminId}</td>
                                        <td>${admin.adminName}</td>
                                        <td>${admin.numberPhone}</td>
                                        <td>${admin.sex}</td>
                                        <td>${admin.address}</td>
                                        <td>${admin.email}</td>
                                        <td><c:if test = "${admin.image != null}"><img src='img/${admin.image}' alt='' class='img-thumbnail img-product'></c:if></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <button type="button" class="btn btn-dark mt-4 ml-5"><a class="text-light" href="">Thoát</a></button>
            </main>
            <!-- page-content" -->
        </div>
    </body>
</html>
