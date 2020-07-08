$(document).ready(function () {
    $("#btn-search").attr('disabled', true);
    $("#input-search").on('keyup', function () {
        var text_value = $("#input-search").val();
        if (text_value != '') {
            $("#btn-search").attr('disabled', false);
            console.log("tutu");
        } else {
            $("#btn-search").attr('disabled', true);
        }
    });
});