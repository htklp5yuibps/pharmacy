<#ftl encoding="UTF-8" />
<!doctype html>
<#assign spring=JspTaglibs[ "http://www.springframework.org/tags"] />
<#assign form=JspTaglibs[ "http://www.springframework.org/tags/form"] />
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Pharma.no: signin</title>
<#include "styles-component.ftl"/>

<style>
  .error {
    color: red;
    font-weight: bold;
    font-size: 130%;
    text-decoration: underline;
  }
</style>
</head>
<body>
  <#include "header-component.ftl"/>

  <main>
    <#include "menu-component.ftl"/>
  
    <!-- content -->
    <div class="content">
    <#if error??>
      <p class="error">Incorrect username/password.</p>
    </#if>
    <@spring.url var="signinCommandLink" value="/authenticateTheUser"/>
    <@form.form action="${signinCommandLink}" method="post">
      <input type="text" name="username" placeholder="Username..."/>
      <input type="password" name="password" placeholder="Password..."/>
      <input type="submit" value="SignIn"/>
    </@form.form>
    
    </div>
    <!-- end of: content -->
    <hr>
  </main>

  <#include "footer-component.ftl"/>
</body>
</html>