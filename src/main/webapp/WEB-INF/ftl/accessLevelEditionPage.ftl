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
<title>Pharma.no</title>

<#include "styles-component.ftl"/>
</head>
<body>
  <#include "header-component.ftl"/>

  <main>
    <#include "menu-component.ftl"/>

    <!-- content -->
    <div class="content">
    <div class="searchBar">
      <@spring.url var="admin_accessLevelEditionPage" value="/admin/accessLevelEditionPage"/>
      <a href="${admin_accessLevelEditionPage}">Show all users</a>
      <a href="${admin_accessLevelEditionPage}?show=user">Show only users</a>
      <a href="${admin_accessLevelEditionPage}?show=admin">Show only admins</a>
      <a href="${admin_accessLevelEditionPage}?show=employee">Show only employee</a>
      <hr>
    </div>
    
    <#if users??>
    <#list users>
    <#items as user>
    User number: ${user.id}<br>
    Username: ${user.username}<br>
    Name: ${user.name}<br>
    Surname: ${user.surname}<br>
    Patronymic: ${user.patronymic}<br>
    Role: ${user.role}<br>
    Status: ${user.status}<br>
    <a href="#">Delete user</a>
    <a href="#">Restore user</a><br>
    Set as: <a href="#">user</a> <a href="#">admin</a> <a href="#">emplyee</a>
    <#sep><hr></#sep>
    </#items>
    </#list>
    </#if>
    
    <!-- end of: content -->
    </div>
    <hr>
    </main>

  <#include "footer-component.ftl"/>
</body>
</html>