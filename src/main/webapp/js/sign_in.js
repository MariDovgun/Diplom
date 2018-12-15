$(document).ready(function () {


    $('#solve').click(function () {

        var msg=$(this).parent().parent().serialize();

        $.ajax({
            type: 'POST',
            url: 'start',
            data: msg,
            success: function (data) {
                var info_msg='';
                var infoBlock=$('.info');
                if (data.indexOf('wrong login')!=-1){
                    info_msg+='Неверный логин\n';
                    infoBlock.css('color','red').append(info_msg);
                }
                if (data.indexOf('wrong password')!=-1){
                    info_msg+='Неверный пароль\n';
                    infoBlock.css('color','red').append(info_msg);
                }
                if (info_msg==''){
                    info_msg+='Подтверждено! Переходите к следующему заданию';
                    infoBlock.css('color','green');
                    infoBlock.append(info_msg);
                }
                infoBlock.text(info_msg);
            },
            error: function (xhr, str) {
                alert('Возникла ошибка!');
            }
        });

        return false;
    })
});