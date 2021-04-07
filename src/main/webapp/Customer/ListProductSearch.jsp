<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh mục sản phẩm</title>
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
        	<p class="text-uppercase font-weight-bold mt-5">Kết quả tìm kiếm cho: ${productName}</p>
        	<div class="row">
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
        	</div>
        </div>

        <!-- Hiển thị phân trang -->
        <nav aria-label="Page navigation example" class="">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <c:if test = "${currentPage > 1}">
                        <a class="page-link" href="Product?chucNang=timKiem&productName=${productName}&page=${currentPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </c:if>
                </li>
                <c:forEach var = "i" begin = "1" end = "${totalPage}">
                    <li class='page-item'><a class='page-link <c:if test = "${currentPage == i}">text-danger</c:if>' href='Product?chucNang=timKiem&productName=${productName}&page=${i}'>${i}</a></li>
                </c:forEach>
                <li class="page-item">
                    <c:if test = "${currentPage < totalPage}">
                        <a class="page-link" href="Product?chucNang=timKiem&productName=${productName}&page=${currentPage+1}" aria-label="Next">
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