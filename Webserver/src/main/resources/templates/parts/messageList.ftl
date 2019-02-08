<#include "security.ftl">

<div class="card-columns"><#--Расположение карт в столбики-->
    <#list messages as message><#--Цикл для перебора сообщений-->
        <div class="card my-3"><#--Отображение сообщений в виде карточек, отступ со всех сторон 3-->
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top"><#--Изображение в топе карточки-->
            </#if>
            <div class="m-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-messages/${message.author.id}">${message.authorName}</a>
                <#if message.author.id == currentUserId>
                    <a class="btn btn-info" href="/user-messages/${message.author.id}?message=${message.id}">
                        Изменить
                    </a>
                </#if>
            </div>
        </div>
    <#else><#--Если в коллекции нет элементов отображать No messages-->
        No message
    </#list>
</div>