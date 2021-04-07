<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin khách hàng</title>
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
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    </head>
    <body>
        <div class="page-wrapper chiller-theme toggled">
            <jsp:include page="SidebarAdmin.jsp"></jsp:include>
            <main class="page-content">
                <div class="container">
                    <h4 class="text-uppercase text-danger font-weight-bold text-center">Thông tin khách hàng: #${customer.customerId}</h4>
                    <hr />
                    <div >
                        <div class="form-group">
                            <label for="customerId" class="font-weight-bold">Mã khách hàng <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="customerId" name="customerId"  placeholder="" value="${customer.customerId}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="customerName" class="font-weight-bold">Tên khách hàng <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="customerName" name="customerName"  placeholder="Tên admin" value="${customer.customerName}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="numberPhone" class="font-weight-bold">Số điện thoại<span class="text-danger">*</span>
                            </label>
                            <input type="tel" class="form-control" id="phoneNumber" name="numberPhone" placeholder="Số điện thoại" value="${customer.numberPhone}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="address" class="font-weight-bold">Địa chỉ<span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="address" name="address"  placeholder="Địa chỉ" value="${customer.address}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="email" class="font-weight-bold">Email <span class="text-danger">*</span>
                            </label>
                            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" placeholder="Email admin" value="${customer.email}" required readonly/>
                        </div>
                        <a class="btn btn-dark my-4" href="Customer?chucNang=hien-thi" role="button"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>