<#import "parts/common.ftl" as common>
<#import "sensors.ftl" as sensors>
<#import "parts/pager.ftl" as pager>
<#include "parts/security.ftl">

<@common.page>

<div class="container-fluid">
    <div class="row">
    <#--Вся ширина делится на col-xs-12 и под элемент отводится col-sm-4 -->
        <div class="col-xs-12 col-sm-4" align="left">
            <a href="/group">
                <button class="btn btn-warning">Back</button>
            </a>
        </div>
        <div class="col-xs-12 col-sm-4" align="center">
    <#if isAdmin||group.getOwner().getId()==currentUserId>
        <div class="btn-group" data-toggle="buttons">
            <label class="btn btn-outline-primary">
                <input type="radio" name="attribute" value="PRIVATE" id="PRIVATE" autocomplete="off" checked>PRIVATE
            </label>
            <label class="btn btn-outline-primary">
                <input type="radio" name="attribute" value="PUBLIC" id="PUBLIC" autocomplete="off">PUBLIC
            </label>
        </div>
    </#if>
        </div>
        <div class="col-xs-12 col-sm-4" align="right">
            <form method="post" action="/group/${group.id}/delete">
                <input type="hidden" name="id" value="${group.id}"/>
            <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                <button type="submit" class="btn btn-danger"
                        align="left"<#if isAdmin||group.getOwner().getId()==currentUserId><#else>disabled="disabled"
                </#if>>Delete
                </button>
            </form>

        </div>
    </div>

</div>
    <div>
        <h5 align="center">${group.groupName!"Без имени"}</h5>
    </div>


<#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->

    <@pager.pager page url/>
    <#list page.content as sensor>
    <div class="my-5">
    <#--br перевод на новую строку-->
        <div class="btn-group" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-secondary"><span><#if sensor.value??>Value ${sensor.value}<#else> No data</#if></span>
            </button>
            <a href="/group/${group.id}/setting/${sensor.id}">
                <button type="button" class="btn btn-primary"><span>${sensor.title}</span></button>
            </a>

        </div>
    </div>
    </#list>
<div>


        <div class="form-group">
<#--Большая красивая кнопка-->
            <a href="/group/${group.id}/scan">
                <button type="submit"
                        class="btn btn-primary mt-3"    <#if isAdmin||group.getOwner().getId()==currentUserId><#else>disabled="disabled"
                </#if>
>Add
</button>
            </a>
        </div>


</div>
</@common.page>