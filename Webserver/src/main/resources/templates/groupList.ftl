<#import "parts/common.ftl" as common>
<#include "/parts/security.ftl">

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
        <div class="btn-group" data-toggle="buttons">
            <label class="btn btn-outline-primary">
                <input type="radio" name="attribute" value="PRIVATE" id="PRIVATE" autocomplete="off" checked>PRIVATE
            </label>
            <label class="btn btn-outline-primary">
                <input type="radio" name="attribute" value="PUBLIC" id="PUBLIC" autocomplete="off">PUBLIC
            </label>
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


<div class="container" align="center">
    <#list groups as group>
<#if group.isPublic()||isAdmin||group.getOwner().getId()==currentUserId>
        <a href="/group/${group.id}"><img src="/static/folder.png" class="img-fluid"></a>
        <div class="m-2">
            <span>${group.groupName!"Безымянный"}</span><br/>
            <i>#${group.groupTag!"Без тега"}</i>
        </div>
</#if>
    <#else>
        Нет групп
    </#list>
</div>
</@common.page>