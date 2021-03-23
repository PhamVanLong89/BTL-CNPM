<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết sản phẩm</title>
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
            <div class="row mt-5">

                <!-- Hiển thị ảnh sản phẩm -->
                <div class="col-md-6 col-12 px-4 pl-md-5">
                    <div class="slider-for row">
                    <c:forEach items="${listImage}" var="image" >
                        <div class=''><img class="rounded mx-auto d-block" src="img/${image}" alt='' style='width: 100%; height: auto; max-width: 650px;'></div>
                    </c:forEach>
                    </div>
                    <div class="slider-nav mt-4 mx-0 row">
                    <c:forEach items="${listImage}" var="image" >
                        <div class=''><img class="border border-secondary" src="img/${image}" alt='' style='width: 97%; height: auto;'></div>
                    </c:forEach>
                    </div>
                </div>

                <!-- Hiển thị thông tin chi tiết sản phẩm -->
                <div class="col-md-6 col-12 pl-md-5">
                    <form action="${pageContext.request.contextPath}/OrderDetail?chucNang=them" method="POST" class="" enctype="multipart/form-data">
                        <h4 class="text-uppercase font-weight-bold name-product mt-4">${product.productName}</h4>
                        <input type="hidden" id="name" name="name" value="${product.productName}">
                        <div class="form-group m-0">
                            <label class="font-weight-bold">Mã: ${product.productId}</label>
                            <input type="hidden" id="productId" name="productId" value="${product.productId}">
                        </div>
                        <div class="form-group m-0">
                            <label class="font-weight-bold" id="labelSKU">SKU: </label>
                            <input type="hidden" id="SKU" name="SKU" value="">
                        </div>

                        <c:choose>
                            <c:when test = "${product.productSale > 0}"><p class="font-weight-bold sale-price" >${product.productPrice}đ</p></c:when>
                            <c:when test = "${product.productSale <= 0}"><p class="font-weight-bold link-active" >${product.productPrice}đ</p></c:when>
                        </c:choose>
                        <c:if test = "${product.productSale > 0}">
                            <p class='font-weight-bold link-active' >${newPrice}đ</p>
                            <p class='link-active' >Giảm: ${product.productSale}%</p>
                        </c:if>
                        <input type="hidden" id="price" name="price" value="">
                        <input type="hidden" id="sale" name="sale" value="">
                        <div class="row">
                            <div class="form-group col-6">
                                <label class="font-weight-bold" for="">Chọn màu <span class="text-danger">*</span></label>
                                <select class="form-control" id="color" name="color" required>
                                <c:forEach items="${listColor}" var="color" >
                                    <option value="${color}">${color}</option>
                                </c:forEach>
                                </select>
                                <p id="errorColor" class="error"></p>
                            </div>
                            <div class="form-group col-6">
                                <label class="font-weight-bold" for="">Chọn kích cỡ <span class="text-danger">*</span></label>
                                <select class="form-control" id="size" name="size" required>
                                    <option value="">Chọn kích cỡ</option>
                                </select>
                                <p id="errorSize" class="error"></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="d-block font-weight-bold" for="">Chọn số lượng <span class="text-danger">*</span>
                                <p id="errorQuantity" class="error"></p>
                            </label>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button type="button" class="btn btn-secondary quantity-minus" onclick="quantityMinus($('#quantity'))"><i class="fas fa-minus"></i></button>
                                <input class="text-center quantity" id="quantity" type="number" name="quantityDetail" value="1" min="1" onkeypress="isInputNumber(event)" required/>
                                <button type="button" class="btn btn-secondary quantity-plus" onclick="quantityPlus($('#quantity'))"><i class="fas fa-plus"></i></button>
                            </div>
                        </div>

                        <button type="button" class="btn btn-danger text-uppercase font-weight-bold mt-3" onclick="addProductToCart()">Thêm vào giỏ hàng</button>
                        <div class="form-group mt-4">
                            <label class="font-weight-bold" for="">Mô tả</label>
                            <div>${product.description}</div>
                        </div>
                    </form>
                </div>
            </div>

        </div>

        <div class="container-fluid">
            <div class="row mt-3">
                <hr class="clearfix w-100" />
                <h4 class="text-uppercase font-weight-bold name-product ml-3">Sản phẩm tương tự</h4>
            </div>
            <div class="autoplay">
                <!-- Hiển thị sản phẩm tương tự -->
                <c:forEach items="${listProduct}" var="product" >
                    <div class="p-1 p-md-3">
                        <div class="card">
                            <c:if test = "${product.productSale > 0}">
                                <div class='ribbon-wrapper'>
                                    <div class='ribbon'>Giảm ${product.productSale}%</div>
                                </div>
                            </c:if>
                            <a href="Product?chucNang=chiTietSanPham&productId=${product.productId}"><img class="card-img-top p-md-3" src="img/${product.image}" alt="product 1" /></a>
                            <div class="card-body">
                                <p class="card-title text-center text-truncate"><a href="Product?chucNang=chiTietSanPham&productId=${product.productId}" class="text-decoration-none">${product.productName}</a></p>
                                <h5 class="card-text font-weight-bold text-center">${product.productPrice}đ</h5>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>