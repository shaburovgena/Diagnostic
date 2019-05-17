<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as pager>

<@common.page>

<h5>Scanner page</h5>

<form action="/group/${group.id}/scan" method="post">
    <div class="form-group">
        <input type="text" class="form-control"
               name="ipAddress"
               placeholder="Enter an address or network"/>
    </div>

    <div class="form-group">
        <input type="text" class="form-control"
               name="port"
               placeholder="Enter port"/>
    </div>

    <div class="form-group">
    <#--Большая красивая кнопка-->
        <button type="submit" class="btn btn-primary mt-3">Start scan</button>
    </div>

    <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
</form>
<#--<#if page??>-->

<#--<#list page.content as sensor>-->
<#--<div class="my-5">-->
<#--&lt;#&ndash;br епревод на новую строку&ndash;&gt;-->
<#--<span>${sensor.title}</span>-->
<#--<span>${sensor.ipAddress}</span>-->
<#--</div>-->
<#--</#list>-->
<#--<@pager.pager page url/>-->
<#--<#else>-->
    <#if sensors1??>
        <#list sensors1 as sensor>
            <div class="my-5">
            <#--br перевод на новую строку-->
                <form action="/group/${group.id}/sensor" method="post">
                        <input type="hidden" name="title" value="${sensor.title}">
                    <input type="hidden" name="group" value="${group}">
                    <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                        <button type="submit" class=<#if sensor.isSelected()>"btn btn-danger mt-3"<#else>"btn
                    btn-primary mt-3"></#if><span>${sensor.title}</span></button>
                </form>
            </div>
        </#list>
    <#else>
            No sensors
    </#if>




</@common.page>