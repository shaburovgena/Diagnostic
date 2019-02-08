<#import "parts/common.ftl" as common>
<#import "metrics.ftl" as metrics>
<#include "/parts/security.ftl">
<@common.page>

<div class="container">
    <div class="row flex-items-xs-left">
        <div class="col-xs-5 col-sm-6" align="left">
        <a href="/group">
            <button class="btn btn-warning" >Назад</button>
        </a>
        </div>
        <div class="col-xs-12 col-sm-6" align="right">
        <form method="post" action="/group/delete/${group.id}">
            <input type="hidden" name="id" value="${group.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-danger" align="left">Удалить</button>
        </form>
        </div>
    </div>
    <div class="container">
        <@metrics.metricsList/>
    </div>
</div>
    <h5 align="center">${group.groupName!"Безымянный"}</h5>
</@common.page>