<#import "parts/common.ftl" as common>
<#include "/parts/security.ftl">
<#--<#import "parts/login.ftl" as login>-->

<@common.page>
   <h5>Список групп</h5>
<div class="form-group mt-3">
    <form method="post" action="/group">
        <div class="form-group">
            <input type="text" class="form-control ${(groupNameError??)?string('is-invalid', '')}"
                   <#--value="<#if group??>${group.groupName}</#if>"-->
                   name="groupName" placeholder="Имя группы"/>
<#if groupNameError??>
                    <div class="invalid-feedback">
                        ${groupNameError}
                    </div>
</#if>
        </div>
        <div class="form-group">
            <input type="text" class="form-control ${(groupTagError??)?string('is-invalid', '')}"
                   <#--value="<#if group??>${group.groupTa}</#if>"-->
                   name="groupTag" placeholder="Тег группы"/>
<#if groupTagError??>
                    <div class="invalid-feedback">
                        ${groupTagError}
                    </div>
</#if>
        </div>

    <#--Скрытое поле с информацией о токене сессии пользователя
        позволяет избежать некоторых видов атак-->
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group">
        <#--Большая красивая кнопка-->
            <button type="submit" class="btn btn-primary">Создать</button>
        </div>
    </form>
</div>


<div>
    <#list groups as group>
        <img src="/static/folder.png" class="img-fluid" href="/group">
        <div class="m-2">
            <span>${group.groupName!"Безымянный"}</span><br/>
            <i>#${group.groupTag!"Без тега"}</i>
        </div>
    <#else>
        Нет групп
    </#list>
</div>
</@common.page>