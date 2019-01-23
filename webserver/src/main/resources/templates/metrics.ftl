<#import "parts/common.ftl" as common>

<@common.page>
<h5>Это страница с результатами</h5>

<form action="/agent" method="post">
    <div class="form-group">
        <input type="text" class="form-control"
               name="time"
               placeholder="Введите время"/>

    </div>
    <div class="form-group">
        <input type="text" class="form-control"
               name="title"
               placeholder="Введите имя сенсора"/>

    </div>
    <div class="form-group">

        <input type="text" class="form-control"
               name="value" placeholder="Значение"/>

    </div>
    <div class="form-group">
    <#--Большая красивая кнопка-->
        <button type="submit" class="btn btn-primary mt-3">Добавить</button>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
    <#list metrics as metric>
    <div class="my-5">
        <span>${metric.time}</span><br/>
        <span>${metric.title}</span>
        <span>${metric.value}</span>
    </div>
    </#list>

</@common.page>