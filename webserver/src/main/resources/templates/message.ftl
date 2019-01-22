<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">
<@common.page>
<div class="card my-3"><#--Отображение сообщений в виде карточек, отступ со всех сторон 3-->
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top" ><#--Изображение в топе карточки-->
            </#if>
<#--<b>${message.id}</b>-->
    <div class="my-2">
        <span>${message.text}</span><br/>
        <i>#${message.tag}</i>
    </div>
    <div>
    <footer class="blockquote-footer">Author is <cite title="${message.authorName}">${message.authorName}</cite>
    </footer>
    </div>
    <div>
        <a class="btn btn-primary" role="button" href="/message/${message.id}/edit">Редактировать</a>
    </div>
</div>

</@common.page>