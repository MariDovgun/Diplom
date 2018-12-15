<%--
  Created by IntelliJ IDEA.
  User: MWGA Tool
  Date: 16.05.2018
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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

    <script src="js/sign_in.js"></script>

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
        <div class="task-horizontal">
            <h4>Вам даны привелегии изменения данных. Но вам этого не достаточно. Вы хотите истреблять и удалять!
                Попробуйте выбрать пункт "Delete" в чекбоксе</h4>
            <div class="tip-horizontal">
                <h2>Подсказки</h2>
                <div id="pay-tip-image" class="tip-image">
                    <img src="images/tip-sign.png" width="30px" height="30px">
                </div>
                <div id="free-tip-image" class="tip-image">
                    <img src="images/free-tip-sign.png" width="30px" height="30px">
                </div>
                <div class="tip-text"></div>
            </div>
            <form method="GET" action="start">
                <input type="hidden" name="action" value="solve_custom_option">
                <select name="choose">
                    <option value="Add">Add</option>
                    <option value="Rename">Rename</option>
                    <option value="Edit">Edit</option>
                </select>
                <button type="submit">Выполнить</button>
            </form>
            <c:if test="${requestScope.choose != null}">
                <c:out value="Ты выбрал: '${requestScope.choose}'"/>
            </c:if>
            <c:if test="${sessionScope.task.complete == true }">
                <p class="success">Удалено! Переходите к следующему заданию</p>
            </c:if>
        </div>
        <form method="post" action="start">
            <input type="hidden" name="action" value="go_new_task">
            <button class="button" id="next_task" type="submit">Следующее задание</button>
        </form>
    </div>
</div>
</div>
<footer>
</footer>
</body>
</html>
