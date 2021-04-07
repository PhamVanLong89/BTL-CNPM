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
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
    </head>
    <body>
        <div class="page-wrapper chiller-theme toggled">
            <jsp:include page="SidebarAdmin.jsp"></jsp:include>
                <main class="page-content">
                    <div class="container-fluid">
                        <h4 class="text-uppercase text-danger font-weight-bold text-center">Danh sách sản phẩm</h4>
                        <hr />
                        <form action="${pageContext.request.contextPath}/Product" method="get" class="input-group col-lg-4 p-0 pl-md-3 float-right" enctype="multipart/form-data">
                            <input type="hidden" class="form-control" name="chucNang" value="adminSearch" />
                            <input type="text" class="form-control" name="productName" placeholder="Tìm kiếm..." value="${productName}"/>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit"><i class="fas fa-search"></i></button>
                            </div>
                        </form>
                        <c:choose>
                            <c:when test = "${productName != null}">
                                <a class="btn btn-dark mt-5 mt-lg-0" href="Product?chucNang=hienThi" role="button"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a>
                                <p class="text-uppercase font-weight-bold mt-5">Kết quả tìm kiếm cho: ${productName}</p>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-dark mt-5 mt-lg-0" href="Manage" role="button"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a>
                                <button class="btn btn-secondary d-lg-block d-inline mt-5" type="button" data-toggle="collapse" data-target="#filterProduct" aria-expanded="false" aria-controls="collapseExample">
                                    <i class="fa fa-filter" aria-hidden="true"></i> Lọc sản phẩm
                                </button>
                            </c:otherwise>
                        </c:choose>
                        <div class="collapse mt-3" id="filterProduct">
                            <div class="card card-body ">
                                <div class="row">
                                    <div class="col-md-7">
                                        <p class="w-100 m bg-light text-uppercase py-2 pl-xl-5">Danh mục sản phẩm</p>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <div class="form-check ml-xl-4">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Nữ'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory1" value="Nữ" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory1" value="Nữ" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory1">Nữ</a></label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Áo nữ'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory2" value="Áo nữ" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory2" value="Áo nữ" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory2">Áo nữ</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Quần nữ'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory3" value="Quần nữ" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory3" value="Quần nữ" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory3">Quần nữ</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Váy nữ'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory4" value="Váy nữ" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory4" value="Váy nữ" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory4">Váy nữ</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Phụ kiện nữ'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory5" value="Phụ kiện nữ" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory5" value="Phụ kiện nữ" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory5">Phụ kiện nữ</label>
                                                </div>
                                                <div class="form-check ml-xl-4">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Nam'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory6" value="Nam" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory6" value="Nam" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory6">Nam</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Áo nam'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory7" value="Áo nam" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory7" value="Áo nam" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory7">Áo nam</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Quần nam'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory8" value="Quần nam" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory8" value="Quần nam" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory8">Quần nam</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Phụ kiện nam'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory9" value="Phụ kiện nam" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory9" value="Phụ kiện nam" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory9">Phụ kiện nam</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-check ml-xl-4">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Bé gái'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory10" value="Bé gái" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory10" value="Bé gái" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory10">Bé gái</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Áo bé gái'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory11" value="Áo bé gái" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory11" value="Áo bé gái" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory11">Áo bé gái</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Quần bé gái'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory12" value="Quần bé gái" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory12" value="Quần bé gái" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory12">Quần bé gái</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Váy bé gái'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory13" value="Váy bé gái" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory13" value="Váy bé gái" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory13">Váy bé gái</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Phụ kiện bé gái'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory14" value="Phụ kiện bé gái" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory14" value="Phụ kiện bé gái" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory14">Phụ kiện bé gái</label>
                                                </div>
                                                <div class="form-check ml-xl-4">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Bé trai'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory15" value="Bé trai" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory15" value="Bé trai" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory15">Bé trai</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Áo bé trai'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory16" value="Áo bé trai" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory16" value="Áo bé trai" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory16">Áo bé trai</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Quần bé trai'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory17" value="Quần bé trai" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory17" value="Quần bé trai" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory17">Quần bé trai</label>
                                                </div>
                                                <div class="form-check ml-xl-5 ml-md-3">
                                                    <c:choose>
                                                        <c:when test = "${category == 'Phụ kiện bé trai'}">
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory18" value="Phụ kiện bé trai" checked onclick="resetValue($(this)); requestProductFilter();">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="radio" name="filterCategory" id="filterCategory18" value="Phụ kiện bé trai" onclick="requestProductFilter()">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="filterCategory18">Phụ kiện bé trai</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-5">
                                        <p class="w-100 m bg-light text-uppercase py-2 pl-xl-5">Đơn giá</p>
                                        <div class="form-check ml-xl-5">
                                            <c:choose>
                                                <c:when test = "${maxPrice == '200000' && (minPrice == '0' || minPrice == null)}">
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice1" value="0-200000" checked onclick="resetValue($(this)); requestProductFilter();">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice1" value="0-200000" onclick="requestProductFilter()">
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="form-check-label" for="filterPrice1">0đ - 200.000đ</a></label>
                                        </div>
                                        <div class="form-check ml-xl-5">
                                            <c:choose>
                                                <c:when test = "${maxPrice == '400000' && minPrice == '200000'}">
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice2" value="200000-400000" checked onclick="resetValue($(this)); requestProductFilter();">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice2" value="200000-400000" onclick="requestProductFilter()">
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="form-check-label" for="filterPrice2">200.000đ - 400.000đ</label>
                                        </div>
                                        <div class="form-check ml-xl-5">
                                            <c:choose>
                                                <c:when test = "${maxPrice == '600000' && minPrice == '400000'}">
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice3" value="400000-600000" checked onclick="resetValue($(this)); requestProductFilter();">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice3" value="400000-600000" onclick="requestProductFilter()">
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="form-check-label" for="filterPrice3">400.000đ - 600.000đ</label>
                                        </div>
                                        <div class="form-check ml-xl-5">
                                            <c:choose>
                                                <c:when test = "${maxPrice == '800000' && minPrice == '600000'}">
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice4" value="600000-800000" checked onclick="resetValue($(this)); requestProductFilter();">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice4" value="600000-800000" onclick="requestProductFilter()">
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="form-check-label" for="filterPrice4">600.000đ - 800.000đ</label>
                                        </div>
                                        <div class="form-check ml-xl-5">
                                            <c:choose>
                                                <c:when test = "${maxPrice == '1200000' && minPrice == '800000'}">
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice5" value="800000-1200000" checked onclick="resetValue($(this)); requestProductFilter()">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice5" value="800000-1200000" onclick="requestProductFilter()">
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="form-check-label" for="filterPrice5">800.000đ - 1.200.000đ</label>
                                        </div>
                                        <div class="form-check ml-xl-5">
                                            <c:choose>
                                                <c:when test = "${maxPrice == '1800000' && minPrice == '1200000'}">
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice6" value="1200000-1800000" checked onclick="resetValue($(this)); requestProductFilter();">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice6" value="1200000-1800000" onclick="requestProductFilter()">
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="form-check-label" for="filterPrice6">1.200.000đ - 1.800.000đ</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class=" table" style="overflow-x:auto;">
                            <table class="table mt-4 table-sortable" id="tableProduct" name="tableProduct">
                                <thead>
                                    <tr>
                                        <th scope="col" >Mã sản phẩm</th>
                                        <th scope="col" >Tên sản phẩm</th>
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
                                            <a href="Product?chucNang=sua&productId=${product.productId} " class="btn btn-warning d-block " role="button">Sửa</a>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalDeleteProduct${product.productId}">Xóa</button>
                                            <!-- Modal delete product-->
                                            <div class="modal fade" id="modalDeleteProduct${product.productId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Sản phẩm</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">Bạn có muốn xóa sản phẩm: <span class="text-danger">${product.productName}</span> không??</div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                            <button type="button" class="btn btn-primary" onclick="deleteProduct('${product.productId}')">Có</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <!-- Modal message delete product -->
                        <div id="modalMessageDeleteProduct" class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">Danh sách đơn hàng</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div id="modal-content-message" class="modal-body">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
