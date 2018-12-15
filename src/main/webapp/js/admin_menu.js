$(document).ready(function () {

    $('.sign-in-task').show();
    $('.admin-menu').hide();
    $('.user-menu').hide();

    $('#solve').click(function () {

        var msg=$(this).parent().parent().serialize();

        $.ajax({
            type: 'POST',
            url: 'start',
            data: msg,
            success: function (data) {
                if (data.indexOf('user login') != -1 && data.indexOf('user password') != -1){
                    $('.sign-in-task').hide();
                    $('.admin-menu').hide();
                    $('.user-menu').show();
                } else if (data.indexOf('wrong login')!=-1 || data.indexOf('wrong password')!=-1){
                    $('.info').css('color','red');
                    $('.info').text('Неверные данные для входа. Попробуйте ещё');
                } else {
                    $('.sign-in-task').hide();
                    $('.admin-menu').show();
                    $('.user-menu').hide();
                    $('.info').text('Подтверждено! Переходите к следющему заданию');
                    $('.info').css('color','green');
                }
            },
            error: function (xhr, str) {
                alert('Возникла ошибка!');
            }
        });

        return false;
    });

    $('.back').click(function () {
        $('.sign-in-task').show();
        $('.user-menu').hide();
        $('.admin-menu').hide();
    })
});