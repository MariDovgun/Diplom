<%--
  Created by IntelliJ IDEA.
  User: MWGA Tool
  Date: 18.05.2018
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
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

    <script src="js/general.js"></script>

    <title>Pattern</title>
</head>
<body>
<header>
    <a class="site-title" href="welcome">Test me Soft</a>
    <div id="sign-in-block"></div>
    <div id="sign-in-links">
        <a href="">Sign in</a>
        <a href="">Register</a>
    </div>
</header>
<div class="center-block">
    <div class="workspace">
        <div class="task-horizontal">
            <div class="tip-horizontal">
                    <div id="pay-tip-image" class="tip-image">
                        <img src="images/tip-sign.png" width="30px" height="30px">
                    </div>
                    <div id="free-tip-image" class="tip-image">
                        <img src="images/free-tip-sign.png" width="30px" height="30px">
                    </div>
                    <div class="tip-text"></div>
                </div>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="text">
                <button id="send" class="btn btn-primary">Написать текст</button>
                <p class="info"></p>
            </div>
            <span>Вы ввели:</span>
            <span class="clear-output"></span>
            <p>Будет выведено:</p>
            <div class="output"></div>
            <form method="post" action="start">
                <input type="hidden" name="action" value="go_new_task">
                <button class="button" id="next_task" type="submit">Следующее задание</button>
            </form>
        </div>
    </div>
</div>
<footer>

</footer>
<script>
    $(document).ready(function () {

        $('#send').click(function () {

            var text = $('input[name="text"]').val();

            $('.clear-output').text(text);
            $('.output').html(text);
            if ($('.output').children().length > 0) {
                $.ajax({
                    type: 'POST',
                    url: 'start',
                    data: 'action=solve_format_data&code=1104',
                    success: function (data) {
                        $('.info').css({color: 'green'}).text('Подтверждено! Переходите к следющему заданию.');
                    },
                    error: function (xhr, str) {
                        alert('Возникла ошибка!');
                    }
                });
            }
        })

    })
</script>
</body>
</html>
