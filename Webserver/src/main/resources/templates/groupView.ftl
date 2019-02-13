<#import "parts/common.ftl" as common>
<#import "metrics.ftl" as metrics>
<#include "parts/security.ftl">
<#import "parts/pager.ftl" as pager>

<@common.page>

<div class="container-fluid">
    <div class="row">
    <#--Вся ширина делится на col-xs-12 и под элемент отводится col-sm-4 -->
        <div class="col-xs-12 col-sm-4" align="left">
            <a href="/group">
                <button class="btn btn-warning">Назад</button>
            </a>
        </div>
        <div class="col-xs-12 col-sm-4" align="center">
    <#if isAdmin||group.getOwner().getId()==currentUserId>
        <div class="btn-group" data-toggle="buttons">
            <label class="btn btn-outline-primary">
                <input type="radio" name="attribute" value="PRIVATE" id="PRIVATE" autocomplete="off" checked>PRIVATE
            </label>
            <label class="btn btn-outline-primary">
                <input type="radio" name="attribute" value="PUBLIC" id="PUBLIC" autocomplete="off">PUBLIC
            </label>
        </div>
    </#if>
        </div>
        <div class="col-xs-12 col-sm-4" align="right">
    <#if isAdmin||group.getOwner().getId()==currentUserId>
        <form method="post" action="/group/delete/${group.id}">
            <input type="hidden" name="id" value="${group.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-danger" align="left">Удалить</button>
        </form>
    </#if>
        </div>
    </div>

</div>
    <div><h5 align="center">${group.groupName!"Безымянный"}</h5>
    </div>
<form action="/agent" method="post">
    <div class="form-group">
        <input type="text" class="form-control"
               name="time"
               placeholder="Введите время"/>

    </div>
    <div class="form-group">
        <input type="text" class="form-control"
               name="title"
               placeholder="Введите имя сенсора"/>

    </div>
    <div class="form-group">

        <input type="text" class="form-control"
               name="value" placeholder="Значение"/>
        <input type="hidden" name="id" value="${group.id}"/>
    </div>
    <div class="form-group">
    <#--Большая красивая кнопка-->
        <button type="submit" class="btn btn-primary mt-3">Добавить</button>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
    <@pager.pager page url/>
    <#list page.content as metric>
    <div class="my-5">
    <#--br епревод на новую строку-->
        <span>${metric.time}</span><br/>
        <span>${metric.title}</span>
        <span>${metric.value}</span>
    </div>
    </#list>
    <@pager.pager page url/>
</@common.page>