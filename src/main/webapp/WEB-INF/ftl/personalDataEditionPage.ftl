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
</head>
<body>
  <#include "header-component.ftl"/>

  <main>
    <#include "menu-component.ftl"/>

    <!-- content -->
    <div class="content">
    
    <#if user??>
    <@spring.url var="user_updatePersonalDataCommandLink" value="/user/updatePersonalData"/>
    <@form.form action="${user_updatePersonalDataCommandLink}" method="post">
      Name: <input type="text" value="${user.name}" name="name"><br>
      Surname: <input type="text" value="${user.surname}" name="surname"><br>
      Patronymic: <input type="text" value="${user.patronymic}" name="patronymic"><br>
      <input type="submit" value="Update data"/>
    </@form.form>
    </#if>
    
    </div>
    <!-- end of: content -->
    <hr>
    </main>

  <#include "footer-component.ftl"/>
</body>
</html>