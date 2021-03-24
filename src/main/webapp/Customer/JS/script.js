$(document).ready(function () {
    $(".slider-for").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        fade: true,
        asNavFor: ".slider-nav",
        prevArrow: false,
        nextArrow: false,
    });
    $(".slider-nav").slick({
        slidesToShow: 3,
        slidesToScroll: 1,
        asNavFor: ".slider-for",
        dots: false,
        centerMode: true,
        focusOnSelect: true,
        prevArrow: false,
        nextArrow: false,
        responsive: [
            {
                breakpoint: 720,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1,
                },
            },
        ],
    });
    $(".autoplay").slick({
        slidesToShow: 6,
        slidesToScroll: 6,
        autoplay: true,
        autoplaySpeed: 4000,
        prevArrow: false,
        nextArrow: false,
        responsive: [
            {
                breakpoint: 720,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2,
                    autoplaySpeed: 4000,
                },
            },
        ],
    });

    $( "#color" ).change(function() {
        $("#size").find("option[value!= '']").remove();
        getSize($("#color").val(), $("#productId").val());
        getSKU($("#color").val(), $("#size").val(), $("#productId").val());
    });

    $( "#size" ).change(function() {
        getSKU($("#color").val(), $("#size").val(), $("#productId").val());
    });

    getSize($("#color").val(), $("#productId").val());
});

function getSize(color, productId){
    $.ajax({
        type: "get",
        url: "Variant?chucNang=getSize",
        data: "Color="+ color + "&productId=" + productId,
        dataType: "json",
        success: function(response) {
            for (var i = 0; i < response.length; i++) {
                var o = new Option(response[i], response[i]);
                $(o).html(response[i]);
                $("#size").append(o);
            }
        },
    });
}

function getSKU(color, size, productId){
    $.ajax({
        type: "get",
        url: "Variant?chucNang=getSKU",
        data: "Color="+ color + "&Size=" + size + "&productId=" + productId,
        success: function(response) {
            $("#labelSKU").text("SKU: " + response);
            $("#SKU").val(response);
        },
    });
}

function quantityPlus(inputQuantity){
    var quantity = parseInt(inputQuantity.val());
    if(Number.isNaN(quantity)){
        quantity = 0;
    }
    inputQuantity.val(quantity + 1);
}

function quantityMinus(inputQuantity){
    var quantity = parseInt(inputQuantity.val());
    if(Number.isNaN(quantity)){
        inputQuantity.val(1);
    }
    if(quantity > 1){
        inputQuantity.val(quantity - 1);
    }
}

function isInputNumber(evt) {
    var ch = String.fromCharCode(evt.which);
    if (!/[0-9]/.test(ch)) {
        evt.preventDefault();
    }
}

function loadMore() {
    var amountProduct = document.getElementsByClassName('product').length;
    $.ajax({
        url: "Product?chucNang=loadMore",
        type:"get",
        data: {
            amountProduct: amountProduct
        },
        success:function(data){
            var row = document.getElementById("list-product");
            row.innerHTML += data;
        }
    });
}

function addProductToCart(){
    var color = $("#color").val();
    var size = $("#size").val();
    var productId = $("#productId").val();
    var quantity = $("#quantity").val();
    $.ajax({
        type: "post",
        url: "OrderDetail?chucNang=them",
        data: "Color="+ color + "&Size=" + size + "&productId=" + productId + "&quantity=" + quantity,
        success: function(response) {
            $("#errorColor").text("");
            $("#errorSize").text("");
            $("#errorQuantity").text("");
            if(response === "Thêm sản phẩm vào giỏ hàng thành công"){
                alert(response);
                window.location.href = "/BTL_CNPM/Product?chucNang=chiTietSanPham&productId=" + productId;
            }
            else{
                $("#errorColor").text(response.errorColor);
                $("#errorSize").text(response.errorSize);
                $("#errorQuantity").text(response.errorQuantity);
            }
        },
    });
}

function changeQuantity(inputQuantity, sKU){
    var quantity = parseInt(inputQuantity.val());
    $.ajax({
        type: "post",
        url: "OrderDetail?chucNang=sua",
        data: "sKU=" + sKU + "&quantity=" + quantity,
        success: function(response) {
            if(response === "Số lượng sản phẩm không thể đáp ứng. Vui lòng chọn lại số lượng"){
                $("#error-" + sKU).text(response);
            }
            else{
                window.location.href = "/BTL_CNPM/OrderDetail?chucNang=hienThi";
            }
        },
    });
}

function order(){
    var addressOrder = $("#addressOrder").val();
    var numberPhoneOrder = $("#numberPhoneOrder").val();
    var paymentMethod = $("input[name='paymentMethod']:checked").val();
    $.ajax({
        type: "post",
        url: "Order?chucNang=them",
        data: "addressOrder=" + addressOrder + "&numberPhoneOrder=" + numberPhoneOrder + "&paymentMethod=" + paymentMethod,
        success: function(response) {
            $("#errorAddress").text("");
            $("#errorNumberPhone").text("");
            $("#errorPayment").text("");
            if(response === "Vui lòng đăng nhập"){
                window.location.href = "/BTL_CNPM/Customer?chucNang=dang-nhap";
            }
            else if(response === "Đặt hàng thành công. Vui lòng xem lại thông tin đơn hàng" || response === "Đặt hàng thất bại"){
                $("#modal-message").modal('show');
                $("#modal-message-body").text(response);
            }
            else {
                $("#errorAddress").text(response.errorAddress);
                $("#errorNumberPhone").text(response.errorNumberPhone);
                $("#errorPayment").text(response.errorPayment);
            }
        },
    });
}

function changeAddressOrder(){
    var address = $("#addressOrderModal").val();
    if(address.trim() === ""){
        $("#errorAddressModal").text("Vui lòng nhập địa chỉ");
    }
    else if(address.trim().length > 100){
        $("#errorAddressModal").text("Địa chỉ dài quá giới hạn(100 ký tự)");
    }
    else{
        $("#addressOrder").val(address);
        $("#modalAddress").modal('hide');
    }
}

function redirectPage(){
    window.location.href = "/BTL_CNPM/OrderDetail?chucNang=hienThi";
}
