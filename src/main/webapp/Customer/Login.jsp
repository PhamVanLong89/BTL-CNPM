<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
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
        			Đăng nhập
        		</div>
        		<div class="col-md-6 bg-info p-md-4">
                    <p class="error2">${error}</p>
        			<form class="m-md-4" action="${pageContext.request.contextPath}/Customer?chucNang=dang-nhap" method="post">
        				<div class="form-group">
        				    <label for="emailUser" class="font-weight-bold">Email <span class="text-danger">*</span></label>
        				    <input type="email" class="form-control" id="emailUser" name="email" aria-describedby="emailHelp" placeholder="Email" value="${customer.email}"/>
        				</div>
        				<div class="form-group">
        				    <label for="password" class="font-weight-bold">Mật khẩu <span class="text-danger">*</span></label>
        				    <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu" value="${customer.password}"/>
        				</div>
        				<button type="submit" class="btn btn-dark text-white my-3 mt-md-5">Đăng nhập</button>
        			</form>
        			<p class="d-inline mr-2 ml-md-4">Bạn chưa có tài khoản?</p>
        			<a class="font-weight-bold" href="Customer?chucNang=dang-ky">Đăng ký</a>
        			<a class="font-weight-bold d-block ml-md-4 my-3" href="">Quên mật khẩu?</a>
        		</div>
        	</div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
