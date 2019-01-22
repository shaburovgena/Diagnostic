<#import "parts/common.ftl" as common>
<#include "/parts/security.ftl">
<#--<#import "parts/login.ftl" as login>-->

<@common.page>
    <div class="form-row">
        <div class="form-group col-md-5">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск">
                <button type="submit" class="btn btn-primary ml-3">Найти</button>
            </form>
        </div>
    </div>
<#--Отображаем кнопку на скрываемую группу со ссылкой на href="#collapseExample"-->
 <a class="btn btn-primary ml-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
    aria-controls="collapseExample">
     Добавить сообщение
 </a>
<#--Скрываемая группа откликается по id="collapseExample"-->
        <div class="collapse <#if message??>show</#if>" id="collapseExample">
            <div class="form-group ml-2 mt-3"> <#--Отступ для всей группы на 2 вправо и на 3 сверху-->
                <form method="post" enctype="multipart/form-data">
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
                    <div class="form-group">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile"> <#--Поле для загрузки файла-->
                            <label class="custom-file-label" for="customFile">Выберите файл</label> <#--Часть поля
                            с отдельным лейблом-->
                        </div>
                    </div>
                    <div class="form-group">
                    <#--Большая красивая кнопка-->
                        <button type="submit" class="btn btn-primary mt-3">Добавить</button>
                    </div>
                <#--Скрытое поле с информацией о токене сессии пользователя
                позволяет избежать некоторых видов атак-->
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
 <div class="card-columns"><#--Расположение карт в столбики-->
    <#list messages as message><#--Цикл для перебора сообщений-->
        <div class="card my-3"><#--Отображение сообщений в виде карточек, отступ со всех сторон 3-->
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top"><#--Изображение в топе карточки-->
            </#if>
        <#--<b>${message.id}</b>-->
            <div class="my-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div>
                <a href="/message/${message.id}">Просмотр</a>
            </div>
            <footer class="blockquote-footer">Author is <cite title="${message.authorName}">${message.authorName}</cite>
            </footer>
        </div>

    <#else><#--Если в коллекциине элементов отображать No messages-->
    No messages
    </#list>
 </div>
</@common.page>