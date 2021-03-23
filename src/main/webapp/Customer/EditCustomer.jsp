<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin tài khoản</title>
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
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-3 my-auto ">
                    <a class="row my-2 link-active" href="Customer?chucNang=sua">Thông tin tài khoản</a>
                    <a class="row my-2" href="Order?chucNang=donHangCuaToi">Danh sách đơn hàng</a>
                    <a class="row my-2" href="Customer?chucNang=doi-mat-khau">Đổi mật khẩu</a>
                    <a class="row my-2" href="Customer?chucNang=dang-xuat">Đăng xuất</a>
                </div>
                <div class="col-md-9">
                    <h4 class="text-uppercase font-weight-bold title-cart">Sửa thông tin</h4>
                    <hr />
                    <p class="
                        <c:choose>
                            <c:when test = "${message == 'Sửa thông tin tài khoản thành công'}">success</c:when>
                            <c:when test = "${message == 'Sửa thông tin tài khoản thất bại'}">error2</c:when>
                        </c:choose>
                    ">${message}</p>
                    <form action="" method="POST">
                        <div class="form-group">
                                <label for="nameUser" class="font-weight-bold">Họ tên<span class="text-danger">*</span>
                                    <p class="error">${errors.get('userName')}</p>
                                </label>
                                <input type="text" class="form-control" id="nameUser" name="customerName"  placeholder="Họ tên" value="${customer.customerName}"/>
                            </div>
                            <div class="form-group">
                                <label for="phoneUser" class="font-weight-bold">Số điện thoại<span class="text-danger">*</span>
                                    <p class="error">${errors.get('userNumberPhone')}</p>
                                </label>
                                <input type="tel" class="form-control" id="phoneUser" name="numberPhone"  placeholder="0987654321" value="${customer.numberPhone}"/>
                            </div>
                            <div class="form-group">
                                <label for="addressUser" class="font-weight-bold">Địa chỉ<span class="text-danger">*</span>
                                    <p class="error">${errors.get('userAddress')}</p>
                                </label>
                                <input type="text" class="form-control" id="addressUser" name="address"  placeholder="Địa chỉ" value="${customer.address}"/>
                            </div>
                            <div class="form-group">
                                <label for="emailAdmin" class="font-weight-bold">Email <span class="text-danger">*</span>
                                </label>
                                <input type="email" class="form-control" id="emailAdmin" aria-describedby="emailHelp" name="email" placeholder="Email user" value="${customer.email}" required readonly/>
                            </div>

                        <button type="submit" class="btn btn-info mt-4">Sửa thông tin</button>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>