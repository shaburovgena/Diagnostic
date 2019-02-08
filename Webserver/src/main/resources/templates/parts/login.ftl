<#include "/parts/security.ftl">
<#macro login path isRegisterForm>

<#--При вызове макроса ссылка передается в качестве параметра path-->
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="User name"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Password"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="passwordConfirm"
                       class="form-control ${(passwordConfirmError??)?string('is-invalid', '')}"
                       placeholder="Retype password"/>
                <#if passwordConfirmError??>
                    <div class="invalid-feedback">
                        ${passwordConfirmError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                       class="form-control ${(emailError??)?string('is-invalid', '')}"
                       placeholder="email@example.com"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
    <div>
    <#--Для отображения капчи на странице регистрации-->
        <div class="g-recaptcha" data-sitekey="6LdbSYsUAAAAALRUNrKROCjrd7uKTqeZ2v0OuVQH"></div>
        <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
        </#if>
    </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div class="form-group row">
        <div class="col-sm-2">
        <#--Если находимся в форме регистрации кнопка Sign In меняется на Create-->
            <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
        </div>
        <div>
        <#--Если не находимся на странице регистрации отображается ссылка Sign Up-->
    <#if !isRegisterForm><a href="/registration" class="ml-5 mt-2">Sign Up</a></#if>
        </div>
    </div>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-primary" type="submit"><#if user?? && known>Sign Out<#else>Log In</#if></button>
</form>
</#macro>