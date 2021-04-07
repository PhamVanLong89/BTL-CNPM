<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đơn hàng của tôi</title>
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
        <div class="container">
            <div class="row">
                <div class="col-md-3 my-5 ">
                    <a class="row my-2" href="Customer?chucNang=sua">Thông tin tài khoản</a>
                    <a class="row my-2 link-active" href="Order?chucNang=donHangCuaToi">Danh sách đơn hàng</a>
                    <a class="row my-2" href="Customer?chucNang=doi-mat-khau">Đổi mật khẩu</a>
                    <a class="row my-2" href="Customer?chucNang=dang-xuat">Đăng xuất</a>
                </div>
                <div class="col-md-9">
                    <h3 class="text-uppercase font-weight-bold mt-3 title-cart" >Đơn hàng của bạn</h3>
                    <div class="row table2">
                        <table class="table table-striped mt-4" id="" name="tableOrders">
                            <thead>
                                <tr>
                                    <th scope="col">Mã đơn hàng</th>
                                    <th scope="col">Ngày mua</th>
                                    <th scope="col">Tổng sản phẩm</th>
                                    <th scope="col">Tổng tiền</th>
                                    <th scope="col">Trạng thái đơn hàng</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listOrder}" var="order" >
                                    <tr>
                                        <td>${order.orderId}</td>
                                        <td>${order.orderDate}</td>
                                        <td>${order.totalProduct}</td>
                                        <td>${order.totalPrice}đ</td>
                                        <td>${order.orderStatus}</td>
                                        <td>
                                            <a href="OrderDetail?chucNang=chiTietDonHang&orderId=${order.orderId}" class="btn btn-warning d-block " role="button">Chi tiết đơn hàng</a>
                                        </td>
                                        <td>
                                            <c:if test = "${order.orderStatus == 'Chờ xác nhận'}"><button type="button" onclick="cancelOrder('${order.orderId}')" class='btn btn-outline-warning'>Hủy đơn hàng</button></c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- Modal message cancel order -->
            <div id="modal-cancel-order" class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Danh sách đơn hàng</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div id="modal-content" class="modal-body">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>