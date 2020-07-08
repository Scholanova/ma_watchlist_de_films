$('#Modal').on('show.bs.modal', function (event) {
    var myVal = $(event.relatedTarget).data('val');
    $(this).find("#apiFilmId").val(myVal);
});