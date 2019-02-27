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
<#--<#if page??>-->

<#--<#list page.content as metric>-->
<#--<div class="my-5">-->
<#--&lt;#&ndash;br епревод на новую строку&ndash;&gt;-->
<#--<span>${metric.title}</span>-->
<#--<span>${metric.ipAddress}</span>-->
<#--</div>-->
<#--</#list>-->
<#--<@pager.pager page url/>-->
<#--<#else>-->
    <#if metrics??>
        <#list metrics as metric>
            <div class="my-5">
            <#--br епревод на новую строку-->
                <form action="/group/${group.id}/metric" method="post">
                        <input type="hidden" name="title" value="${metric.title}">
                    <input type="hidden" name="group" value="${group}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class=<#if metric.isSelected()>"btn btn-danger mt-3"<#else>"btn
                    btn-primary mt-3"></#if><span>${metric.title}</span></button>
                </form>
            </div>
        </#list>
    <#else>
            No metrics
    </#if>




</@common.page>