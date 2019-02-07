<#import "parts/common.ftl" as common>
<@common.page>
<form action="/message" method="post">
    <div class="form-group"><#--Поле текста сообщения-->
    <#--Если textError существует, привести к строке и пометить как ivalid-->
        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
               value="<#if message??>${message.text}</#if>" name="text"
               placeholder="Введите сообщение"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
    </div>
    <div class="form-group"><#--Поле тега сообщения-->

        <input type="text" class="form-control ${(tagError??)?string('is-invalid', '')}"
               value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Тэг">
                         <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                         </#if>
    </div>


    <div>
        <button class="btn btn-primary" type="submit" role="button">Сохранить</button>
        <input type="hidden" name="id" value="${message.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <a class="btn btn-primary" role="button" href="/message/${message.id}">Назад</a>
    </div>
</form>
</@common.page>