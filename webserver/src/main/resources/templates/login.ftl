<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
<div class="mb-1"> Login page</div>
<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="alert alert-danger" role="alert">
       ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
</#if>
    <@login.login "/login" false/>
</@common.page>
