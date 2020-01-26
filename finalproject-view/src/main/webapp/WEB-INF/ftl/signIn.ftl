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

.signin_form {
  min-width: 400px;
}

</style>
</head>

<body>
    <!-- content -->
    <div class="content">
      <@spring.url var="signinCommandLink" value="/authenticateTheUser"/>
      <@form.form action="${signinCommandLink}" method="post" class="card signin_form">
        <!-- card_header -->
        <div class="card_header">
          <p class="card_title">Signin form</p>
        </div>
        <!-- end of: card_header -->
        
        <!-- card_content -->
        <div class="card_content">
          <#if RequestParameters.error??>
            <div class="card_content_subcontent">
             <div class="myui_message-error">
              Incorrect username or password. Please try again with correct data.
             </div>
            </div>
            <#elseif RequestParameters.registered??>
            <div class="card_content_subcontent">
             <div class="myui_message-success">
              Registration completed successfully. Please sign in to continue work with resource.<br>
              Thanks!
             </div>
            </div>
          </#if>
          <div class="card_content_subcontent df">
            <input type="text" name="username" placeholder="Username..." class="uui-form-element w100"/>
          </div>
          <div class="card_content_subcontent df">
          <input type="password" name="password" placeholder="Password..." class="uui-form-element w100"/>
          </div>
        </div>
        <!-- end of: card_content -->
        
        <!-- card_footer -->
        <div class="card_footer">
          <div class="card_content_subcontent">
            <input type="submit" value="SignIn" class="uui-button"/>
          </div>
          <div class="card_content_subcontent">
            <@spring.url var="registrationPageLink" value="/registration"/>
            <a href="${registrationPageLink}" class="uui-button">Signup page</a>
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