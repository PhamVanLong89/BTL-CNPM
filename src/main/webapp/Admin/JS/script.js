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
                    "' class='img-product img-thumbnail' src='img/product.jpg' alt=''></div><div class='row'><input type='file' name='product-image1' accept='.jpg, .jpeg, .png' onchange='readURL(this, $(\"#img-product1-" +
                    $("#productSKU").val().trim() + "\"))' /></div></td> <td><div class='row'><img id='img-product2-" +
                    $("#productSKU").val().trim() +
                    "' class='img-product img-thumbnail' src='img/product.jpg' alt=''></div><div class='row'><input type='file' name='product-image2' accept='.jpg, .jpeg, .png' onchange='readURL(this, $(\"#img-product2-" +
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
                    "' class='img-product img-thumbnail' src='img/product.jpg' alt=''></td> <td><img id='img-product2-" +
                    $("#productSKU").val().trim() +
                    "' class='img-product img-thumbnail' src='img/product.jpg' alt=''></td> <td><a class='' href='Variant?chucNang=sua&SKU=" +
                    $("#productSKU").val().trim() +
                    "&pId=" + $("#productId").val().trim() + "'>Sửa</a></td><td><a class='' href='Variant?chucNang=xoa&SKU=" +
                    $("#productSKU").val().trim() +
                    "&pId=" + $("#productId").val().trim() + "'>Xóa</a></td></tr>";
            $("#tableProductVariant > tbody").append(data);
            var id1 = "img-product1-" + $("#productSKU").val().trim();
            var id2 = "img-product2-" + $("#productSKU").val().trim();
            readURL(document.getElementById("product-image1"), $("#" + id1));
            readURL(document.getElementById("product-image2"), $("#" + id2));
        }
        $("#errorVariant").text(error);
    });

});

function isInputNumber(evt) {
    var ch = String.fromCharCode(evt.which);
    if (!/[0-9]/.test(ch)) {
        evt.preventDefault();
    }
}
;

function readURL(input1, input2) {
    if (input1.files && input1.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            input2.attr("src", e.target.result);
        };
        reader.readAsDataURL(input1.files[0]);
    }
}


function deleteRow1(SKU) {
    var table = document.getElementById("tableProductVariant");
    for (var i = 1; i < table.rows.length; i++) {
        if (SKU === table.rows[i].cells[3].innerHTML) {
            table.deleteRow(i);
            break;
        }
    }
}

//function deleteForm() {
//    $("input").val("");
//    $("textarea").text("");
//    $("select").prop('selectedIndex', 0);
//    $("form img").attr("src", "img/no_avatar.jpg");
//}


