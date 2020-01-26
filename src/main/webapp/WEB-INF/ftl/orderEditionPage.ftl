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
      <@spring.url var="employee_orderEditionPage" value="/employee/orderEditionPage"/>
      <a href="${employee_orderEditionPage}?show=new">Show only new</a>
      <a href="${employee_orderEditionPage}?show=ready">Show only ready</a>
      <a href="${employee_orderEditionPage}?show=closed">Show only closed</a>
      <a href="${employee_orderEditionPage}?show=canceled">Show only canceled</a>
      <hr>
    </div>
    
    <#if orders??>
      <#list orders>
        <#items as order>
        Order number: ${order.id}
        Order status: ${order.status}<br>
        Order date: ${order.orderDate?string["dd-MM-yyyy HH:mm:ss"]}<br>
        Order end-date: ${order.endDate!"Order is not closed yet."}<br>
        <a href="#">Order consist of: ${order.parts?size}</a><br>
        <a href="#">Cancel order</a>
        <a href="#">Close order</a>
        <a href="#">Make it ready</a>
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