<#include "/parts/security.ftl">
<#import "parts/common.ftl" as common>
<@common.page>
<form action="/message" method="post">
    <div>
    <input type="text" name="text" value="${message.text}">
    </div>
    <div>
    <input type="text" name="tag" value="${message.tag}">
    </div>
    <div>
        <button  class="btn btn-primary" type="submit" role="button">Сохранить</button>
        <input type="hidden" name="id" value="${message.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <a class="btn btn-primary" role="button" href="/message/${message.id}">Назад</a>
    </div>
</form>
</@common.page>