<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết đơn hàng</title>
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
                    <h4 class="text-uppercase text-danger font-weight-bold text-center">Chi tiết đơn hàng: ${order.orderId}</h4>
                    <hr />
                    <div class="row ">
                        <div class="col-md-7 bg-light rounded-5 mb-3">
                            <p class="text-center text-uppercase mt-2 font-weight-bold">Địa chỉ người nhận</p>
                            <div class="row">
                                <p class="ml-3 w-100">${order.customer.customerName}</p>
                                <p class="ml-3 w-100">Số điện thoại: ${order.customer.numberPhone}</p>
                                <p class="ml-3 w-100">Địa chỉ: ${order.customer.address}</p>
                            </div>
                        </div>
                        <div class="col-md-1 rounded mb-3">
                        </div>
                        <div class="col-md-4 bg-light mb-3">
                            <p class="text-center text-uppercase mt-2 font-weight-bold">Hình thức thanh toán</p>
                             <div class="row">
                                 <c:choose>
                                     <c:when test = "${order.paymentsMethod == 'COD'}">
                                         <p class="ml-3 w-100">Thanh toán tiền mặt khi nhận hàng - COD</p>
                                     </c:when>
                                     <c:when test = "${order.paymentsMethod == 'MoMo'}">
                                         <p class="ml-3 w-100">Thanh toán bằng ví MoMo</p>
                                     </c:when>
                                     <c:when test = "${order.paymentsMethod == 'ZaloPay'}">
                                        <p class="ml-3 w-100">Thanh toán bằng ZaloPay</p>
                                     </c:when>
                                 </c:choose>
                             </div>
                        </div>
                    </div>
                    <div class="row table">
                        <table class="table mt-4" id="" name="tableOrderDetail">
                            <thead>
                                <tr>
                                    <th scope="col">Hình ảnh</th>
                                    <th scope="col">SKU</th>
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Màu sắc</th>
                                    <th scope="col">Kích cỡ</th>
                                    <th scope="col">Giá bán</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Giảm giá</th>
                                    <th scope="col">Thành tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listOrderDetail}" var="orderDetail" >
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test = "${orderDetail.variant.image1 != null}">
                                                    <a class='' href="Product?chucNang=chiTietSanPham&productId=${orderDetail.product.productId}"><img src='img/${orderDetail.variant.image1}' alt='' class='img-thumbnail img-product'></a>
                                                </c:when>
                                                <c:when test = "${orderDetail.variant.image2 != null}">
                                                    <a class='' href="Product?chucNang=chiTietSanPham&productId=${orderDetail.product.productId}"><img src='img/${orderDetail.variant.image2}' alt='' class='img-thumbnail img-product'></a>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>${orderDetail.variant.SKU}</td>
                                        <td><p class='font-weight-bold text-uppercase'><a class='' href="Product?chucNang=chiTietSanPham&productId=${orderDetail.product.productId}">${orderDetail.product.productName}</a></p></td>
                                        <td>${orderDetail.variant.color}</td>
                                        <td>${orderDetail.variant.size}</td>
                                        <td>${orderDetail.product.productPrice}đ</td>
                                        <td>${orderDetail.variant.quantity}</td>
                                        <td>${orderDetail.product.productSale}%</td>
                                        <td>${orderDetail.amount}đ</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                    <div class="col-md-5 mb-3">
                        </div>
                        <div class="col-md-7 bg-light rounded-5 mb-3">
                            <div class='row mt-4'>
                                <div class='col-6'>
                                    <p class='text-left'>Tổng tiền:</p>
                                </div>
                                <div class='col-5'>
                                    <p class='text-right'> ${order.totalPrice} đ</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn btn-dark mt-4 ml-5"><a class="text-light" href=""><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a></button>
            </main>
        </div>
    </body>
</html>