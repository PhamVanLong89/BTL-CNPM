$(document).ready(function () {
    $(".option-variant").hide();
    $(".sidebar-dropdown > a").click(function () {
        $(".sidebar-submenu").slideUp(200);
        if ($(this).parent().hasClass("active")) {
            $(".sidebar-dropdown").removeClass("active");
            $(this).parent().removeClass("active");
        } else {
            $(".sidebar-dropdown").removeClass("active");
            $(this).next(".sidebar-submenu").slideDown(200);
            $(this).parent().addClass("active");
        }
    });

    $("#close-sidebar").click(function () {
        $(".page-wrapper").removeClass("toggled");
    });

    $("#show-sidebar").click(function () {
        $(".page-wrapper").addClass("toggled");
    });

//Thêm variant trong chức năng thêm mới sản phẩm
    $("#addVariant").click(function () {
        var table = document.getElementById("tableProductVariant");
        var error = null;
        if ($("#productColor").val() === "") {
            error = "Bạn chưa chọn màu sắc sản phẩm";
        } else if ($("#productSize").val().trim() === "") {
            error = "Bạn chưa chọn kích cỡ sản phẩm";
        } else if ($("#productSKU").val().trim() === "") {
            error = "Bạn chưa nhập SKU sản phẩm";
        } else if ($("#productQuantity").val().trim() === "") {
            error = "Bạn chưa nhập số lượng sản phẩm";
        } else {
            var SKU = $("#productSKU").val().trim();
            var color = $("#productColor").val().trim();
            var size = $("#productSize").val().trim();
            var quantity = $("#productQuantity").val().trim();
            $.ajax({
                type: "POST",
                url: "Variant?chucNang=checkVariant",
                data: "SKU=" + SKU + "&Color=" + color + "&Size=" + size + "&Quantity=" + quantity,
                success: function (response) {
                    if (response.trim() !== "") {
                        error = response;
                    }
                },
                async: false
            });
            for (var i = 1; i < table.rows.length; i++) {
                if ($("#productSKU").val().trim() === table.rows[i].cells[3].innerHTML) {
                    error = "Mã SKU đã tồn tại";
                } else if ($("#productColor").val() === table.rows[i].cells[0].innerHTML) {
                    if ($("#productSize").val() === table.rows[i].cells[1].innerHTML) {
                        error = "Biến thể sản phẩm đã tồn tại";
                    } else if ($("#productSize").val() !== "Free size" && table.rows[i].cells[1].innerHTML === "Free size") {
                        table.deleteRow(i);
                        i--;
                    } else if ($("#productSize").val() === "Free size" && table.rows[i].cells[1].innerHTML !== "Free size") {
                        error = "Biến thể sản phẩm đã được chọn kích cỡ. Vui lòng chọn kích cỡ khác cho biến thể sản phẩm";
                    }
                }
            }
        }
        if (error === null) {
            var data =
                    "<tr><td>" +
                    $("#productColor").val().trim() +
                    "</td><td>" +
                    $("#productSize").val().trim() +
                    "</td><td>" +
                    $("#productQuantity").val().trim() +
                    "</td><td>" +
                    $("#productSKU").val().trim() +
                    "</td> <td><div class='row'><img id='img-product1-" +
                    $("#productSKU").val().trim() +
                    "' class='img-product rounded' src='' alt=''></div><div class='row'><input type='file' name='product-image1' accept='.jpg, .jpeg, .png' onchange='readURL(this, $(\"#img-product1-" +
                    $("#productSKU").val().trim() + "\"))' /></div></td> <td><div class='row'><img id='img-product2-" +
                    $("#productSKU").val().trim() +
                    "' class='img-product rounded' src='' alt=''></div><div class='row'><input type='file' name='product-image2' accept='.jpg, .jpeg, .png' onchange='readURL(this, $(\"#img-product2-" +
                    $("#productSKU").val().trim() + "\"))' /></div></td> <td><button type=\"button\" onclick=\"deleteRow1('" +
                    $("#productSKU").val().trim() +
                    "')\">Xóa</button></td><td><input type='hidden' name='colorVariant' value='" +
                    $("#productColor").val().trim() + "'><input type='hidden' name='sizeVariant' value='" +
                    $("#productSize").val().trim() + "'><input type='hidden' name='quantityVariant' value='" +
                    $("#productQuantity").val().trim() + "'><input type='hidden' name='SKUVariant' value='" +
                    $("#productSKU").val().trim() + "'></td></tr>";
            $("#tableProductVariant > tbody").append(data);
        }
        $("#errorVariant").text(error);
    });


//Thêm variant trong chức năng sửa sản phẩm
    $("#addVariant2").click(function () {
        var table = document.getElementById("tableProductVariant");
        var error = null;
        if ($("#productColor").val() === "") {
            error = "Bạn chưa chọn màu sắc sản phẩm";
        } else if ($("#productSize").val().trim() === "") {
            error = "Bạn chưa chọn kích cỡ sản phẩm";
        } else if ($("#productSKU").val().trim() === "") {
            error = "Bạn chưa nhập SKU sản phẩm";
        } else if ($("#productQuantity").val().trim() === "") {
            error = "Bạn chưa nhập số lượng sản phẩm";
        } else {
            var SKU = $("#productSKU").val().trim();
            var color = $("#productColor").val().trim();
            var size = $("#productSize").val().trim();
            var quantity = $("#productQuantity").val().trim();
            $.ajax({
                type: "POST",
                url: "Variant?chucNang=checkVariant",
                data: "SKU=" + SKU + "&Color=" + color + "&Size=" + size + "&Quantity=" + quantity,
                success: function (response) {
                    if (response.trim() !== "") {
                        error = response;
                    }
                },
                async: false
            });
            for (var i = 1; i < table.rows.length; i++) {
                if ($("#productSKU").val().trim() === table.rows[i].cells[3].innerHTML) {
                    error = "Mã SKU đã tồn tại";
                } else if ($("#productColor").val() === table.rows[i].cells[0].innerHTML) {
                    if ($("#productSize").val() === table.rows[i].cells[1].innerHTML) {
                        error = "Biến thể sản phẩm đã tồn tại";
                    } else if ($("#productSize").val() !== "Free size" && table.rows[i].cells[1].innerHTML === "Free size") {
                        table.deleteRow(i);
                        i--;
                    } else if ($("#productSize").val() === "Free size" && table.rows[i].cells[1].innerHTML !== "Free size ") {
                        error = "Biến thể sản phẩm đã được chọn kích cỡ. Vui lòng chọn kích cỡ khác cho biến thể sản phẩm";
                    }
                }
            }
        }
        if (error === null) {
            var fd = new FormData();
            var files1 = $("#product-image1")[0].files[0];
            var files2 = $("#product-image2")[0].files[0];
            fd.append('Image1', files1);
            fd.append('Image2', files2);
            fd.append('Color', $("#productColor").val().trim());
            fd.append('Size', $("#productSize").val().trim());
            fd.append('Quantity', $("#productQuantity").val().trim());
            fd.append('SKU', $("#productSKU").val().trim());
            fd.append('productId', $("#productId").val().trim());
            $.ajax({
                type: "POST",
                encType : "multipart/form-data",
                url: "Variant?chucNang=them",
                cache : false,
                data: fd,
                contentType: false,
                processData: false,
                success: function (response2) {
                    if (response2.trim() !== "") {
                        error = response2;
                    }
                },
                async: false
            });
        }
        if (error === null) {
            var data =
                    "<tr><td>" +
                    $("#productColor").val().trim() +
                    "</td><td>" +
                    $("#productSize").val().trim() +
                    "</td><td>" +
                    $("#productQuantity").val().trim() +
                    "</td><td>" +
                    $("#productSKU").val().trim() +
                    "</td> <td><img id='img-product1-" +
                    $("#productSKU").val().trim() +
                    "' class='img-product rounded' src='' alt=''></td> <td><img id='img-product2-" +
                    $("#productSKU").val().trim() +
                    "' class='img-product rounded' src='' alt=''></td> <td><a class='btn btn-warning d-block ' href='Variant?chucNang=sua&SKU=" +
                    $("#productSKU").val().trim() +
                    "&pId=" + $("#productId").val().trim() + "'>Sửa</a></td> \
                    <td><button type='button' class='btn btn-warning' data-toggle='modal' data-target='#modalDeleteVariant" + $("#productSKU").val().trim() + "'>Xóa</button>" +
                        "<!-- Modal delete variant--> \
                        <div class='modal fade' id='modalDeleteVariant" + $("#productSKU").val().trim() + "' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'> \
                            <div class='modal-dialog' role='document'> \
                                <div class='modal-content'> \
                                    <div class='modal-header'> \
                                        <h5 class='modal-title' id='exampleModalLabel'>Sản phẩm</h5> \
                                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'> \
                                            <span aria-hidden='true'>&times;</span> \
                                        </button> \
                                    </div> \
                                    <div class='modal-body'>Bạn có muốn xóa sản phẩm màu: <span class='text-danger'>" + $("#productColor").val().trim() + "</span>, size: <span class='text-danger'>" + $("#productSize").val().trim() + "</span> không??</div> \
                                    <div class='modal-footer'> \
                                        <button type='button' class='btn btn-secondary' data-dismiss='modal'>Hủy</button> \
                                        <button type='button' class='btn btn-primary' onclick='deleteVariant(\"" + $("#productSKU").val().trim() + "\")'>Có</button> \
                                    </div> \
                                </div> \
                            </div> \
                        </div></td></tr>";
            $("#tableProductVariant > tbody").append(data);
            var id1 = "img-product1-" + $("#productSKU").val().trim();
            var id2 = "img-product2-" + $("#productSKU").val().trim();
            readURL(document.getElementById("product-image1"), $("#" + id1));
            readURL(document.getElementById("product-image2"), $("#" + id2));
        }
        $("#errorVariant").text(error);
    });

    //Đóng modal sửa trạng thái đơn hàng
    $('#modal').on('hidden.bs.modal', function () {
        window.location.href = "/BTL_CNPM/Order?chucNang=danhSachDonHang";
    });

    //Đóng modal sửa trạng thái tài khoản khách hàng
    $('#modalChangeStatusCustomer').on('hidden.bs.modal', function () {
        window.location.href = "/BTL_CNPM/Customer?chucNang=hien-thi";
    });

    //Đóng modal thông báo xóa sản phẩm
    $('#modalMessageDeleteProduct').on('hidden.bs.modal', function () {
        window.location.href = "/BTL_CNPM/Product?chucNang=hienThi";
    });

    //Đóng modal thông báo xóa biến thể sản phẩm -> get table variant
    $('#modalMessageDeleteVariant').on('hidden.bs.modal', function () {
        reloadTableVariant();
    });

    document.querySelectorAll("#tableProduct th").forEach(headerCell => {
        headerCell.addEventListener("click", () => {
            const tableElement = headerCell.parentElement.parentElement.parentElement;
            const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
            const currentIsAscending = headerCell.classList.contains("th-sort-asc");
            if(headerIndex === 0 || headerIndex === 1 || headerIndex === 2 || headerIndex === 3 || headerIndex === 4 || headerIndex === 5){
                sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
            }
        });
    });

});

//Hiển thị lại bảng variant trong sửa sản phẩm
function reloadTableVariant(){
    var Parent = document.getElementById("tBodyProductVariant");
    while(Parent.hasChildNodes())
    {
       Parent.removeChild(Parent.firstChild);
    }
    var productId = $("#productId").val();
    $.ajax({
        type: "get",
        url: "Variant?chucNang=getVariantByProductId",
        data: "productId="+ productId,
        dataType: "json",
        success: function(response) {
            for (var i = 0; i < response.length; i++) {
                var data =
                    "<tr><td>" +
                    response[i].color +
                    "</td><td>" +
                    response[i].size +
                    "</td><td>" +
                    response[i].quantity +
                    "</td><td>" +
                    response[i].sKU +
                    "</td> <td><img id='img-product1-" +
                    response[i].sKU +
                    "' class='img-product rounded' src='img/" + response[i].image1 + "' alt=''></td> <td><img id='img-product2-" +
                    response[i].sKU +
                    "' class='img-product rounded' src='img/" + response[i].image2 + "' alt=''></td> <td><a class='btn btn-warning d-block ' href='Variant?chucNang=sua&SKU=" +
                    response[i].sKU +
                    "&pId=" + response[i].productId + "'>Sửa</a></td> \
                    <td><button type='button' class='btn btn-warning' data-toggle='modal' data-target='#modalDeleteVariant" + response[i].sKU + "'>Xóa</button>" +
                    "<!-- Modal delete variant--> \
                    <div class='modal fade' id='modalDeleteVariant" + response[i].sKU + "' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'> \
                        <div class='modal-dialog' role='document'> \
                            <div class='modal-content'> \
                                <div class='modal-header'> \
                                    <h5 class='modal-title' id='exampleModalLabel'>Sản phẩm</h5> \
                                    <button type='button' class='close' data-dismiss='modal' aria-label='Close'> \
                                        <span aria-hidden='true'>&times;</span> \
                                    </button> \
                                </div> \
                                <div class='modal-body'>Bạn có muốn xóa sản phẩm màu: <span class='text-danger'>" + response[i].color + "</span>, size: <span class='text-danger'>" + response[i].size + "</span> không??</div> \
                                <div class='modal-footer'> \
                                    <button type='button' class='btn btn-secondary' data-dismiss='modal'>Hủy</button> \
                                    <button type='button' class='btn btn-primary' onclick='deleteVariant(\"" + response[i].sKU + "\")'>Có</button> \
                                </div> \
                            </div> \
                        </div> \
                    </div></td></tr>";
                $("#tableProductVariant > tbody").append(data);
            }
        },
    });
}

//input chỉ nhập số
function isInputNumber(evt) {
    var ch = String.fromCharCode(evt.which);
    if (!/[0-9]/.test(ch)) {
        evt.preventDefault();
    }
};

//Thay đổi đường dẫn ảnh bằng input file
function readURL(input1, input2) {
    if (input1.files && input1.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            input2.attr("src", e.target.result);
        };
        reader.readAsDataURL(input1.files[0]);
    }
}

//Xóa 1 hàng trong bảng variant
function deleteRow1(SKU) {
    var table = document.getElementById("tableProductVariant");
    for (var i = 1; i < table.rows.length; i++) {
        if (SKU === table.rows[i].cells[3].innerHTML) {
            table.deleteRow(i);
            break;
        }
    }
}

//Admin sửa trạng thái tài khoản khách hàng
function myFunction(input, customerId) {
    if(input.checked){
        var statusActive = 1;
    }
    else{
        statusActive = 0;
    }
    $.ajax({
        type: "POST",
        url: "Customer?chucNang=change-status",
        data: "statusActive=" + statusActive + "&customerId=" + customerId,
        success: function (response2) {
            if (response2.trim() === "success") {
                $("#modal-content").text("Sửa trạng thái tài khoản khách hàng thành công");
            } else if (response2.trim() === "fail") {
                $("#modal-content").text("Sửa trạng thái tài khoản khách hàng thất bại");
            }
            $('#modalChangeStatusCustomer').modal('show');
        },
        async: false
    });
}

//Admin sửa trạng thái đơn hàng của khách hàng
function changeOrderStatus(orderStatus, orderId){
    $.ajax({
        type: "post",
        url: "Order?chucNang=suaTrangThai",
        data: "orderStatus=" + orderStatus + "&orderId=" + orderId,
        success: function(response) {
            if (response.trim() === "success") {
                $("#modal-content").text("Sửa trạng thái đơn hàng thành công");
            } else if (response.trim() === "fail") {
                $("#modal-content").text("Sửa trạng thái đơn hàng thất bại");
            }
            $('#modal').modal('show');
        },
    });
}

//Xóa sản phẩm
function deleteProduct(productId){
    $("#modalDeleteProduct"+ productId).modal('hide');
    $.ajax({
        type: "post",
        url: "Product?chucNang=xoa",
        data: "productId=" + productId,
        success: function(response) {
            if (response.trim() === "success") {
                $("#modal-content-message").text("Xóa sản phẩm thành công");
            } else{
                $("#modal-content-message").text("Xóa sản phẩm thất bại");
            }
            $('#modalMessageDeleteProduct').modal('show');
        },
    });
}

//Xóa biến thể sản phẩm
function deleteVariant(sKU){
    $("#modalDeleteVariant"+ sKU).modal('hide');
    $.ajax({
        type: "post",
        url: "Variant?chucNang=xoa",
        data: "SKU=" + sKU,
        success: function(response) {
            if (response.trim() === "success") {
                $("#modal-content-message").text("Xóa biến thể sản phẩm thành công");
            } else{
                $("#modal-content-message").text("Xóa biến thể sản phẩm thất bại");
            }
            $('#modalMessageDeleteVariant').modal('show');
        },
    });
}


function sortTableByColumn(table, column, asc = true) {
    const dirModifier = asc ? "ASC" : "DESC";
    const tBody = table.tBodies[0];
    var columnName = "ProductId";
    if(column === 1){
        columnName = "ProductName";
    }
    else if(column === 2){
        columnName = "CategoryName";
    }
    else if(column === 3){
        columnName = "ProductPrice";
    }
    else if(column === 4){
        columnName = "Sale";
    }
    else if(column === 5){
        columnName = "SaleDate";
    }
    while (tBody.firstChild) {
        tBody.removeChild(tBody.firstChild);
    }
    $.ajax({
        type: "get",
        url: "Product?chucNang=sort",
        data: "columnName="+ columnName + "&type=" + dirModifier,
        dataType: "json",
        success: function(response) {
            for (var i = 0; i < response.length; i++) {
                var data =" \
                    <tr> \
                        <td>" + response[i].productId + "</td> \
                        <td>" + response[i].productName + "</td> \
                        <td>" + response[i].categoryName + "</td> \
                        <td>" + response[i].productPrice + "đ</td> \
                        <td>" + response[i].productSale + "%</td> \
                        <td>" + response[i].saleDate + "</td> \
                        <td> \
                            <c:choose> \
                                <c:when test = '${" + response[i].displayHome +" == 0}'> \
                                    Không \
                                </c:when> \
                                <c:when test = '${" + response[i].displayHome +" == 1}'> \
                                    Có \
                                </c:when> \
                            </c:choose> \
                        </td> \
                        <td><c:if test = '${" + response[i].image + " != null}'><img src='img/" + response[i].image + "' alt='' class='img-thumbnail img-product'></c:if></td> \
                        <td>" + response[i].adminId + "</td> \
                        <td> \
                            <a href='Product?chucNang=sua&productId=" + response[i].productId + "' class='btn btn-warning d-block ' role='button'>Sửa</a> \
                        </td> \
                        <td> \
                            <button type='button' class='btn btn-warning' data-toggle='modal' data-target='#modalDeleteProduct" + response[i].productId + "'>Xóa</button> \
                            <!-- Modal delete product--> \
                            <div class='modal fade' id='modalDeleteProduct" + response[i].productId + "' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'> \
                                <div class='modal-dialog' role='document'> \
                                    <div class='modal-content'> \
                                        <div class='modal-header'> \
                                            <h5 class='modal-title' id='exampleModalLabel'>Sản phẩm</h5> \
                                            <button type='button' class='close' data-dismiss='modal' aria-label='Close'> \
                                                <span aria-hidden='true'>&times;</span> \
                                            </button> \
                                        </div> \
                                        <div class='modal-body'>Bạn có muốn xóa sản phẩm: <span class='text-danger'>" + response[i].productName + "</span> không??</div> \
                                        <div class='modal-footer'> \
                                            <button type='button' class='btn btn-secondary' data-dismiss='modal'>Hủy</button> \
                                            <button type='button' class='btn btn-primary' onclick='deleteProduct(\"" + response[i].productId + "\")'>Có</button> \
                                        </div> \
                                    </div> \
                                </div> \
                            </div> \
                        </td> \
                    </tr> \
                    ";
                $("#tableProduct > tbody").append(data);
            }
        },
    });
    // Remember how the column is currently sorted
    table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
    table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-asc", asc);
    table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-desc", !asc);
}

function requestProductFilter(){
    var url = "Product?chucNang=hienThi";
    var category = $("input[name='filterCategory']:checked").val();
    var valueFilterPrice = $("input[name='filterPrice']:checked").val();
    if(category != null && category.trim() !== ""){
        url += "&danhMucSanPham=" + category;
    }
    if(valueFilterPrice === '0-200000'){
        url += "&minPrice=0&maxPrice=200000";
    }
    else if(valueFilterPrice === '200000-400000'){
        url += "&minPrice=200000&maxPrice=400000";
    }
    else if(valueFilterPrice === '400000-600000'){
        url += "&minPrice=400000&maxPrice=600000";
    }
    else if(valueFilterPrice === '600000-800000'){
        url += "&minPrice=600000&maxPrice=800000";
    }
    else if(valueFilterPrice === '800000-1200000'){
        url += "&minPrice=800000&maxPrice=1200000";
    }
    else if(valueFilterPrice === '1200000-1800000'){
        url += "&minPrice=1200000&maxPrice=1800000";
    }
    window.location.href = url;
}

function resetValue(radio){
    radio.val("");
}


