<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MWGA Tool
  Date: 15.05.2018
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="styles/pattern-style.css">
    <link rel="stylesheet" href="styles/welcome-style.css">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="jquery-3.3.1.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <script src="script.js"></script>
    <title>Welcome</title>
</head>
<body>
<div id="sign_in_modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4>Вход в учетную запись</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="start" method="post">
                    <input type="hidden" name="action" value="sign_in">
                    <div class="form-group">
                        <label for="sign-in-login" class="control-label col-sm-2">Логин</label>
                        <div class="col-sm-10">
                            <input id="sign-in-login" class="form-control" type="text" name="login"
                                   placeholder="Введите логин...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sign-in-password" class="control-label col-sm-2">Пароль</label>
                        <div class="col-sm-10">
                            <input id="sign-in-password" class="form-control" type="password" name="password"
                                   placeholder="Введите логин...">
                        </div>
                    </div>
                    <button class="btn btn-success" type="submit">Войти</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="sign_up_modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Регистрация</h4>
            </div>
            <div class="modal-body">
                <form id="sign_up_form" class="form-horizontal">
                    <div class="form-group has-feedback">
                        <input type="hidden" name="action" value="sign_up_user">
                        <label for="up_login" class="control-label col-sm-2">Логин:</label>
                        <div class="col-sm-10">
                            <input id="up_login" class="form-control" type="text" name="login"
                                   placeholder="Введите логин...">
                            <span class="glyphicon glyphicon-ok form-control-feedback val-obj"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback val-obj"></span>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="up_password" class="control-label col-sm-2">Пароль:</label>
                        <div class="col-sm-10">
                            <input id="up_password" class="form-control" type="password" name="password"
                                   placeholder="Введите пароль...">
                            <span class="glyphicon glyphicon-ok form-control-feedback val-obj"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback val-obj"></span>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="up_name" class="control-label col-sm-2">Имя:</label>
                        <div class="col-sm-10">
                            <input id="up_name" class="form-control" type="text" name="name"
                                   placeholder="Введите имя...">
                            <span class="glyphicon glyphicon-ok form-control-feedback val-obj"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback val-obj"></span>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="up_surname" class="control-label col-sm-2">Фамилия:</label>
                        <div class="col-sm-10">
                            <input id="up_surname" class="form-control" type="text" name="surname"
                                   placeholder="Введите Фамилию">
                            <span class="glyphicon glyphicon-ok form-control-feedback val-obj"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback val-obj"></span>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="up_group" class="control-label col-sm-2">Группа:</label>
                        <div class="col-sm-10">
                            <input id="up_group" class="form-control" type="text" name="group"
                                   placeholder="Группа...">
                            <span class="glyphicon glyphicon-ok form-control-feedback val-obj"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback val-obj"></span>
                        </div>
                    </div>
                    <button class="btn btn-success" type="submit">Подтвердить</button>
                </form>
            </div>
        </div>
    </div>
</div>
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
        <div class="welcome">
            <h3>Основные инструкции</h3>
            <div class="welcome-text">
                <p>Данное приложение разработано с целью обучения студентов основам тестирования безопасности
                    веб-приложения. Теоретические сведения изложены в соответствии с различными категориями уязвимостей
                    web-приложений.</p>
                <h4>Что такое исходный код? Как его используют.</h4>
                <p><b>Исходный код</b> (также исходный текст) — текст компьютерной программы на каком-либо языке
                    программирования или языке разметки, который может быть прочтён человеком. Исходный код
                    транслируется в исполняемый код целиком до запуска программы при помощи компилятора, или может
                    исполняться сразу при помощи интерпретатора.
                    Сходный код для web-страницы состоит из трёх частей:
                <ul>
                    <li>Информация о версии HTML.</li>
                    <li>Шапка веб-страницы, в которой содержится техническая информация (
                        <head>).
                    </li>
                    <li>Тело веб-страницы(
                        <?body>). Все, что отображается на web-странице, находится в тегах. Это текст, картинки и
                        исполняющиеся скрипты, а также теги для оформления.
                        Исходный код может являться источником утечки конфиденциальных данных.
                    </li>
                </ul>
                </p>
                <h4>Что такое JavaScript?</h4>
                <p>Программы на клиентских языках обрабатываются на стороне пользователя, как правило, их выполняет браузер.
                    Язык JavaScript — это клиентский язык web-программирования, который был создан в 1995 году, разработчиком Бренданом Айком.
                    JavaScript обычно применяется для манипулирования объектами в различных приложениях, но наибольшую популярность он приобрел как один из основных языков применяемых при создании сайтов (и как единственный клиентский язык web-программирования).
                    Код языка JavaScript обычно исполняется в окне браузера на открытой странице сайта. Это происходит благодаря тому что в веб-браузере по умолчанию имеется интерператор языка JavaScript, благодаря которому браузер имеет возможность понимать и исполнять код написанный на языке JavaScript.
                </p>
                <h4>Что такое MIME и для чего необходим?</h4>
                <p>Процесс преобразования сообщения в комбинацию символов в соответствии с кодом называется кодированием, процесс восстановления сообщения из комбинации символов называется декодированием.
                    <b>base64</b>
                    В формате электронной почты <b>MIME base64</b> — это схема, по которой произвольная последовательность байт преобразуется в последовательность печатных ASCII символов. Это определяет MIME как транспортное кодирование содержимого для использования в электронной почте. Используются только символы латинского алфавита в верхнем и нижнем регистре — символы (A—Z, a—z), цифры (0—9), и символы «+» и «/», с символом «=» в качестве специального кода суффикса.
                    Полная спецификация этой формы base64 содержится в RFC 1421 и RFC 2045. Эта схема используется для кодирования последовательности октетов (байт). Это соответствует определению файлов почти во всех системах. Результирующие закодированные по base64 данные имеют длину, большую оригинальной на 33%, а именно, в соотношении 4:3 (каждым 3 байтам оригинального текста соответствуют 4 символа base64), и напоминают по виду случайные символы.
                </p>
            </div>
            <div id="start-button">
                <form method="post" action="start">
                    <input type="hidden" name="action" value="go_new_task">
                    <input type="hidden" name="complete" value="true">
                    <button class="button" id="next_task" type="submit">Начать задание</button>
                </form>
            </div>
        </div>
    </div>
</div>
<footer>
    <div class="author-info">
        <p>Dev by nickaden</p>
        <p>daelset@yandex.by</p>
        <p>BSUIR, 2018</p></div>
</footer>
</body>
</html>
