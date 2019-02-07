<#--В шаблонизаторе Freemarker применяются макросы
определяются с помощью директивы <#macro name>
вызываются с помощью
<#import "parts/common.ftl" as common>  common - это алиас макроса для конкретной формы

<@common.page>

для добавления одной формы в другую (или через шаблон)
<#include "navbar.ftl"> элемент navbar будет отображен везде, где применен макрос <@common.page>
-->

<#macro page>
    <!DOCTYPE HTML>
<html>
<head>
    <title>Simple Diagnostic Tool</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<#--Добавление статических стилей-->
    <link rel="stylesheet" href="/static/style.css">
<#--Учитывать плотность пикселей, чтобы изменять масштаб для моб версии-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<#--Добавляет иконки и пиктограммки-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

    <!-- Подключение стилей Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css" integrity="2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js" integrity="VjEeINv9OSwtWFLAtmc4JCtEJXXBub00gtSnszmspDLCtC0I4z4nqz7rEFbIZLLU" crossorigin="anonymous"></script>
<#--Добавление капчи от Гугл-->
    <script src='https://www.google.com/recaptcha/api.js'></script>

<#--Фреймворк для кеширования страниц, для ускорения их отображения-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/turbolinks/5.2.0/turbolinks.js"></script>
<#--Скрипт будет отрабатывать после полной загрузки страницы defer-->
<#--Подключаем JQuery-->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
<#--Подключаем зависимости popper (всплывающие окна и т.д.)-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
            integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
            crossorigin="anonymous"></script>
    <style>
        .turbolinks-progress-bar {
            height: 2px;
            background-color: navy;
        }
    </style>
</head>
<body>
    <#include "navbar.ftl">
<#--Элемент Grid Bootstrap для выравнивания контента
mt-5 отступ margin сверху top на размер 5-->
<div class="container mt-5">
<#nested>
</div>

<#--Добавляем JQuery плагины обеспечивающие функциональность-->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>

</body>
</html>

</#macro>