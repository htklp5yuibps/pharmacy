<#ftl encoding="UTF-8"/>
<!doctype html>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Pharma.no: personal page</title>

<#include "styles-component.ftl"/>

<style>
  .caution {
    color: green;
    font-weight: bold;
    font-size: 130%;
    text-decoration: underline;
  }
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
      <p class="error">Some error was corrupt...</p>
      <#else>
      <p class="caution">In order of success, you will be redirected to login page for resigning</p>
    </#if>
    
    <@spring.url var="user_updatePasswordCommandLink" value="/user/updatePassword"/>
    <@form.form action="${user_updatePasswordCommandLink}" method="post">
      Old password: <input type="password" name="oldPass"><br>
      New password: <input type="password" name="newPass"><br>
      <input type="submit" value="Change password"/>
    </@form.form>
    
    </div>
    <!-- end of: content -->
    <hr>
    </main>

  <#include "footer-component.ftl"/>
</body>
</html>