<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as pager>

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
    <@pager.pager page url/>
    <#list page.content as sensor>
    <div class="my-5">
    <#--br епревод на новую строку-->
        <span>${sensor.time}</span><br/>
        <span>${sensor.title}</span>
        <span>${sensor.value}</span>
    </div>
    </#list>
    <@pager.pager page url/>
</@common.page>