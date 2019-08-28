<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">
<@common.page>
<form action="/group/${group.id}/save/${sensor.id}" method="post">
    <div class="container-fluid">
        <div class="row">
        <#--Вся ширина делится на col-xs-12 и под элемент отводится col-sm-4 -->
            <div class="col-xs-12 col-sm-4" align="left">
                <a href="/group/${group.id}">
                    <button class="btn btn-warning">Back</button>
                </a>
            </div>
            <div class="col-xs-12 col-sm-4" align="center">
    <#if isAdmin||group.getOwner().getId()==currentUserId>
        <h5>${sensor.title}</h5>
    </#if>
            </div>
            <div class="col-xs-12 col-sm-4" align="right">
    <#if isAdmin||group.getOwner().getId()==currentUserId>
        <form method="post" action="/group/${group.id}/delete/${sensor.id}">
            <input type="hidden" name="id" value="${sensor.id}"/>
        <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
            <button type="submit" class="btn btn-danger" align="left">Delete</button>
        </form>
    </#if>
            </div>
        </div>

    </div>

    <div>
        <dl class="row">
            <dt class="col-sm-3">Exec command</dt>
            <dd class="col-sm-9"><input type="text" name="execCommand" placeholder="Exec command"/></dd>

            <dt class="col-sm-3">SNMP MIB</dt>
            <dd class="col-sm-9"><input type="text" name="snmpMib" placeholder="MIB"/></dd>


            <dt class="col-sm-3">Delta</dt>
            <dd class="col-sm-9"><input type="checkbox" name="delta"/></dd>

            <dt class="col-sm-3 text-truncate">Multiplier</dt>
            <dd class="col-sm-9"><input type="number" name="multiplier" placeholder="Multiplier"/></dd>

            <dt class="col-sm-3 text-truncate">Divider</dt>
            <dd class="col-sm-9"><input type="number" name="divider" placeholder="Divider"/></dd>
            <dt class="col-sm-3">Credentials</dt>
            <dd class="col-sm-9">
                <progress class="progress progress-striped progress-danger mt-2" value="100" max="100"></progress>
            </dd>

            <dt class="col-sm-3">Login</dt>
            <dd class="col-sm-9"><input type="text" name="loginCredentials" placeholder="Login"/></dd>
            <dt class="col-sm-3">Password</dt>
            <dd class="col-sm-9"><input type="password" name="passwordCredentials" placeholder="Password"/></dd>


        </dl>
    </div>
    <div>
        <div class="row">
        <#--Вся ширина делится на col-xs-12 и под элемент отводится col-sm-4 -->
            <div class="col-xs-12 col-sm-4" align="left">

                    <button class="btn btn-primary" type="submit">Save</button>

            </div>
        </div>
    </div>
</form>
</@common.page>