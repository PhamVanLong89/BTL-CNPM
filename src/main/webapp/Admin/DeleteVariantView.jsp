<%-- 
    Document   : DeleteVariantView
    Created on : Feb 1, 2021, 1:19:57 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xóa biến thể sản phẩm</title>
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
                        <h4 class="text-uppercase text-danger font-weight-bold text-center">Xóa biến thể sản phẩm</h4>
                        <hr />
                        <form action="${pageContext.request.contextPath}/Variant?chucNang=xoa" method="POST">
                        <input type="hidden" name="SKU" value="${variant.SKU}">
                        <input type="hidden" name="productId" value="${variant.productId}">
                        <div class="form-group">
                            <label class="font-weight-bold">Màu sắc: ${variant.color}</label>	
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Kích thước: ${variant.size} </label>	
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">SKU: ${variant.SKU} </label>	
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Số lượng: ${variant.quantity} </label>	
                        </div>
                        <button type="submit" class="btn btn-info mt-4">Xóa variant</button>
                        <button type="button" class="btn btn-dark mt-4"><a class="text-light" href="">Hủy</a></button>
                    </form>
                </div>
            </main>
        </div>
    </body>
</html>
