<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<@common.page>
<div class="mb-3"> Add new user </div>
    ${message?ifExists}
    <@login.login "/registration" true/>
</@common.page>