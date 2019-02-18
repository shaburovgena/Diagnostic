<#include "/parts/security.ftl">
<#import "login.ftl" as login>

<#--Панель навигации для lg (large) экранов в светлом стиле
на маленьких экранах будет показан только этот элемент-->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
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

             <#if user?? && known><#--Просмотр метрик доступен только авторизованному пользователю-->
            <li class="nav-item">
                <a class="nav-link" href="/group">Группы</a>
            </li>
              <li class="nav-item">
                  <a class="nav-link" href="/group/user-groups/${currentUserId}">Мои группы</a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="/agent">Сенсоры</a>
              </li>
             </#if>

            <#if isAdmin><#--Список пользователей доступен только Админу-->
            <li class="nav-item">
                <a class="nav-link" href="/user">Пользователи</a>
            </li>
            </#if>


        </ul>
    </div>
    <#if user?? && known><#--Редактирование профиля доступно только авторизованному пользователю-->
            <li class="nav-item">
                <a class="nav-link" style="color: aliceblue" href="/user/profile">${name}</a>
            </li>
    </#if>

    <@login.logout/>
</nav>