<%-- 
    Document   : EditProductView
    Created on : Jan 31, 2021, 4:14:46 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa sản phẩm</title>
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
                        <h4 class="text-uppercase text-danger font-weight-bold text-center">Sửa sản phẩm</h4>
                        <hr/>
                        <p class="<c:choose>
                            <c:when test = "${message == 'Sửa thông tin sản phẩm thành công'}">success</c:when>
                            <c:when test = "${message == 'Sửa thông tin sản phẩm thất bại'}">error2</c:when>
                        </c:choose>">${message}</p>
                    <form action="${pageContext.request.contextPath}/Product?chucNang=sua" method="POST" class="">
                        <input type="hidden" name="productId" id="productId" value="${product.productId}">
                        <div class="form-group">
                            <label for="productName" class="font-weight-bold">Tên sản phẩm <span class="text-danger">*</span>
                                <p class="error">${errors.get('productName')}</p>
                            </label>
                            <input type="text" class="form-control" id="productName" name="productName" placeholder="Tên sản phẩm" value="${product.productName}" required/>
                        </div>
                        <div class="form-group">
                            <label for="categoryName" class="font-weight-bold">Loại sản phẩm <span class="text-danger">*</span>
                                <p class="error">${errors.get('categoryName')}</p>
                            </label>
                            <select class="custom-select" id="categoryName" name="categoryName" required>
                                <option value="" <c:if test = "${product.categoryName == ''}">selected="selected"</c:if> >Chọn loại sản phẩm</option>
                                <option value="Áo nữ" <c:if test = "${product.categoryName == 'Áo nữ'}">selected="selected"</c:if> >Áo nữ</option>
                                <option value="Quần nữ" <c:if test = "${product.categoryName == 'Quần nữ'}">selected="selected"</c:if> >Quần nữ</option>
                                <option value="Váy nữ" <c:if test = "${product.categoryName == 'Váy nữ'}">selected="selected"</c:if> >Váy nữ</option>
                                <option value="Phụ kiện nữ" <c:if test = "${product.categoryName == 'Phụ kiện nữ'}">selected="selected"</c:if> >Phụ kiện nữ</option>
                                <option value="Áo nam" <c:if test = "${product.categoryName == 'Áo nam'}">selected="selected"</c:if> >Áo nam</option>
                                <option value="Quần nam" <c:if test = "${product.categoryName == 'Quần nam'}">selected="selected"</c:if> >Quần nam</option>
                                <option value="Phụ kiện nam" <c:if test = "${product.categoryName == 'Phụ kiện nam'}">selected="selected"</c:if> >Phụ kiện nam</option>
                                <option value="Áo bé gái" <c:if test = "${product.categoryName == 'Áo bé gái'}">selected="selected"</c:if> >Áo bé gái</option>
                                <option value="Quần bé gái" <c:if test = "${product.categoryName == 'Quần bé gái'}">selected="selected"</c:if> >Quần bé gái</option>
                                <option value="Váy bé gái" <c:if test = "${product.categoryName == 'Váy bé gái'}">selected="selected"</c:if> >Váy bé gái</option>
                                <option value="Phụ kiện bé gái" <c:if test = "${product.categoryName == 'Phụ kiện bé gái'}">selected="selected"</c:if> >Phụ kiện bé gái</option>
                                <option value="Áo bé trai" <c:if test = "${product.categoryName == 'Áo bé trai'}">selected="selected"</c:if> >Áo bé trai</option>
                                <option value="Quần bé trai" <c:if test = "${product.categoryName == 'Quần bé trai'}">selected="selected"</c:if> >Quần bé trai</option>
                                <option value="Phụ kiện bé trai" <c:if test = "${product.categoryName == 'Phụ kiện bé trai'}">selected="selected"</c:if> >Phụ kiện bé trai</option>
                                </select>
                                <div class="invalid-feedback">Example invalid custom select feedback</div>
                            </div>
                            <div class="form-group">
                                <label for="productPrice" class="font-weight-bold">Giá bán <span class="text-danger">*</span>
                                    <p class="error">${errors.get('productPrice')}</p>
                            </label>
                            <input class="form-control" type="number" onkeypress="isInputNumber(event)" min="0" value="${product.productPrice}" id="productPrice" name="productPrice" required/>
                        </div>

                        <div class="form-group">
                            <label class="font-weight-bold" for="productDescription">Mô tả </label>
                            <textarea name="productDescription" class="form-control" rows="5" cols="50" tabindex="3" >${product.description}</textarea>
                        </div>  
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label for="productSale" class="font-weight-bold">Giảm giá(%)
                                    <p class="error">${errors.get('productSale')}</p>
                                </label>
                                <input class="form-control d-inline-block" type="number" onkeypress="isInputNumber(event)" min="0" max="100" value="${product.productSale}" id="productSale" name="productSale"/>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="displayHome" class="font-weight-bold">Hiển thị lên trang chủ <span class="text-danger">*</span>
                                    <p class="error">${errors.get('displayHome')}</p>
                                </label>
                                <select class="custom-select" id="displayHome" name="displayHome" required>
                                    <option value="1" <c:if test = "${product.displayHome == '1'}">selected="selected"</c:if> >Có</option>
                                    <option value="0" <c:if test = "${product.displayHome == '0'}">selected="selected"</c:if> >Không</option>
                                    </select>
                                </div>
                            </div>
                            <label for="productVariant" class="font-weight-bold">Variant <span class="text-danger">*</span>
                                <p class="error">${errors.get('variant')}</p>
                        </label>
                        <p id="errorVariant" class="text-danger"></p>
                        <div class="container-fluid border border-dark p-0">
                            <div class="row m-md-4 m-2">
                                <div class="col-md-3 col-12">
                                    <label for="productColor" class="">Màu sắc <span class="text-danger">*</span></label>
                                    <select class="custom-select" id="productColor">
                                        <option value="">Chọn màu</option>
                                        <option value="Đỏ">Đỏ</option>
                                        <option value="Đen">Đen</option>
                                        <option value="Trắng">Trắng</option>
                                        <option value="Vàng">Vàng</option>
                                        <option value="Cam">Cam</option>
                                        <option value="Hồng">Hồng</option>
                                        <option value="Tím">Tím</option>
                                        <option value="Nâu">Nâu</option>
                                        <option value="Xanh">Xanh</option>
                                        <option value="Xám">Xám</option>
                                    </select>
                                    <p></p>
                                </div>
                                <div class="col-md-3 col-12">
                                    <label for="productSize" class="">Kích cỡ <span class="text-danger">*</span></label>
                                    <select class="custom-select" id="productSize">
                                        <option value="">Chọn kích cỡ</option>
                                        <option value="Free size">Free Size</option>
                                        <option value="S">S</option>
                                        <option value="M">M</option>
                                        <option value="L">L</option>
                                        <option value="XL">XL</option>
                                        <option value="2XL">2XL</option>
                                        <option value="3XL">3XL</option>
                                        <option value="28">28</option>
                                        <option value="29">29</option>
                                        <option value="30">30</option>
                                        <option value="31">31</option>
                                        <option value="32">32</option>
                                        <option value="33">33</option>
                                    </select>
                                </div>
                                <div class="col-md-3 col-12">
                                    <label for="productQuantity" class="">Số lượng <span class="text-danger">*</span></label>
                                    <input class="form-control" type="number" onkeypress="isInputNumber(event)" min="0" value="1" id="productQuantity" />
                                </div>
                                <div class="col-md-3 col-12">
                                    <label for="productSKU" class="">SKU <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="productSKU" aria-describedby="emailHelp" placeholder="SKU" />
                                </div>
                            </div>
                            <div class="row m-md-4 m-2">
                                <div class="col-md-6 col-12">
                                    <p class="m-1">Ảnh 1</p>
                                    <input type="file" name="product-image1" id="product-image1" />

                                </div>
                                <div class="col-md-6 col-12">
                                    <p class="m-1 ">Ảnh 2</p>
                                    <input type="file" name="product-image2" id="product-image2"/>
                                </div>
                            </div>

                            <button type="button" class="btn btn-dark addVariant ml-md-5 m-4" id="addVariant2">Thêm variant</button>
                            <div class="row table m-0" style="overflow-x:auto;">
                                <table class="table my-5" id="tableProductVariant" name="tableProductVariant">
                                    <thead>
                                        <tr>
                                            <th scope="col">Màu sắc</th>
                                            <th scope="col">Kích cỡ</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">SKU</th>
                                            <th scope="col">Ảnh 1</th>
                                            <th scope="col">Ảnh 2</th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="tBodyProductVariant">
                                        <c:forEach items="${listVariant}" var="variant" >
                                            <tr>
                                                <td>${variant.color}</td>
                                                <td>${variant.size}</td>
                                                <td>${variant.quantity}</td>
                                                <td>${variant.SKU}</td>
                                                <td><c:if test = "${variant.image1 != null}"><img src='img/${variant.image1}' alt='' class='rounded img-product'></c:if></td>
                                                <td><c:if test = "${variant.image2 != null}"><img src='img/${variant.image2}' alt='' class='rounded img-product'></c:if></td>
                                                <td>
                                                    <a href="Variant?chucNang=sua&SKU=${variant.SKU}&productId=${product.productId}" class="btn btn-warning d-block " role="button">Sửa</a>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalDeleteVariant${variant.SKU}">Xóa</button>
                                                    <!-- Modal delete variant-->
                                                    <div class="modal fade" id="modalDeleteVariant${variant.SKU}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel">Sản phẩm</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">Bạn có muốn xóa sản phẩm màu: <span class="text-danger">${variant.color}</span>, size: <span class="text-danger">${variant.size}</span> không??</div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                                    <button type="button" class="btn btn-primary" onclick="deleteVariant('${variant.SKU}')">Có</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- Modal message delete variant -->
                                <div id="modalMessageDeleteVariant" class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">Sản phẩm</h5>
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
                        <button type="submit" class="btn btn-info mt-4">Sửa sản phẩm</button>
                        <a class="btn btn-dark mt-4" href="Product?chucNang=hienThi" role="button"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a>
                    </form>
                </div>
            </main>
            <!-- page-content" -->
        </div>
    </body>
</html>
