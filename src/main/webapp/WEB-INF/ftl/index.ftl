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
    
    
    <div class="content">
    
    <div>
      <hr>
      <@spring.url var="indexPageLink" value="/index"/>
      <@form.form action="${indexPageLink}" method="post">
        <input type="text" name="part" placeholder="Product name part..."/>
        <input type="submit" value="Find..."/>
      </@form.form>
      <hr>
    </div>
    
    <#if products??>
      <#list products>
        <#items as product>
        <@spring.url var="product_viewProduct" value="/product/viewProductPage?productId=${product.id}"/>
        <a href="${product_viewProduct}">${product.name}</a>
        Name: ${product.name}<br>
        Info: ${product.info}<br>
        Cost: ${product.cost}<br>
        Status: ${product.status}<br>
        <#sep><hr></#sep>
        </#items>
      </#list>
    </#if>
    </div>
    <hr>
    </main>

  <#include "footer-component.ftl"/>
</body>
</html>