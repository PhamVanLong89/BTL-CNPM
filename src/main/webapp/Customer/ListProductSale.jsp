<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sản phẩm giảm giá</title>
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
        	<div class="row">

        	    <div class="col-md-2 col-12 d-none d-md-block">
                    <div class="mt-5 ml-0 ml-xl-5">
                        <c:choose>
                            <c:when test = "${categoryName == 'Nữ'}">
                                <a class='d-block font-weight-bold' href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Nữ'>Nữ</a>
                                <a class='d-block mt-3 font-weight-bold' href='Product?chucNang=hienThiSanPhamMoi&danhMucSanPham=Nữ'>Sản phẩm mới</a>
                                <a class='d-block mt-3 font-weight-bold link-active' href='Product?chucNang=sanPhamGiamGia&danhMucSanPham=Nữ'>Sản phẩm giảm giá</a>
                                <p class='font-weight-bold mb-1 mt-3'>Danh mục sản phẩm</p>
                                <ul class='list-unstyled'>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Áo nữ' class="">Áo</a></li>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Quần nữ' class="">Quần</a></li>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Váy nữ' class="">Váy</a></li>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Phụ kiện nữ' class="">Phụ kiện</a></li>
                                </ul>
                            </c:when>
                            <c:when test = "${categoryName == 'Nam' }">
                                <a class='d-block font-weight-bold ' href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Nam'>Nam</a>
                                <a class='d-block mt-3 font-weight-bold' href='Product?chucNang=hienThiSanPhamMoi&danhMucSanPham=Nam'>Sản phẩm mới</a>
                                <a class='d-block mt-3 font-weight-bold link-active' href='Product?chucNang=sanPhamGiamGia&danhMucSanPham=Nam'>Sản phẩm giảm giá</a>
                                <p class='font-weight-bold mb-1 mt-3'>Danh mục sản phẩm</p>
                                <ul class='list-unstyled'>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Áo nam' class="">Áo</a></li>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Quần nam' class="">Quần</a></li>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Phụ kiện nam' class="">Phụ kiện</a></li>
                                </ul>
                            </c:when>
                            <c:when test = "${categoryName == 'Bé gái'}">
                                <a class='d-block font-weight-bold ' href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Bé gái'>Bé gái</a>
                                <a class='d-block mt-3 font-weight-bold' href='Product?chucNang=hienThiSanPhamMoi&danhMucSanPham=Bé gái'>Sản phẩm mới</a>
                                <a class='d-block mt-3 font-weight-bold link-active' href='Product?chucNang=sanPhamGiamGia&danhMucSanPham=Bé giá'>Sản phẩm giảm giá</a>
                                <p class='font-weight-bold mb-1 mt-3'>Danh mục sản phẩm</p>
                                <ul class='list-unstyled'>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Áo bé gái' class="">Áo</a></li>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Quần bé gái' class="">Quần</a></li>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Váy bé gái' class="">Váy</a></li>
                                    <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Phụ kiện bé gái' class="">Phụ kiện</a></li>
                                </ul>
                            </c:when>
                            <c:when test = "${categoryName == 'Bé trai'}">
                                 <a class='d-block font-weight-bold ' href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Bé trai'>Bé trai</a>
                                 <a class='d-block mt-3 font-weight-bold' href='Product?chucNang=hienThiSanPhamMoi&danhMucSanPham=Bé trai'>Sản phẩm mới</a>
                                 <a class='d-block mt-3 font-weight-bold link-active' href='Product?chucNang=sanPhamGiamGia&danhMucSanPham=Bé trai'>Sản phẩm giảm giá</a>
                                 <p class='font-weight-bold mb-1 mt-3'>Danh mục sản phẩm</p>
                                 <ul class='list-unstyled'>
                                     <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Áo bé trai' class="">Áo</a></li>
                                     <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Quần bé trai' class="">Quần</a></li>
                                     <li><a href='Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Phụ kiện bé trai' class="">Phụ kiện</a></li>
                                 </ul>
                            </c:when>
                        </c:choose>
                    </div>
                    <div>
                        <hr />
                        <p class="w-100 m bg-light text-uppercase py-2 pl-xl-5">Đơn giá</p>
                        <div class="form-check ml-xl-5">
                            <c:choose>
                                <c:when test = "${maxPrice == '200000' && (minPrice == '0' || minPrice == null)}">
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice1" value="" checked onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}')">
                                </c:when>
                                <c:otherwise>
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice1" value="" onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&maxPrice=200000&minPrice=0')">
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label" for="filterPrice1">0đ - 200.000đ</a></label>
                        </div>
                        <div class="form-check ml-xl-5">
                            <c:choose>
                                <c:when test = "${maxPrice == '400000' && minPrice == '200000'}">
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice2" value="" checked onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}')">
                                </c:when>
                                <c:otherwise>
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice2" value="" onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&maxPrice=400000&minPrice=200000')">
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label" for="filterPrice2">200.000đ - 400.000đ</label>
                        </div>
                        <div class="form-check ml-xl-5">
                            <c:choose>
                                <c:when test = "${maxPrice == '600000' && minPrice == '400000'}">
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice3" value="" checked onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}')">
                                </c:when>
                                <c:otherwise>
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice3" value="" onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&maxPrice=600000&minPrice=400000')">
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label" for="filterPrice3">400.000đ - 600.000đ</label>
                        </div>
                        <div class="form-check ml-xl-5">
                            <c:choose>
                                <c:when test = "${maxPrice == '800000' && minPrice == '600000'}">
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice4" value="" checked onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}')">
                                </c:when>
                                <c:otherwise>
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice4" value="" onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&maxPrice=800000&minPrice=600000')">
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label" for="filterPrice4">600.000đ - 800.000đ</label>
                        </div>
                        <div class="form-check ml-xl-5">
                            <c:choose>
                                <c:when test = "${maxPrice == '1200000' && minPrice == '800000'}">
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice5" value="" checked onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}')">
                                </c:when>
                                <c:otherwise>
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice5" value="" onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&maxPrice=1200000&minPrice=800000')">
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label" for="filterPrice5">800.000đ - 1.200.000đ</label>
                        </div>
                        <div class="form-check ml-xl-5">
                            <c:choose>
                                <c:when test = "${maxPrice == '1800000' && minPrice == '1200000'}">
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice6" value="" checked onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}')">
                                </c:when>
                                <c:otherwise>
                                    <input class="form-check-input" type="radio" name="filterPrice" id="filterPrice6" value="" onclick="requestProductFilter('Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&maxPrice=1800000&minPrice=1200000')">
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label" for="filterPrice6">1.200.000đ - 1.800.000đ</label>
                        </div>
                    </div>
                </div>

        	    <div class="col-md-10 col-12">
            		<div class="row mt-5">
                        <c:forEach items="${listProduct}" var="product" >
                            <div class="product col-md-4 col-6 p-1 p-md-3">
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
                        <c:if test="${ empty(listProduct) }">
                            <div class="w-100 font-weight-bold text-center">Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn.</div>
                        </c:if>
            		</div>
                </div>
            </div>
        </div>

        <!-- Hiển thị phân trang -->
        <nav aria-label="Page navigation example" class="">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <c:if test = "${currentPage > 1}">
                        <a class="page-link" href="Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&page=${currentPage-1}<c:if test = '${maxPrice != null}'>&maxPrice=${maxPrice}</c:if><c:if test = '${minPrice != null}'>&minPrice=${minPrice}</c:if>" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </c:if>
                </li>
                <c:forEach var = "i" begin = "1" end = "${totalPage}">
                    <li class='page-item'><a class='page-link <c:if test = "${currentPage == i}">text-danger</c:if>' href='Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&page=${i}<c:if test = "${maxPrice != null}">&maxPrice=${maxPrice}</c:if><c:if test = "${minPrice != null}">&minPrice=${minPrice}</c:if>'>${i}</a></li>
                </c:forEach>
                <li class="page-item">
                    <c:if test = "${currentPage < totalPage}">
                        <a class="page-link" href="Product?chucNang=sanPhamGiamGia&danhMucSanPham=${categoryName}&page=${currentPage+1}<c:if test = '${maxPrice != null}'>&maxPrice=${maxPrice}</c:if><c:if test = '${minPrice != null}'>&minPrice=${minPrice}</c:if>" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </c:if>
                </li>
            </ul>
        </nav>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>