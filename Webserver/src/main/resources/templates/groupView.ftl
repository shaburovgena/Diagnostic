<#import "parts/common.ftl" as common>
<#import "metrics.ftl" as metrics>
<#include "/parts/security.ftl">
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
            <form method="post" action="/group/delete/${group.id}">
                <input type="hidden" name="id" value="${group.id}"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-danger" align="left">Удалить</button>
            </form>
        </div>
    </div>

</div>
    <h5 align="center">${group.groupName!"Безымянный"}</h5>
</@common.page>