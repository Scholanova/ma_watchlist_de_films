$(document).ready(function() {
    $('#Modal').on('show.bs.modal', function (event) {
        var myVal = $(event.relatedTarget).data('val');
        $(this).find("#apiFilmId").val(myVal);
    });


    $('button[id^="list"]').click(function(){
       console.log($(this).attr('id'));
       $('#idList').val($(this).attr('id'));
    });
});




