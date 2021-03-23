<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
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
        <div class="container-fluid ">
            <div class="row">
                <div class="col-md-6 text-uppercase text-center my-auto register" >
                    Đăng nhập
                </div>
                <div class="col-md-6 bg-info p-md-4 pt-3">
                   <p class="error2">${error}</p>
                    <form class="m-md-4" action="${pageContext.request.contextPath}/Admin?chucNang=dang-nhap" method="post">
                        <div class="form-group">
                            <label for="emailAdmin" class="font-weight-bold">Email <span class="text-danger">*</span></label>
                            <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Email" value="${admin.email}"/>
                        </div>
                        <div class="form-group">
                            <label for="passwordAdmin" class="font-weight-bold">Mật khẩu <span class="text-danger">*</span></label>
                            <input type="password" class="form-control" id="password" placeholder="Mật khẩu" name="password" value="${admin.password}"/>
                        </div>
                        <button type="submit" class="btn btn-dark text-white my-3 mt-md-5">Đăng nhập</button>
                    </form>
                    <a class="font-weight-bold d-block ml-md-4 my-3" href="">Quên mật khẩu?</a>
                </div>
            </div>
        </div>
    </body>
</html>
