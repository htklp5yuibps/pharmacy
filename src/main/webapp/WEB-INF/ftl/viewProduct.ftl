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
    
    <#if product??>
    Name: ${product.name}
    Info: ${product.info}
    Cost: ${product.cost}
    </#if>
    
    <!-- end of: content -->
    </div>
    <hr>
    </main>

  <#include "footer-component.ftl"/>
</body>
</html>