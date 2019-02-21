<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as pager>

<@common.page>

<h5>Это страница с сенсорами</h5>
    <#list metrics as metric>
    <div class="my-5">
    <#--br епревод на новую строку-->
        <span>${metric.ipAddress}</span><br/>
        <#--<span>${metric.title}</span>-->
    </div>
    </#list>
</@common.page>