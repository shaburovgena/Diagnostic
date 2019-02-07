<#import "parts/common.ftl" as common>
<#include "/parts/security.ftl">
<#--<#import "parts/login.ftl" as login>-->

<@common.page>
    <div class="form-row">
        <div class="form-group col-md-5">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск">
                <button type="submit" class="btn btn-primary ml-3">Найти</button>
            </form>
        </div>
    </div>

    <#include "parts/messageEdit.ftl" />

    <#include "parts/messageList.ftl" />
</@common.page>