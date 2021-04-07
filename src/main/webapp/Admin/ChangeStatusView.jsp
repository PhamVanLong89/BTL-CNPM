<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thay đổi trạng thái tài khoản</title>
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
                    <h4 class="text-uppercase text-danger font-weight-bold text-center">Thay đổi trạng thái tài khoản khách hàng</h4>
                    <hr />
                    <p class="
                    <c:choose>
                        <c:when test = "${message == 'Thay đổi trạng thái tài khoản khách hàng thành công'}">success</c:when>
                        <c:when test = "${message == 'Thay đổi trạng thái tài khoản khách hàng thất bại'}">error2</c:when>
                    </c:choose>
                    ">${message}</p>
                    <form action="${pageContext.request.contextPath}/Customer?chucNang=change-status" method="POST">
                    <input type="hidden" name="customerId" id="customerId" value="${customer.customerId}">
                        <div class="form-group">
                            <label for="nameUser" class="font-weight-bold">Họ tên<span class="text-danger">*</span>
                                <p class="error">${errors.get('userName')}</p>
                            </label>
                            <input type="text" class="form-control" id="nameUser" name="customerName"  placeholder="Họ tên" value="${customer.customerName}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="phoneUser" class="font-weight-bold">Số điện thoại<span class="text-danger">*</span>
                                <p class="error">${errors.get('userNumberPhone')}</p>
                            </label>
                            <input type="tel" class="form-control" id="phoneUser" name="numberPhone"  placeholder="0987654321" value="${customer.numberPhone}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="addressUser" class="font-weight-bold">Địa chỉ<span class="text-danger">*</span>
                                <p class="error">${errors.get('userAddress')}</p>
                            </label>
                            <input type="text" class="form-control" id="addressUser" name="address"  placeholder="Địa chỉ" value="${customer.address}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="emailAdmin" class="font-weight-bold">Email <span class="text-danger">*</span>
                            </label>
                            <input type="email" class="form-control" id="emailAdmin" aria-describedby="emailHelp" name="email" placeholder="Email user" value="${customer.email}" required readonly/>
                        </div>
                        <div class="form-group">
                            <label for="displayHome" class="font-weight-bold">Trạng thái tài khoản <span class="text-danger">*</span>
                                <p class="error">${error}</p>
                            </label>
                            <select class="custom-select" id="statusActive" name="statusActive" required>
                                <option value="1" <c:if test = "${customer.statusActive == '1'}">selected="selected"</c:if> >Mở</option>
                                <option value="0" <c:if test = "${customer.statusActive == '0'}">selected="selected"</c:if> >Khóa</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-info mt-4">Sửa trạng thái tài khoản</button>
                    </form>
                </div>
            </main>
        </div>
    </body>
</html>