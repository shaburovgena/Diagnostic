<#--Отображаем кнопку на скрываемую группу со ссылкой на href="#collapseExample"-->
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Сообщение
</a>
<#--Скрываемая группа откликается по id="collapseExample"-->
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group mt-3"><#--Отступ для всей группы на 2 вправо и на 3 сверху-->
        <form method="post" enctype="multipart/form-data">
            <div class="form-group"><#--Поле текста сообщения-->
                    <#--Если textError существует, привести к строке и пометить как ivalid-->
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите сообщение" />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"<#--Поле тега сообщения-->
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
            <#--Скрытое поле с информацией о токене сессии пользователя
                позволяет избежать некоторых видов атак-->
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
            <div class="form-group">
            <#--Большая красивая кнопка-->
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </form>
    </div>
</div>