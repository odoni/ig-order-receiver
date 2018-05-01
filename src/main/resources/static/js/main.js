$(document).ready(function () {
    $("#btn-submit").click(function (event) {
        event.preventDefault();
        submitOrders();
    });
});

function submitOrders() {
    var form = $("#send-orders-form")[0];
    var data = new FormData(form);

    $("#btn-submit").prop("disabled", true);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/order/api/v1/order",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#btn-submit").prop("disabled", false);
            $("#result").text(data.message);
            console.log("SUCCESS : ", data);
        },
        error: function (e) {
            $("#btn-submit").prop("disabled", false);
            $("#result").html(extractErrorMessageFromResponse(e.responseJSON));
            console.log("ERROR : ", e);
        }
    });
}

function extractErrorMessageFromResponse(response) {
    var message = "";
    if (response.errors) {
        $(response.errors).each(function() {
            message += this.message + " <br>"
        });
    }
    return message;
}