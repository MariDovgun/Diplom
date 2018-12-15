<%--
  Created by IntelliJ IDEA.
  User: MWGA Tool
  Date: 31.05.2018
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/pattern-style.css">
    <link rel="stylesheet" href="styles/sign_in.css">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="jquery-3.3.1.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <script src="js/admin_menu.js"></script>
    <script src="js/general.js"></script>

    <title>${sessionScope.task.title}</title>
</head>
<body>
<header>
    <a class="site-title" href="welcome">Test me Soft</a>
    <div class="sign-in-block">
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div class="user-info">
                    <a href=""><c:out value="${sessionScope.user.login}"/></a>
                    <form method="post" action="start">
                        <input type="hidden" name="action" value="sign_out">
                        <button type="submit">Выйти</button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <a href="" id="sign-in">Войти</a>
                <a href="" id="sign-up">Регистрация</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>
<div class="center-block">
    <div class="workspace">
        <div class="task">
            <div class="tip">
                <div id="pay-tip-image" class="tip-image">
                    <img src="images/tip-sign.png" width="30px" height="30px">
                </div>
                <div id="free-tip-image" class="tip-image">
                    <img src="images/free-tip-sign.png" width="30px" height="30px">
                </div>
                <div class="tip-text"></div>
            </div>
            <div class="sign-in-task">
                <h2>Поле входа в аккаунт</h2>
                <form method="post" action="start">
                    <input type="hidden" name="action" value="solve_code_concat">
                    <div id="sign-in-form">
                        <div class="input">
                            Логин
                            <input type="text" name="login">
                        </div>
                        <div class="input">
                            Пароль
                            <input type="text" name="password">
                        </div>
                        <button id="js-solve" type="submit">Войти</button>
                        <p class="info"></p>
                    </div>
                </form>
            </div>
            <div class="admin-menu">
                <h2>Меню администратора. Удалите пару записей.</h2>
                <button class="back">Назад</button>
            </div>
            <div class="user-menu">
                <h2>Тут нечего делать. Возвращайтесь назад</h2>
                <button class="back">Назад</button>
            </div>
        </div>
        <form method="post" action="start">
            <input type="hidden" name="action" value="go_new_task">
            <button class="button" id="next_task" type="submit">Следующее задание</button>
        </form>
    </div>
</div>
<footer>
</footer>
<script>
    $('#js-solve').click(function () {

        var CORRECT_LOGIN="musi";
        var CORRECT_PASSWORD="pass";

        var msg=$(this).parent().parent().serialize();

        CORRECT_LOGIN+="tusi";
        CORRECT_PASSWORD+="word";

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

    });
</script>
</body>
</html>
