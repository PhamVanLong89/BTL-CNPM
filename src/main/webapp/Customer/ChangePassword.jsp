<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi mật khẩu</title>
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
                <div class="col-md-3 my-5 ">
                    <a class="row my-2" href="Customer?chucNang=sua">Thông tin tài khoản</a>
                    <a class="row my-2" href="Order?chucNang=donHangCuaToi">Danh sách đơn hàng</a>
                    <a class="row my-2 link-active" href="Customer?chucNang=doi-mat-khau">Đổi mật khẩu</a>
                    <a class="row my-2" href="Customer?chucNang=dang-xuat">Đăng xuất</a>
                </div>
                <div class="col-md-9">
        		    <h4 class="text-uppercase font-weight-bold title-cart">Đổi mật khẩu</h4>
        		    <hr />
                    <p class="
                        <c:choose>
                            <c:when test = "${message == 'Đổi mật khẩu thành công'}">success</c:when>
                            <c:when test = "${message == 'Đổi mật khẩu thất bại'}">error2</c:when>
                        </c:choose>
                    ">${message}</p>
        		    <form class="m-md-4" action="${pageContext.request.contextPath}/Customer?chucNang=doi-mat-khau" method="post">
        				<div class="form-group">
        				    <label for="currentPassword" class="font-weight-bold">Mật khẩu hiện tại <span class="text-danger">*</span>
        				    <p class="error">${errors.get('currentPassword')}</p>
        				    </label>
        				    <input type="password" class="form-control" id="currentPassword" placeholder="Mật khẩu hiện tại" name="currentPassword" value="${customer.password}"/>
        				</div>
        				<div class="form-group">
        				    <label for="newPassword" class="font-weight-bold">Mật khẩu mới <span class="text-danger">*</span>
        				    <p class="error">${errors.get('newPassword')}</p>
        				    </label>
        				    <input type="password" class="form-control" id="newPassword" placeholder="Mật khẩu mới" name="newPassword" value="${customer.newPassword}"/>
        				</div>
        				<div class="form-group">
        				    <label for="confirmPassword" class="font-weight-bold">Nhập lại mật khẩu <span class="text-danger">*</span>
        				    <p class="error">${errors.get('confirmPassword')}</p>
        				    </label>
        				    <input type="password" class="form-control" id="confirmPassword" placeholder="Nhập lại mật khẩu" name="confirmPassword" value="${customer.confirmPassword}"/>
        				</div>

        				<button type="submit" class="btn btn-info text-white my-3 mt-md-5">Đổi mật khẩu</button>
        				<button type="button" class="btn btn-dark my-3 mt-md-5"><a class="text-light" href="">Hủy</a></button>
        			</form>
        		</div>
        	</div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>