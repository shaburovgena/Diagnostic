<#import "parts/common.ftl" as common>

<@common.page>
<h5>Это страница с результатами</h5>
    <#list metrics as metric>
    <div class="my-5">
        <span>${metric.time}</span><br/>
        <span>${metric.title}</span>
        <span>${metric.value}</span>
    </div>
    </#list>

</@common.page>