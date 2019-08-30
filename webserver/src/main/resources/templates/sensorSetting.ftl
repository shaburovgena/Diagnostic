<#import "parts/common.ftl" as common>
<#import "metricsSetting.ftl" as metric>
<#include "parts/security.ftl">
<#assign
isAddingMetric = false
>

<@common.page>

    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-4" align="left">
                <a href="/group/${group.id}">
                    <button class="btn btn-warning">Back</button>
                </a>
            </div>
            <div class="col-xs-12 col-sm-4" align="center">

                <h5>${sensor.title}</h5>

            </div>
            <div class="col-xs-12 col-sm-4" align="right">

                <form method="post" action="/group/${group.id}/delete/${sensor.id}">
                    <input type="hidden" name="id" value="${sensor.id}"/>
                    <button type="submit" class="btn btn-danger"
                            align="left"
                        <#if isAdmin||group.getOwner().getId()==currentUserId><#else>disabled="disabled"
                        </#if>>Delete
                    </button>
                </form>

            </div>
        </div>
    </div>

  <form action="/group/${group.id}/save/${sensor.id}" method="post">
      <div class="conteainer mt-3">
          <div class="form-group row">
              <label class="col-sm-3 col-form-label">Title:</label>
              <div class="col-sm-9 ">
                  <input class="form-control" type="text" name="title" value="${sensor.title}"/>
              </div>
          </div>
          <div class="form-group row">
              <label class="col-sm-3 col-form-label">IP Address:</label>
              <div class="col-sm-9 ">
                  <input class="form-control" type="text" name="ipAddress" value="${sensor.ipAddress}"/>
              </div>
          </div>
          <div class="form-group row">
              <label class="col-sm-3 col-form-label">Credentials</label>
              <div class="col-sm-9 ">
                  <progress class="progress progress-striped progress-animated mt-2" value="100" max="100"></progress>
              </div>
          </div>
          <div class="form-group row">
              <label class="col-sm-3 col-form-label">Login:</label>
              <div class="col-sm-9 ">
                  <input class="form-control" type="text" name="loginCredentials"
                         value="${sensor.loginCredentials!""}"/>
              </div>
          </div>
          <div class="form-group row">
              <label class="col-sm-3 col-form-label">Title:</label>
              <div class="col-sm-9 ">
                  <input class="form-control" type="password" name="passwordCredentials"
                         value="${sensor.passwordCredentials!""}"/>
              </div>
          </div>
      </div>


      <div>
          <div class="conteainer mt-3">
              <div class="row">
                  <div class="col-xs-12 col-sm-6" align="left">

                      <button class="btn btn-success" type="button" onclick="invokeJsCode()"
                            <#if isAdmin||group.getOwner().getId()==currentUserId><#else>disabled="disabled"
                            </#if>>Add metric
                      </button>
                  </div>
                  <div class="col-xs-12 col-sm-6" align="right">
                      <button class="btn btn-primary"
                              type="submit"
                          <#if isAdmin||group.getOwner().getId()==currentUserId><#else>disabled="disabled"
                          </#if>>Save
                      </button>
                  </div>

              </div>
          </div>
          <div>

          </div>
<#if isAddingMetric>
    <@metric.metricsSetting></@metric.metricsSetting>
</#if>
      </div>
  </form>

<script>

    function invokeJsCode() {
        console.log("Add metric pressed ")
    }
</script>


</@common.page>

