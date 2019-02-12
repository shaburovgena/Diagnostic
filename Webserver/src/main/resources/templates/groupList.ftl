<#import "parts/common.ftl" as common>
<#include "/parts/security.ftl">

<@common.page>

 <div class="form-row">
     <div class="form-group col-md-5">
         <form method="get" action="/group" class="form-inline">
             <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск">
             <button type="submit" class="btn btn-primary ml-3">Найти</button>
         </form>
     </div>
 </div>

<#include "parts/groupEdit.ftl"/>

<div class="card-columns"><#--Расположение карт в столбики-->
    <#list groups as group>

        <#if group.isPublic()||isAdmin||group.getOwner().getId()==currentUserId>
        <div class="card my-3"><#--Отображение сообщений в виде карточек, отступ со всех сторон 3-->
            <a href="/group/${group.id}"> <#if group.filename??>
                <img src="/img/${group.filename}" class="card-img-top"><#--Изображение в топе карточки-->
            <#else><img src="/static/folder.png" class="img-fluid"></#if></a>
            <div class="m-2">
                <span>${group.groupName!"Безымянный"}</span><br/>
                <i>#${group.groupTag!"Без тега"}</i>
            </div>
        </div>
        </#if>

    <#else><#--Если в коллекции нет элементов отображать Нет групп-->
        Нет групп
    </#list>
</div>
</@common.page>