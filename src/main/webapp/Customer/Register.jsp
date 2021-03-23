<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
        <link rel="stylesheet" type="text/css" href="Customer/CSS/style.css" />
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
       <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
        <script type="text/javascript" src="JS/jquery-3.3.1.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script type="text/javascript" src="JS/popper.min.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="Customer/JS/script.js"></script>
        <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <div class="container-fluid mt-5">
                <div class="row">
                    <div class="col-md-6 text-uppercase text-center my-auto register" >
                        Đăng ký tài khoản
                    </div>
                    <div class="col-md-6 bg-info p-md-4">
                        <c:choose>
                            <c:when test = "${message == 'Đăng ký tài khoản thành công'}"><p class="success">${message}. <a class="font-weight-bold" href="Customer?chucNang=dang-nhap">Đăng nhập</a></p></c:when>
                            <c:when test = "${message == 'Đăng ký tài khoản thất bại'}"><p class="error2">${message}</p></c:when>
                        </c:choose>
                        <form class="m-md-4" action="${pageContext.request.contextPath}/Customer?chucNang=dang-ky" method="post">
                            <div class="form-group">
                                <label for="nameUser" class="font-weight-bold">Họ tên<span class="text-danger">*</span>
                                    <p class="error">${errors.get('userName')}</p>
                                </label>
                                <input type="text" class="form-control" id="nameUser" name="customerName"  placeholder="Họ tên" value="${customer.customerName}" required/>
                            </div>
                            <div class="form-group">
                                <label for="phoneUser" class="font-weight-bold">Số điện thoại<span class="text-danger">*</span>
                                    <p class="error">${errors.get('userNumberPhone')}</p>
                                </label>
                                <input type="tel" class="form-control" id="phoneUser" name="numberPhone"  placeholder="0987654321" value="${customer.numberPhone}" required/>
                            </div>
                            <div class="form-group">
                                <label for="addressUser" class="font-weight-bold">Địa chỉ<span class="text-danger">*</span>
                                    <p class="error">${errors.get('userAddress')}</p>
                                </label>
                                <input type="text" class="form-control" id="addressUser" name="address"  placeholder="Địa chỉ" value="${customer.address}" required/>
                            </div>
                            <div class="form-group">
                                <label for="emailUser" class="font-weight-bold">Email <span class="text-danger">*</span>
                                    <p class="error">${errors.get('userEmail')}</p>
                                </label>
                                <input type="email" class="form-control" id="emailUser" name="email" aria-describedby="emailHelp" placeholder="Email" value="${customer.email}" required/>
                            </div>
                            <div class="form-group">
                                <label for="passwordUser" class="font-weight-bold">Mật khẩu <span class="text-danger">*</span>
                                    <p class="error">${errors.get('userPassword')}</p>
                                </label>
                                <input type="password" class="form-control" id="passwordUser" name="password" placeholder="Mật khẩu" value="${customer.password}" required/>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword" class="font-weight-bold">Nhập lại mật khẩu <span class="text-danger">*</span>
                                    <p class="error">${errors.get('userConfirmPassword')}</p>
                                </label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Nhập lại mật khẩu" value="${customer.confirmPassword}" required/>
                            </div>
                            <button type="submit" class="btn btn-dark text-white my-3 my-md-5">Đăng ký</button>
                        </form>
                    </div>
                </div>
            </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
