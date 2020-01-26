<#ftl encoding="UTF-8" />
<#assign spring=JspTaglibs[ "http://www.springframework.org/tags"] />
<#assign form=JspTaglibs[ "http://www.springframework.org/tags/form"] />

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Pharma.no: signin</title>
<#include "styles-component.ftl"/>


<style>

body {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #2e5885;
}

.signup_form {
  min-width: 400px;
}

</style>
</head>

<body>
    <!-- content -->
    <div class="content">
      <@spring.url var="signupCommandLink" value="/registration/signup"/>
      <@form.form action="${signupCommandLink}" method="post" class="card signup_form">
        <!-- card_header -->
        <div class="card_header">
          <p class="card_title">REGISTRATION FORM</p>
        </div>
        <!-- end of: card_header -->
        
        <!-- card_content -->
        <div class="card_content">
          <div class="card_content_subcontent">
           <div class="myui_message-tip">
            In order of successfull registration, you will be redirected to signin form.
           </div>
          </div>
          <#if RequestParameters.error??>
            <div class="card_content_subcontent">
             <div class="myui_message-error">
              Registration was interrupted by error. Please try again.
             </div>
            </div>
            <div class="card_content_subcontent">
             <div class="myui_message-tip">
              `Username` must be your email adress.<br>
              Password mast be at least 10 symbols, but not biger then 30.
             </div>
            </div>
            <#elseif RequestParameters.exists??>
            <div class="card_content_subcontent">
             <div class="myui_message-error">
              User with such username is already registered.<br>
              Please, try again with another email adress.
             </div>
            </div>
          </#if>
          <div class="card_content_subcontent df">
            <input type="text" name="username" placeholder="Username..." class="uui-form-element w100"/>
          </div>
          <div class="card_content_subcontent df">
            <input type="password" name="password" placeholder="Password..." class="uui-form-element w100"/>
          </div>
          <div class="card_content_subcontent df">
            <input type="text" name="name" placeholder="Name..." class="uui-form-element w100"/>
          </div>
          <div class="card_content_subcontent df">
            <input type="text" name="surname" placeholder="Surname..." class="uui-form-element w100"/>
          </div>
          <div class="card_content_subcontent df">
            <input type="text" name="patronymic" placeholder="Patronymic..." class="uui-form-element w100"/>
          </div>
        </div>
        <!-- end of: card_content -->
        
        <!-- card_footer -->
        <div class="card_footer">
          <div class="card_content_subcontent">
            <input type="submit" value="Signup" class="uui-button"/>
          </div>
          <div class="card_content_subcontent">
            <@spring.url var="signinPageLink" value="/signin"/>
            <a href="${signinPageLink}" class="uui-button">Signin page</a>
            <@spring.url var="indexPageLink" value="/index"/>
            <a href="${indexPageLink}" class="uui-button">Go to main page</a>
          </div>
        </div>
        <!-- end of: card_footer -->
      </@form.form>
    </div>
    <!-- end of: content -->

  <#include "js-component.ftl"/>
</body>
</html>