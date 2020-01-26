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
<style>
  .card_footer > .card_content_subcontent {
    display: flex;
    aligh-items: center;
  }
  
  .product_card img {
  	max-height: 150px;
  	margin-left: 10px;
  }
  
  .product_card img:hover {
  	max-height: 250px;
  	margin-left: 10px;
  	transition-duration: 500ms;
  	transition-property: max-height;
  }
  
  .product_card .images_block {
  	display: flex;
  	justify-content: center;
  	align-items: center;
  }
  
</style>
</head>
<body>
  <!-- wrapper -->
  <div class="wrapper">
    <#include "header-menu-component.ftl"/>
    
    <div class="uui-main-container">
    
      <!-- main -->
      <main>        
        <!-- content -->
        <div class="content">
          <!-- card -->
          <@spring.url var="addProductToCartCommandLink" value="/addProductToCart"/>
          <@form.form action="${addProductToCartCommandLink}" method="post" class="card product_card">
            <!-- card_header -->
            <div class="card_header">
              <p class="card_title">${product.name}</p>
            </div>
            <!-- end of: card_header -->
            
            <!-- card_content -->
            <div class="card_content">
              <@security.authorize access="isAnonymous()">
                <p class="myui_message-tip mt10">
                  <@spring.url var="signinPageLink" value="/signin"/>
                  You mast be authorized to buy this product. Please follow this <a href="${signinPageLink}">link to sighin</a>.
                </p>
              </@security.authorize>
              <#if RequestParameters.error??>
                <p class="myui_message-error">Opperation failed. Try again with correct data.</p>
                <p class="myui_message-tip mt10">Tip: Count can`t be lower or equals to zero.</p>
                <#elseif RequestParameters.success??>
                <p class="myui_message-success">Operation success.</p>
              </#if>
              <#if product.images??>
                <#list product.images>
                  	<div class="images_block">
	                  <#items as image>
	                    <@spring.url var="getImageCommandLink" value="/images/getimage?imageId=${image}"/>
	                    <img src="${getImageCommandLink}">                    
	                  </#items>
                  	</div>
                </#list>
              </#if>
              <div class="card_content_subcontent">
                Info: ${product.info}
              </div>
              <div class="card_content_subcontent">
                Cost: ${product.cost}
              </div>
            </div>
            <!-- end of: card_content -->
            
            <!-- card_footer -->
            <div class="card_footer">
              <@security.authorize access="isAuthenticated()">
                <div class="card_content_subcontent">
                  <input type="hidden" name="productId" value="${product.id}"/>
                  <input type="text" name="count" pattern="[0-9]{, 3}" class="uui-form-element">
                  <input type="submit" value="Put to cart" class="uui-button ml5">
                </div>
              </@security.authorize>
            </div>
            <!-- end of: card_footer -->
          </@form.form>
          <!-- end of: card -->
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
