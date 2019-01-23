<#--Шаблон для сокрытия пунктов меню в зависимости от привелегий польз-->
<#assign
<#--Помещаем в переменную сессию из контекста spring если пользоавтель авторизован-->
known = Session.SPRING_SECURITY_CONTEXT??
<#--?? - приведение к булевому типу-->
>

<#if known>
    <#assign
    <#--Проверяем есть ли у пользователя права админа-->
    <#--Получаем пользователя из данных о сессии-->
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal

    <#--Выполняем методы Java для получения данных-->
    name = user.getUsername()
    isAdmin = user.isAdmin()
    currentUserId = user.getId()
    >
<#else>
    <#assign
    name = "Гость"
    isAdmin = false
    currentUserId = -1
    >
</#if>
