<%-- 
    Document   : ProductView
    Created on : Jan 31, 2021, 2:39:38 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách sản phẩm</title>
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
                        <h4 class="text-uppercase text-danger font-weight-bold text-center">Danh sách sản phẩm</h4>
                        <hr />
                        <form action="" method="get" class="input-group col-lg-4 p-0 pl-md-3 float-right" enctype="multipart/form-data">
                            <input type="text" class="form-control" name="search" placeholder="Tìm kiếm..." />
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit"><i class="fas fa-search"></i></button>
                            </div>
                        </form>
                        <div class="row table" style="overflow-x:auto;">
                            <table class="table mt-4" id="" name="tableProduct">
                                <thead>
                                    <tr>
                                        <th scope="col">Mã sản phẩm</th>
                                        <th scope="col">Tên sản phẩm</th>
                                        <th scope="col">Loại sản phẩm</th>
                                        <th scope="col">Giá bán</th>
                                        <th scope="col">Giảm giá</th>
                                        <th scope="col">Ngày bán</th>
                                        <th scope="col">Hiển thị lên trang chủ</th>
                                        <th scope="col">Hình ảnh</th>
                                        <th scope="col">Admin thêm</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listProduct}" var="product" >
                                    <tr>
                                        <td>${product.productId}</td>
                                        <td>${product.productName}</td>
                                        <td>${product.categoryName}</td>
                                        <td>${product.productPrice}đ</td>
                                        <td>${product.productSale}%</td>
                                        <td>${product.saleDate}</td>
                                        <td><c:choose>
                                                <c:when test = "${product.displayHome == 0}">
                                                    Không
                                                </c:when>
                                                <c:when test = "${product.displayHome == 1}">
                                                    Có
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td><c:if test = "${product.image != null}"><img src='img/${product.image}' alt='' class='img-thumbnail img-product'></c:if></td>
                                        <td>${product.adminId}</td>
                                        <td>
                                            <a href="Product?chucNang=sua&productId=${product.productId}">Sửa</a>
                                        </td>
                                        <td>
                                            <a href="Product?chucNang=xoa&productId=${product.productId}">Xóa</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <button type="button" class="btn btn-dark mt-4 ml-5"><a class="text-light" href="">Thoát</a></button>
            </main>
        </div>
    </body>
</html>
