<#ftl encoding="UTF-8"/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<#include "styles-component.ftl"/>
</head>
<body>
  <!-- wrapper -->
  <div class="wrapper">
    <#include "header-menu-component.ftl"/>
    
    <div class="uui-main-container">
    
      <!-- main -->
      <main>
        <!-- search_bar -->
          <@spring.url var="indexPageLink" value="/index"/>
          <@form.form class="myui_search_bar" action="${indexPageLink}" method="post">
            <div class="myui_search_sub">
              <input type="text" name="part" placeholder="Product name part..." class="uui-form-element"/>
              <input type="submit" value="Find" class="uui-button ml5"/>            
            </div>
          </@form.form>
        <!-- end of: search_bar -->
        
        <!-- content -->
        <div class="content">
        
          <#if products??>
	          <#list products>
	            <#items as product>
	              <!-- card -->
	              <div class="card">
	                <!-- card_header -->
	                <div class="card_header">
	                  <@spring.url var="product_viewProduct" value="/product/viewProductPage?productId=${product.id}"/>
	                  <a href="${product_viewProduct}" class="card_title-link">${product.name}</a>
	                </div>
	                <!-- end of: card_header -->
	                
	                <!-- card_content -->
	                <div class="card_content">
	                  <div class="card_content_subcontent">
	                    ${product.info}
	                  </div>
	                </div>
	                <!-- end of: card_content -->
	                
	                <!-- card_footer -->
	                <div class="card_footer">
	                  <div class="card_content_subcontent">
	                    Cost: ${product.cost}
	                  </div>
	                </div>
	                <!-- end of: card_footer -->
	              </div>
	              <!-- end of: card -->
	            </#items>
	          </#list>
          </#if>
          
          
        </div>
        <!-- end of: content -->
        
      </main>
      <!-- end of: main -->

    </div>
    <#include "footer-component.ftl"/>
  </div>
  <!-- end of: wrapper -->
  <#include "js-component.ftl">
</body>
</html>
