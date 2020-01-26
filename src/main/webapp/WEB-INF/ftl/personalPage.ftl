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
    Username: ${user.username}<br>
    Name: ${user.name}<br>
    Surname: ${user.surname}<br>
    Patronymic: ${user.patronymic}<br>
    <@spring.url var="user_personalDataEditionPage" value="/user/personalDataEditionPage"/>
    <a href="${user_personalDataEditionPage}">Edit user personal data</a><br>
    <@spring.url var="user_passwordChangingPage" value="/user/passwordChangingPage"/>
    <a href="${user_passwordChangingPage}">Change password</a>
    <hr>
    </#if>
    
    Orders:<br>
    <#if orders??>
    <#list orders>
    <#items as order>
    <a href="#">Order number: ${order.id}</a><br>
    Order status: ${order.status}<br>
    Order date: ${order.orderDate?string["dd-MM-yyyy HH:mm:ss"]}<br>
    Order end-date: 
    <#if order.endDate??>
    ${order.endDate?string["dd-MM-yyyy HH:mm:ss"]}
    <#else>
    Order is not closed yet.
    </#if><br>
    Order contains: ${order.parts?size} parts<br>
    <#sep><hr></#sep>
    </#items>
    </#list>
    </#if>
    
    </div>
    <!-- end of: content -->
    <hr>
    </main>

  <#include "footer-component.ftl"/>
</body>
</html>