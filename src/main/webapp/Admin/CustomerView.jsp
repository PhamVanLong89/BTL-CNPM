<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách tài khoản khách hàng</title>
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
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    </head>
    <body>
        <div class="page-wrapper chiller-theme toggled">
            <jsp:include page="SidebarAdmin.jsp"></jsp:include>
            <main class="page-content">
                <div class="container-fluid">
                    <h4 class="text-uppercase text-danger font-weight-bold text-center">Danh sách khách hàng</h4>
                    <hr />
                    <c:choose>
                        <c:when test = "${customerName != null}">
                            <a class="btn btn-dark" href="Customer?chucNang=hien-thi" role="button"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a>
                        </c:when>
                        <c:when test = "${customerName == null}">
                            <a class="btn btn-dark" href="${pageContext.request.contextPath}/Manage" role="button"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a>
                        </c:when>
                    </c:choose>
                    <form action="${pageContext.request.contextPath}/Customer" method="get" class="input-group col-lg-4 p-0 pl-md-3 float-right" enctype="multipart/form-data">
                        <input type="hidden" class="form-control" name="chucNang" value="adminSearch" />
                        <input type="text" class="form-control" name="customerName" placeholder="Tìm kiếm..." value="${customerName}"/>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="submit"><i class="fas fa-search"></i></button>
                        </div>
                    </form>
                    <c:if test = "${customerName != null}"><p class="text-uppercase font-weight-bold mt-5">Kết quả tìm kiếm cho: ${customerName}</p></c:if>
                    <div class="row table" style="overflow-x:auto;">
                        <table class="table mt-4" id="" name="tableProductVariant">
                            <thead>
                                <tr>
                                    <th scope="col">Mã khách hàng</th>
                                    <th scope="col">Họ tên</th>
                                    <th scope="col">Số điện thoại</th>
                                    <th scope="col">Địa chỉ</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Hoạt động</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listCustomer}" var="customer" >
                                    <tr>
                                        <td>${customer.customerId}</td>
                                        <td>${customer.customerName}</td>
                                        <td>${customer.numberPhone}</td>
                                        <td>${customer.address}</td>
                                        <td>${customer.email}</td>
                                        <td>
                                            <input type="checkbox" data-onstyle="success" data-offstyle="danger" onchange="myFunction(this, ${customer.customerId})" <c:if test = "${customer.statusActive == 1}">checked</c:if> data-toggle="toggle" data-on="<i class='fa fa-unlock' aria-hidden='true'></i> Mở" data-off="<i class='fa fa-lock' aria-hidden='true'></i> Khóa" data-size="mini">
                                        </td>
                                        <td><a class="btn btn-warning" href="Customer?chucNang=hienThiChiTiet&customerId=${customer.customerId}" role="button">Chi tiết</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div id="modalChangeStatusCustomer" class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div id="modal-content" class="modal-body">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
