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
                if (data.indexOf('empty field')!=-1 ){
                    info_msg+='Не оставляйте поля пустыми!\n';
                    infoBlock.css('color','red');
                    infoBlock.text(info_msg);
                }
                else {
                    data=JSON.parse(data);
                    if(data.redirect){
                        window.location.href=data.redirect;
                    }
                }
            },
            error: function (xhr, str) {
                alert('Возникла ошибка!');
            }
        });

        return false;
    })
});