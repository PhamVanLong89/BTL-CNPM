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
        <title>Sửa thông tin tài khoản admin</title>
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
                        <h4 class="text-uppercase text-danger font-weight-bold text-center">Sửa thông tin tài khoản admin</h4>
                        <hr />
                        <p class="
                        <c:choose>
                            <c:when test = "${message == 'Sửa thông tin tài khoản admin thành công'}">success</c:when>
                            <c:when test = "${message == 'Sửa thông tin tài khoản admin thất bại'}">error2</c:when>
                        </c:choose>
                        ">${message}</p>
                    <form action="${pageContext.request.contextPath}/Admin?chucNang=sua" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="adminName" class="font-weight-bold">Tên admin <span class="text-danger">*</span>
                                <p class="error">${errors.get('adminName')}</p>
                            </label>
                            <input type="text" class="form-control" id="adminName" name="name"  placeholder="Tên admin" value="${admin.adminName}" required/>
                        </div>
                        <div class="form-group">
                            <label for="numberPhoneAdmin" class="font-weight-bold">Số điện thoại<span class="text-danger">*</span>
                                <p class="error">${errors.get('adminNumberPhone')}</p>
                            </label>
                            <input type="tel" class="form-control" id="phoneNumberAdmin" name="numberPhone" placeholder="Số điện thoại" value="${admin.numberPhone}" required/>
                        </div>
                        <div class="form-group">
                            <label for="sexAdmin" class="font-weight-bold">Giới tính<span class="text-danger">*</span>
                                <p class="error">${errors.get('adminSex')}</p>  
                            </label>
                            <select class="custom-select" id="sexAdmin" name="sex" >
                                <option value="Nam" <c:if test = "${admin.sex == 'Nam'}">selected="selected"</c:if> >Nam</option>
                                <option value="Nữ" <c:if test = "${admin.sex == 'Nữ'}">selected="selected"</c:if> >Nữ</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="addressAdmin" class="font-weight-bold">Địa chỉ<span class="text-danger">*</span>
                                    <p class="error">${errors.get('adminAddress')}</p>   
                            </label>
                            <input type="text" class="form-control" id="addressAdmin" name="address"  placeholder="Địa chỉ" value="${admin.address}" required/>
                        </div>
                        <div class="form-group">
                            <label for="emailAdmin" class="font-weight-bold">Email <span class="text-danger">*</span>
                            </label>
                            <input type="email" class="form-control" id="emailAdmin" aria-describedby="emailHelp" name="email" placeholder="Email admin" value="${admin.email}" required readonly/>
                        </div>
                        <div class="form-group">
                            <label for="imageAdmin" class="font-weight-bold">Hình ảnh

                            </label>
                            <input type="file" name="image" id="imgAdmin" onchange="readURL(this, $('#avatarAdmin'))" />
                            <img id="avatarAdmin" class="avatar" src="
                                 <c:choose>
                                     <c:when test = "${admin.image == null}">img/no_avatar.png</c:when>
                                     <c:when test = "${admin.image != null}">img/${admin.image}</c:when>
                                 </c:choose>
                                 " alt="Avatar admin" class="img-thumbnail" >
                        </div>
                        <button type="submit" class="btn btn-info mt-4">Sửa admin</button>
                        <button type="button" id="huy" onclick="deleteForm()" class="btn btn-dark mt-4"><a class="text-light" href="">Hủy</a></button>

                    </form>
                </div>
            </main>
            <!-- page-content" -->
        </div>
    </body>
</html>