<#import "parts/common.ftl" as common>
<#include "/parts/security.ftl">
<@common.page>
<h5>${username}</h5>
    ${message?ifExists}

<#--action="${path}" убрали так как ответ будет отправляться туда
же, откуда пришел запрос-->
<form  method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-4 ">
            <input class="form-control" type="password" name="password" placeholder="Password"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email:</label>
        <div class="col-sm-6">
            <#--value="${email!''}" если поле пустое, его значение будет заменено на пустую строку-->
            <input type="email" name="email" class="form-control" placeholder="email@example.com" value="${email!''}"/>
        </div>
    </div>
    <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
    <div class="form-group row">
        <div class="col-sm-2">
        <#--Если находимся в форме регистрации кнопка Sign In меняется на Create-->
            <button class="btn btn-primary" type="submit">Save</button>
        </div>

    </div>
</form>
</@common.page>