<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as pager>

<@common.page>

<h5>Это страница сканирования</h5>

<form action="/group/${group.id}/scan" method="post">
    <div class="form-group">
        <input type="text" class="form-control"
               name="ipAddress"
               placeholder="Введите адрес или сеть"/>
    </div>

    <div class="form-group">
        <input type="text" class="form-control"
               name="port"
               placeholder="Введите порт"/>
    </div>

    <div class="form-group">
    <#--Большая красивая кнопка-->
        <button type="submit" class="btn btn-primary mt-3">Сканировать</button>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
    <#if page??>

        <#list page.content as metric>
        <div class="my-5">
        <#--br епревод на новую строку-->
            <span>${metric.title}</span>
            <span>${metric.ipAddress}</span>
        </div>
        </#list>
        <@pager.pager page url/>
    <#else>
        <#list metrics as metric>
            <div class="my-5">
            <#--br епревод на новую строку-->
                <span>${metric.title}</span>
                <span>${metric.ipAddress}</span>
            </div>
        </#list>
    </#if>




</@common.page>