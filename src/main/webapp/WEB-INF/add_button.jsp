<%--
  Created by IntelliJ IDEA.
  User: MWGA Tool
  Date: 07.06.2018
  Time: 15:38
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

    <script src="js/general.js"></script>

    <script src="js/sign_in.js"></script>

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
                <h2>Отправьте данные на сервер..подождите, а где кнопка?</h2>
                <form method="post" action="start">
                    <input type="hidden" name="action" value="solve_add_button">
                    <div id="sign-in-form">
                        <div class="input">
                            <label for="name">Имя</label>
                            <input id="name" type="text" name="login">
                        </div>
                        <div class="input">
                            <label for="surname">Фамилия</label>
                            <input id="surname" type="text" name="password">
                        </div>
                        <div class="input">
                            <label for="card">Номер карты</label>
                            <input id="card" type="text" name="card">
                        </div>
                        <c:if test="${sessionScope.task.complete==true}">
                            <p class="info">Подтверждено! Переходите к следующему заданию.</p>
                        </c:if>
                    </div>
                </form>
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
</body>
</html>
