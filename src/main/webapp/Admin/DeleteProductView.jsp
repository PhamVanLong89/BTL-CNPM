<%-- 
    Document   : DeleteProductView
    Created on : Jan 31, 2021, 9:16:22 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xóa sản phẩm</title>
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
                        <h4 class="text-uppercase text-danger font-weight-bold text-center">Xóa sản phẩm</h4>
                        <hr />
                        <p class="error2">${error}</p>
                    <form action="${pageContext.request.contextPath}/Product?chucNang=xoa" method="POST">
                        <input type="hidden" name="productId" value="${product.productId}">
                        <div class="form-group">
                            <label for="productName" class="font-weight-bold">Tên sản phẩm: ${product.productName}
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="categoryName" class="font-weight-bold">Loại sản phẩm: ${product.categoryName}
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="productPrice" class="font-weight-bold">Giá bán: ${product.productPrice}
                            </label>
                        </div>

                        <label for="productVariant" class="font-weight-bold">Variant</label>
                        <p id="errorVariant" class="text-danger"></p>
                        <div class="container-fluid border border-dark">

                            <div class="row table">
                                <table class="table mt-4" id="tableProductVariant" name="tableProductVariant">
                                    <thead>
                                        <tr>
                                            <th scope="col">Màu sắc</th>
                                            <th scope="col">Kích cỡ</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">SKU</th>
                                            <th scope="col">Ảnh 1</th>
                                            <th scope="col">Ảnh 2</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listVariant}" var="variant" >
                                            <tr>
                                                <td>${variant.color}</td>
                                                <td>${variant.size}</td>
                                                <td>${variant.quantity}</td>
                                                <td>${variant.SKU}</td>
                                                <td><c:if test = "${variant.image1 != null}"><img src='img/${variant.image1}' alt='' class='img-thumbnail img-product'></c:if></td>
                                                <td><c:if test = "${variant.image2 != null}"><img src='img/${variant.image2}' alt='' class='img-thumbnail img-product'></c:if></td>
                                                </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>  
                            </div>

                        </div>
                        <button type="submit" class="btn btn-info mt-4">Xóa sản phẩm</button>
                        <button type="button" class="btn btn-dark mt-4"><a class="text-light" href="">Hủy</a></button>
                    </form>
                </div>
            </main>
            <!-- page-content" -->
        </div>
    </body>
</html>
