<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách đơn hàng</title>
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
                    <h4 class="text-uppercase text-danger font-weight-bold text-center">Danh sách đơn hàng</h4>
                    <hr />
                    <div class="row table" >
                        <table class="table mt-4" id="" name="tableOrders">
                            <thead>
                                <tr>
                                    <th scope="col">Mã đơn hàng</th>
                                    <th scope="col">Tên khách hàng</th>
                                    <th scope="col">SĐT nhận hàng</th>
                                    <th scope="col">Địa chỉ nhận hàng</th>
                                    <th scope="col">Ngày mua</th>
                                    <th scope="col">Tổng sản phẩm</th>
                                    <th scope="col">Tổng tiền</th>
                                    <th scope="col">Trạng thái đơn hàng</th>
                                    <th scope="col">Phương thức thanh toán</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listOrder}" var="order" >
                                    <tr <c:if test = "${order.orderStatus == 'Đã hủy'}">class="table-secondary"</c:if>>
                                        <td>${order.orderId}</td>
                                        <td>${order.customer.customerName}</td>
                                        <td>${order.orderNumberPhone}</td>
                                        <td>${order.orderAddress}</td>
                                        <td>${order.orderDate}</td>
                                        <td>${order.totalProduct}</td>
                                        <td>${order.totalPrice}đ</td>
                                        <td>
                                            <c:choose>
                                                <c:when test = "${order.orderStatus == 'Đã hủy'}">
                                                    <div class="form-group">
                                                        <select class="custom-select " id="" name="" >
                                                            <option value="Đã hủy" <c:if test = "${order.orderStatus == 'Đã hủy'}">selected="selected"</c:if> >Đã hủy</option>
                                                        </select>
                                                    </div>
                                                </c:when>
                                                <c:when test = "${order.orderStatus != 'Đã hủy'}">
                                                    <div class="form-group">
                                                        <select class="custom-select " id="" name="" onchange="changeOrderStatus($(this).val(), '${order.orderId}')">
                                                            <option value="Chờ xác nhận" <c:if test = "${order.orderStatus == 'Chờ xác nhận'}">selected="selected"</c:if> >Chờ xác nhận</option>
                                                            <option value="Đang giao" <c:if test = "${order.orderStatus == 'Đang giao'}">selected="selected"</c:if> >Đang giao</option>
                                                            <option value="Đã giao" <c:if test = "${order.orderStatus == 'Đã giao'}">selected="selected"</c:if> >Đã giao</option>
                                                        </select>
                                                    </div>
                                                </c:when>
                                           </c:choose>
                                        </td>
                                        <td>${order.paymentsMethod}</td>
                                        <td>
                                            <a href="OrderDetail?chucNang=chiTietDonHang2&orderId=${order.orderId}" class="btn btn-warning d-block " role="button">Chi tiết đơn hàng</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- Modal message change order status -->
                    <div id="modal" class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                <button type="button" class="btn btn-dark mt-4 ml-5"><a class="text-light" href=""><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a></button>
            </main>
        </div>
    </body>
</html>