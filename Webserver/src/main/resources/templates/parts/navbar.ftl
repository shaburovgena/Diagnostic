<#include "/parts/security.ftl">
<#import "login.ftl" as login>

<#--Панель навигации для lg (large) экранов в светлом стиле
на маленьких экранах будет показан только этот элемент-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Diagnostic</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
<#--Часть панели навигации, содержащее пункты меню
collapse- на маленьких экранах будет схлопываться-->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">На главную</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Сообщения</a>
            </li>
             <#if user?? && known><#--Просмотр метрик доступен только авторизованному пользователю-->
            <li class="nav-item">
                <a class="nav-link" href="/agent">Метрики</a>
            </li>
             </#if>
            <#if isAdmin><#--Список пользователей доступен только Админу-->
            <li class="nav-item">
                <a class="nav-link" href="/user">Пользователи</a>
            </li>
            </#if>
             <#if user?? && known><#--Редактирование профиля доступно только авторизованному пользователю-->
            <li class="nav-item">
                <a class="nav-link" href="/user/profile">Профиль</a>
            </li>
             </#if>

        </ul>
    </div>
    <div class="navbar-text mr-3">${name}</div>
    <@login.logout/>
</nav>