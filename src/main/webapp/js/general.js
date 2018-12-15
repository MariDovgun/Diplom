$(document).ready(function () {

    $('#pay-tip-image').click(function () {
        var msg='action=get_tip';
        $.ajax({
            type: 'POST',
            url: 'start',
            data: msg,
            success: function (data) {
                $('.tip-text').text(data);
                $('.tip-text').slideDown(400);
            },
            error: function (xhr, str) {

            }
        });
    });

    $('#free-tip-image').click(function () {
        var msg='action=get_free_tip';
        $.ajax({
            type: 'POST',
            url: 'start',
            data: msg,
            success: function (data) {
                $('.tip-text').text(data);
                $('.tip-text').slideDown(400);
            },
            error: function (xhr, str) {

            }
        });
    });

});