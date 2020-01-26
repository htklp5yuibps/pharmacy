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

<#include "../styles-component.ftl"/>
</head>
<body>
  <#include "../header-component.ftl"/>

  <main>
    <#include "../menu-component.ftl"/>
    
    <div class="content">
    	<#if order??>
    	Order number: ${order.id}<br>
    	Order status: ${order.status}<br>
    	Order date: ${order.orderDate?string["dd-MM-yyyy HH:mm:ss"]}<br>
    	<#if order.endDate??>
    	Order end date: ${order.endDate?string["dd-MM-yyyy HH:mm:ss"]}<br>
    	<#else>
    	Order end date: order not closed yet.<br>
    	</#if>
    	<#if order.status = "new" | order.status = "ready">
    	<@spring.url var="cancelOrderLink" value="/employee/order/cancelOrder"/>
    	<a href="${cancelOrderLink}?id=${order.id}">Cancel order</a><br>
    	</#if>
    	Order parts:
    	<hr>
    	<#list products>
    	<#items as product>
    	<a href="#">${product.getKey().name} - ${product.getValue()}</a><br>
    	</#items>
    	</#list>
    	</#if>
    </div>
    
    <hr>
    </main>

  <#include "../footer-component.ftl"/>
</body>
</html>