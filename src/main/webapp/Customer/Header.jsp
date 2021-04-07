<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid sticky-top p-0">
    <div class="container-fluid bg-dark p-0 m-0" style="height: 24px;">
        <div class="col-12">
            <div class="mx-3 float-right">
                <a class="text-white" href="OrderDetail?chucNang=hienThi"><i class="far fa-shopping-bag text-white"></i>Giỏ hàng</a>
                <c:choose>
                    <c:when test = "${cart != null && !empty(cart)}">
                        <c:set var="totalProduct" value="${0}"/>
                        <c:forEach items="${cart}" var="item" >
                            <c:set var="totalProduct" value="${totalProduct + item.variant.quantity}" />
                        </c:forEach>
                        <span class='link-active'>(${totalProduct})</span>
                    </c:when>
                    <c:when test = "${cart == null || empty(cart)}">
                        <span class='text-white'>(0)</span>
                    </c:when>
                </c:choose>
            </div>

            <div class="mx-3 float-right dropdown">
                <a class="text-white dropdown-toggle" href="" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="far fa-user text-white"></i>
                    <c:choose>
                        <c:when test = "${user == null}">Tài khoản</c:when>
                        <c:when test = "${user != null}">${user.customerName}</c:when>
                    </c:choose>
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <c:choose>
                        <c:when test = "${user == null}">
                            <a class='dropdown-item' href='Customer?chucNang=dang-nhap'>Đăng nhập</a>
                            <a class='dropdown-item' href='Customer?chucNang=dang-ky'>Đăng ký</a>
                        </c:when>
                        <c:when test = "${user != null}">
                            <a class='dropdown-item' href='Customer?chucNang=sua'>Tài khoản của tôi</a>
                            <a class='dropdown-item' href='Order?chucNang=donHangCuaToi'>Danh sách đơn hàng</a>
                            <a class='dropdown-item' href='Customer?chucNang=dang-xuat'>Đăng xuất</a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

    <!-- menu -->
    <nav class="container-fluid navbar navbar-expand-lg navbar-light bg-light header">
        <div class="container col-lg-8">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><img style="width: 80px;" src="img/logo.jpg" alt="PhamLong" /></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown px-3">
                        <a class="nav-link" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Nữ" id="navbarDropdown">
                            NỮ
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Áo nữ">Áo</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Quần nữ">Quần</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Váy nữ">Váy</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Phụ kiện nữ">Phụ kiện</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown px-3">
                        <a class="nav-link" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Nam" id="navbarDropdown">
                            NAM
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Áo nam">Áo</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Quần nam">Quần</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Phụ kiện nam">Phụ kiện</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown px-3">
                        <a class="nav-link" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Bé gái" id="navbarDropdown">
                            BÉ GÁI
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Áo bé gái">Áo</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Quần bé gái">Quần</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Váy bé gái">Váy</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Phụ kiện bé gái">Phụ kiện</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown px-3">
                        <a class="nav-link" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Bé trai" id="navbarDropdown">
                            BÉ TRAI
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Áo bé trai">Áo</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Quần bé trai">Quần</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiTheoDanhMuc&danhMucSanPham=Phụ kiện bé trai">Phụ kiện</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown px-3">
                        <p class="nav-link link-active m-0" id="navbarDropdown">
                            MỚI
                        </p>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Product?chucNang=hienThiSanPhamMoi&danhMucSanPham=Nữ">NỮ</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiSanPhamMoi&danhMucSanPham=Nam">NAM</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiSanPhamMoi&danhMucSanPham=Bé gái">BÉ GÁI</a>
                            <a class="dropdown-item" href="Product?chucNang=hienThiSanPhamMoi&danhMucSanPham=Bé trai">BÉ TRAI</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown px-3">
                        <p class="nav-link link-active m-0" id="navbarDropdown">
                            GIẢM GIÁ
                        </p>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Product?chucNang=sanPhamGiamGia&danhMucSanPham=Nữ">NỮ</a>
                            <a class="dropdown-item" href="Product?chucNang=sanPhamGiamGia&danhMucSanPham=Nam">NAM</a>
                            <a class="dropdown-item" href="Product?chucNang=sanPhamGiamGia&danhMucSanPham=Bé gái">BÉ GÁI</a>
                            <a class="dropdown-item" href="Product?chucNang=sanPhamGiamGia&danhMucSanPham=Bé trai">BÉ TRAI</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/Product" method="get" class="input-group col-lg-4 p-0 pl-md-3" enctype="multipart/form-data">
            <input type="hidden" class="form-control" name="chucNang" value="timKiem" />
            <input type="text" class="form-control" name="productName" placeholder="Tìm kiếm..." value="${productName}"/>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit"><i class="fas fa-search"></i></button>
            </div>
        </form>
    </nav>
</div>

<!-- end menu -->

<!-- slide top -->
<div id="carouselExampleSlidesOnly" class="carousel slide m-0 bg-dark" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <p class="text-center text-light bg-dark m-0" style="font-size: 2vw !important;">Thêm vào giỏ hàng 499.000đ để được miễn phí vận chuyển</p>
        </div>
        <div class="carousel-item">
            <p class="text-center text-light bg-dark m-0" style="font-size: 2vw !important;">ĐỔI HÀNG MIỄN PHÍ - Tại tất cả các cửa hàng trong 15 ngày</p>
        </div>
    </div>
</div>