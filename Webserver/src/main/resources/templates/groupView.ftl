<#import "parts/common.ftl" as common>
<#import "sensors.ftl" as sensors>
<#include "parts/security.ftl">
<#import "parts/pager.ftl" as pager>

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
    <#if isAdmin||group.getOwner().getId()==currentUserId>
        <form method="post" action="/group/${group.id}/delete">
            <input type="hidden" name="id" value="${group.id}"/>
            <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
            <button type="submit" class="btn btn-danger" align="left">Delete</button>
        </form>
    </#if>
        </div>
    </div>

</div>
    <div>
        <h5 align="center">${group.groupName!"Без имени"}</h5>
    </div>


    <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
</form>
    <@pager.pager page url/>
    <#list page.content as sensor>
    <div class="my-5">
    <#--br перевод на новую строку-->
        <span>${sensor.title}</span>
        <span><#if sensor.value??> ${sensor.value}<#else> No data</#if></span>
    </div>
    </#list>
<div>

    <#if isAdmin||group.getOwner().getId()==currentUserId>
        <div class="form-group">
        <#--Большая красивая кнопка-->
            <a href="/group/${group.id}/scan">
                <button type="submit" class="btn btn-primary mt-3">Add</button>
            </a>
        </div>
    </#if>

</div>
</@common.page>