<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng</title>
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
        <div class="container-fluid">
        	<h3 class="text-uppercase font-weight-bold mt-5 ml-md-4 title-cart" style="font-size: 30px !important;">Giỏ Hàng</h3>
        	<hr class="clearfix w-100 " />
        	<c:choose>
                <c:when test = "${cart != null && !empty(cart)}">
                <!-- Modal message order-->
                <div class="modal fade" id="modal-message" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body" id="modal-message-body">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
                            </div>
                        </div>
                    </div>
                </div>

                <form action="" method="POST">
                    <hr class="clearfix w-100 " />
                    <div class="row">
                        <div class='col-md-8 col-12'>
                            <c:set var="totalProduct" value="${0}"/>
                            <c:set var="totalPrice" value="${0}"/>
                            <c:set var="totalSale" value="${0}"/>
                            <c:set var="payment" value="${0}"/>
                            <c:forEach items="${cart}" var="item" >
                                <div class='row'>
                                    <div class='col-md-3 col-4'>
                                    <c:choose>
                                        <c:when test = "${item.variant.image1 != null}">
                                           <a class='' href="Product?chucNang=chiTietSanPham&productId=${item.product.productId}"><img src="img/${item.variant.image1}" alt='' class='img-thumbnail'></a>
                                        </c:when>
                                        <c:when test = "${item.variant.image2 != null}">
                                            <a class='' href="Product?chucNang=chiTietSanPham&productId=${item.product.productId}"><img src="img/${item.variant.image2}" alt='' class='img-thumbnail'></a>
                                        </c:when>
                                    </c:choose>
                                    </div>
                                    <div class='col-md-9 col-8 pl-md-5'>
                                        <p class='font-weight-bold text-uppercase'><a class='' href="Product?chucNang=chiTietSanPham&productId=${item.product.productId}">${item.product.productName}</a></p>
                                        <p class='my-1'>SKU: ${item.variant.SKU}</p>
                                        <p class='my-1'>Màu sắc: ${item.variant.color}</p>
                                        <p class='my-1'>Kích cỡ: ${item.variant.size}</p>
                                        <p class='my-1'>Đơn giá: ${item.product.productPrice}đ</p>
                                        <p class='my-1'>Giảm giá: <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.sale / item.variant.quantity}" />đ</p>
                                        <p class='d-inline'>Số lượng: </p>
                                        <div class='btn-group btn-group-sm' role='group' aria-label='Basic example'>
                                            <button type='button' class='btn btn-secondary quantity-minus' onclick="quantityMinus($(this).next()); changeQuantity($(this).next(), '${item.variant.SKU}');"><i class='fas fa-minus'></i></button>
                                            <input class='text-center quantity' type='number' name='' value="${item.variant.quantity}" min='1' onkeypress='isInputNumber(event)' onchange="changeQuantity($(this), '${item.variant.SKU}')" style='width:50px;'>
                                            <button type='button' class='btn btn-secondary quantity-plus' onclick="quantityPlus($(this).prev()); changeQuantity($(this).prev(), '${item.variant.SKU}');"><i class='fas fa-plus'></i></button>
                                        </div>
                                        <p class='my-2'>Thành tiền: ${item.amount}đ</p>
                                        <a class='float-right mr-3 mr-md-5' href="OrderDetail?chucNang=xoa&SKU=${item.variant.SKU}">Xóa</a>
                                        <p id="error-${item.variant.SKU}" class="error"></p>
                                    </div>
                                </div>
                                <hr class='clearfix w-100' />
                                <c:set var="totalProduct" value="${totalProduct + item.variant.quantity}" />
                                <c:set var="totalPrice" value="${totalPrice + (item.variant.quantity * item.product.productPrice)}" />
                                <c:set var="totalSale" value="${totalSale + item.sale}" />
                                <c:set var="payment" value="${payment + item.amount}" />
                            </c:forEach>
                        </div>
                        <div class='col-md-4 col-12'>
                            <div class='container bg-light'>
                                <div class='row pt-5'>
                                    <div class='col-6'>
                                        <p class='text-left'>Tổng:</p>
                                    </div>
                                    <div class='col-5'>
                                        <p class='text-right'>${totalProduct} sản phẩm</p>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class='col-6'>
                                        <p class='text-left'>Tổng giá bán:</p>
                                    </div>
                                    <div class='col-5'>
                                        <p class='text-right'> ${totalPrice}đ</p>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class='col-6'>
                                        <p class='text-left'>Giảm giá:</p>
                                    </div>
                                    <div class='col-5'>
                                        <p class='text-right'> ${totalSale}đ</p>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class='col-6'>
                                        <p class='text-left font-weight-bold'>Tạm tính:</p>
                                    </div>
                                    <div class='col-5'>
                                        <p class='text-right font-weight-bold text-danger'> ${payment}đ</p>
                                    </div>
                                </div>
                                <hr class="clearfix w-100 " />
                                <c:if test = "${user != null}">
                                    <div class="form-group">
                                        <label for="addressOrder" class="font-weight-bold">Địa chỉ giao hàng<span class="text-danger">*</span>
                                            <p id="errorAddress" class="error"></p>
                                        </label>
                                        <button type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#modalAddress">
                                            Thay đổi
                                        </button>
                                        <input type="text" class="form-control float-right" id="addressOrder" name="addressOrder"  placeholder="Địa chỉ" readonly value="${user.address}"/>
                                        <!-- Modal address -->
                                        <div class="modal fade" id="modalAddress" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Thay đổi địa chỉ</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label for="addressOrderModal" class="font-weight-bold">Địa chỉ giao hàng
                                                                <p id="errorAddressModal" class="error"></p>
                                                            </label>
                                                            <input type="text" class="form-control" id="addressOrderModal" name="addressOrderModal"  placeholder="" value=""/>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                        <button type="button" class="btn btn-warning" onclick="changeAddressOrder()">Lưu</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group mt-5">
                                        <label for="numberPhoneOrder" class="font-weight-bold">Số điện thoại<span class="text-danger">*</span>
                                            <p id="errorNumberPhone" class="error"></p>
                                        </label>
                                        <button type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#modalNumberPhone">
                                            Thay đổi
                                        </button>
                                        <input type="tel" class="form-control" id="numberPhoneOrder" name="numberPhoneOrder" readonly placeholder="" value="${user.numberPhone}"/>
                                        <!-- Modal number phone -->
                                        <div class="modal fade" id="modalNumberPhone" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Thay đổi số điện thoại</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label for="numberPhoneOrderModal" class="font-weight-bold">Số điện thoại
                                                                <p id="errorNumberPhoneModal" class="error"></p>
                                                            </label>
                                                            <input type="tel" class="form-control" id="numberPhoneOrderModal" name="numberPhoneOrderModal"  placeholder="" value=""/>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                        <button type="button" class="btn btn-warning" onclick="changeNumberPhone()">Lưu</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="paymentMethod" class="font-weight-bold">Phương thức thanh toán<span class="text-danger">*</span>
                                            <p id="errorPayment" class="error"></p>
                                        </label>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="paymentMethod" value="COD" id="defaultCheck1" >
                                            <label class="form-check-label" for="defaultCheck1">Thanh toán tiền mặt khi nhận hàng - COD</label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="paymentMethod" value="MoMo" id="defaultCheck2" >
                                            <label class="form-check-label" for="defaultCheck2">Thanh toán bằng ví MoMo</label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="paymentMethod" value="ZaloPay" id="defaultCheck3" >
                                            <label class="form-check-label" for="defaultCheck3">Thanh toán bằng ZaloPay</label>
                                        </div>
                                    </div>
                                    <hr class="clearfix w-100 " />
                                </c:if>
                                <div class='row'>
                                    <button type='button' class='btn btn-secondary text-uppercase font-weight-bold mb-5 float-left mr-4'>Tiếp tục mua hàng</button>
                                    <button type='button' class='btn btn-danger text-uppercase font-weight-bold mb-5 float-right' onclick="order()">Đặt hàng</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                </c:when>
                <c:when test = "${cart == null || empty(cart)}">
                    <div class='row'>
                        <img src="https://salt.tikicdn.com/desktop/img/mascot@2x.png" alt="" class="rounded mx-auto d-block mb-2">
                        <div class="w-100 font-weight-bold text-center">Không có sản phẩm nào trong giỏ hàng của bạn.</div>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-warning d-block mx-auto mt-4">Tiếp tục mua hàng</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>