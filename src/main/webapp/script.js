$(document).ready(function f() {

    $('.glyphicon-ok').hide();
    $('.glyphicon-remove').hide();

    var jVal = {
        'login': function (input) {
            var elem = input;
            if (elem.val().length < 4 || elem.val().length > 20) {
                jVal.error = true;
                elem.parent().removeClass('has-success').addClass('has-error');
                elem.siblings('.glyphicon-remove').show();
                elem.siblings('.glyphicon-ok').hide();
            } else {
                elem.parent().removeClass('has-error').addClass('has-success');
                elem.siblings('.glyphicon-ok').show();
                elem.siblings('.glyphicon-remove').hide();
            }
        },
        'email': function (input) {
            var elem = input;
            var pattern = /^.+@.+[.].{2,}$/i;
            if (!pattern.test(elem.val())) {
                jVal.error = true;
                elem.parent().removeClass('has-success').addClass('has-error');
                elem.siblings('.glyphicon-remove').show();
                elem.siblings('.glyphicon-ok').hide();
            } else {
                elem.parent().removeClass('has-error').addClass('has-success');
                elem.siblings('.glyphicon-ok').show();
                elem.siblings('.glyphicon-remove').hide();
            }
        },
        'firstName': function (input) {
            var elem = input;
            if (elem.val().length < 4 || elem.val().length > 20) {
                jVal.error = true;
                elem.parent().removeClass('has-success').addClass('has-error');
                elem.siblings('.glyphicon-remove').show();
                elem.siblings('.glyphicon-ok').hide();
            } else {
                elem.parent().removeClass('has-error').addClass('has-success');
                elem.siblings('.glyphicon-ok').show();
                elem.siblings('.glyphicon-remove').hide();
            }
        },
        'lastName': function (input) {
            var elem = input;
            if (elem.val().length < 4 || elem.val().length > 20) {
                jVal.error = true;
                elem.parent().removeClass('has-success').addClass('has-error');
                elem.siblings('.glyphicon-remove').show();
                elem.siblings('.glyphicon-ok').hide();
            } else {
                elem.parent().removeClass('has-error').addClass('has-success');
                elem.siblings('.glyphicon-ok').show();
                elem.siblings('.glyphicon-remove').hide();
            }
        },
        'password': function (input) {
            var elem = input;
            var pattern = /\w{4,}/;
            if (!pattern.test(elem.val())) {
                jVal.error = true;
                elem.parent().removeClass('has-success').addClass('has-error');
                elem.siblings('.glyphicon-remove').show();
                elem.siblings('.glyphicon-ok').hide();
            } else {
                elem.parent().removeClass('has-error').addClass('has-success');
                elem.siblings('.glyphicon-ok').show();
                elem.siblings('.glyphicon-remove').hide();
            }
        },
        'sendIt': function () {
            if (!jVal.error) {
                return true;
            } else {
                return false;
            }
        },
        'description': function (textarea) {
            var elem = textarea;
            if (elem.val().length == 0) {
                jVal.error = true;
                elem.parent().removeClass('has-success').addClass('has-error');
            } else {
                elem.parent().removeClass('has-error').addClass('has-success');
            }
        },
        'title': function (input) {
            var elem = input;
            if (elem.val().length < 4 || elem.val().length > 1024) {
                jVal.error = true;
                elem.parent().removeClass('has-success').addClass('has-error');
            } else {
                elem.parent().removeClass('has-error').addClass('has-success');
            }
        },
        'group': function (input) {
            var elem= input;
            var pattern='/\d+/';
            if(elem.val().length !=6){
                jVal.error = true;
                elem.parent().removeClass('has-success').addClass('has-error');
            } else {
                elem.parent().removeClass('has-error').addClass('has-success');
            }
        }
    };

    $('#sign-in').click(function () {

        $('#sign_in_modal').modal();
        return false;
    });

    $('#sign_in_modal').find('button[type="submit"]').click(function () {

        var form=$('#sign_in_modal').find('form');
        var form=form.serialize();
        $.ajax({
            type: 'POST',
            url: 'start',
            data: form,
            success: function (data) {
                if (data == 'error') {
                    $('.sign-in-error').fadeIn(300);
                } else {
                    location.reload();
                }
            },
            error: function (xhr, str) {
                alert('Возникла ошибка!');
            }
        });
        return false;
    });

    $('#sign-up').click(function () {

        $('#sign_up_modal').modal();
        return false;
    });

    $('#sign_up_form').find('button[type="submit"]').click(function () {
        jVal.error = false;
        jVal.login($('#up_login'));
        jVal.password($('#up_password'));
        jVal.firstName($('#up_name'));
        jVal.lastName($('#up_surname'));
        jVal.group($('#up_group'));
        var sent = jVal.sendIt();
        if (sent) {
            var form = $('#sign_up_form').serialize();
            $.ajax({
                type: 'POST',
                url: 'start',
                data: form,
                success: function (data) {
                    if (data == 'error') {
                        var alert=$('<div class="form-group"></div>');
                        alert.html('<div class="user-exist alert alert-warning alert-dismissible" style="margin: 10px 5px;">\n' +
                            '    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>\n' +
                            '    <strong>Ошибка!</strong> Данный пользователь уже существует.\n' +
                            '</div>');
                        $('#sign_up_form').append(alert);
                    } else {
                        location.reload();
                    }
                },
                error: function (xhr, str) {
                    alert('Возникла ошибка!');
                }
            });
        }
        return false;
    })

});