<#include "/parts/security.ftl">
<#import "parts/common.ftl" as common>
<@common.page>
List of users
<#--Таблица-->
<table>
    <thead>
    <#--Стока-->
    <tr>
    <#--Наименование колонок-->
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
<tbody>
 <#list users as user>
 <tr>
     <td>${user.username}</td>
     <td><#list user.roles as role>${role}<#sep>, </#list></td>
     <td><a href="/user/${user.id}">edit</a></td>
 </tr>
 </#list>
</tbody>
</table>

</@common.page>