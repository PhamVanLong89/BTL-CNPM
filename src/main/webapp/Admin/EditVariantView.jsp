<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa biến thể sản phẩm</title>
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
                    <h4 class="text-uppercase text-danger">Sửa biến thể sản phẩm</h4>
                    <hr />
                    <p class="<c:choose>
                        <c:when test = "${message == 'Sửa thông tin biến thể sản phẩm thành công'}">success</c:when>
                        <c:when test = "${message == 'Sửa thông tin biến thể sản phẩm thất bại'}">error2</c:when>
                    </c:choose>">${message}</p>
                    <form action="${pageContext.request.contextPath}/Variant?chucNang=sua" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="SKU" value="${variant.SKU}">
                        <input type="hidden" name="productId" value="${variant.productId}">
                        <div class="form-group">
                            <label class="font-weight-bold">Màu sắc: ${variant.color} </label>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Kích thước: ${variant.size} </label>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">SKU: ${variant.SKU} </label>
                        </div>
                        <div class="form-group">
                            <label for="productQuantity" class="font-weight-bold">Số lượng <span class="text-danger">*</span>
                                <p class="error">${error}</p>
                            </label>
                            <input class="form-control" type="number" onkeypress="isInputNumber(event)" min="0" value="${variant.quantity}" id="productQuantity" name="productQuantity" required/>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-12">
                                <p class="m-1">Ảnh 1</p>
                                <input type="file" name="product-image1" id="product-image1" onchange="readURL(this, $('#img-product1'))" />
                                <img src="img/${variant.image1}" alt="" class="img-variant img-thumbnail" id="img-product1">
                            </div>
                            <div class="col-md-6 col-12">
                                <p class="m-1">Ảnh 2</p>
                                <input type="file" name="product-image2" id="product-image2" onchange="readURL(this, $('#img-product2'))" value=""/>
                                <img src="img/${variant.image2}" alt="" class="img-variant img-thumbnail" id="img-product2">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-info mt-4">Sửa variant</button>
                        <button type="button" class="btn btn-dark mt-4"><a class="text-light" href="">Hủy</a></button>
                    </form>
                </div>
            </main>
        </div>
    </body>
</html>
