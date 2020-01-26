<#ftl encoding="UTF-8"/>
<#-- 
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
    <@spring.url var="productEditionPageLink" value="/admin/productEditionPage"/>
    <@form.form action="${productEditionPageLink}" method="get">
    <a href="${productEditionPageLink}">Show all</a>
    <a href="${productEditionPageLink}?status=available">Show available</a>
    <a href="${productEditionPageLink}?status=unavailable">Show unavailable</a><br>
    <input type="text" name="name" placeholder="Product name part..."/>
    <input type="submit"><br>
    <@spring.url var="addProductForm" value="/admin/product/add/form"/>
    <a href="${addProductForm}">Add new product...</a>
    <hr>
    <#if products??>
      <#list products>
        <#items as product>
        Name: ${product.name}<br>
        Info: ${product.info}<br>
        Cost: ${product.cost}<br>
        Status: ${product.status}<br>
        <@spring.url var="productEditorPageLink" value="/admin/product/editor"/>
        <a href="${productEditorPageLink}?productId=${product.id}">Edit</a><br>
        <#if product.status = "available">
        <@spring.url var="deleteProductCommandLink" value="/admin/product/delete"/>
        <a href="${deleteProductCommandLink}?productId=${product.id}">Delete product</a><br>
        <#elseif product.status = "unavailable">
        <@spring.url var="restoreProductCommandLink" value="/admin/product/restore"/>
        <a href="${restoreProductCommandLink}?productId=${product.id}">Restore</a><br>                
        </#if>
        <#sep><hr></#sep>
        </#items>
      </#list>
    </#if>
    </@form.form>
    </div>
    <hr>
    </main>

  <#include "footer-component.ftl"/>
</body> -->




<!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
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
  .product_card > .card_header {
    display: flex;
    justify-content: space-between;
  }
  
  .product_card > .card_footer {
    padding: 10px;
    display: grid;
    grid-template-columns: repeat(2, auto);
    grid-auto-rows: auto;
    grid-gap: 10px;
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
        <!-- search bar -->
        <@spring.url var="productEditionPageLink" value="/admin/productEditionPage"/>
        <@form.form class="myui_search_bar" action="${productEditionPageLink}" method="get">
          <div class="myui_search_sub">
            <div class="myui_search_sub">
              <input type="text" name="name" placeholder="Product name part..." class="uui-form-element"/>
              <input type="submit" value="Search" class="uui-button ml5"><br>
            </div>
          </div>
          <div class="myui_search_separator"></div>
          <div class="myui_search_sub">
            <a href="${productEditionPageLink}" class="uui-button"><span>Show all</span></a>
            <a href="${productEditionPageLink}?status=available" class="uui-button ml5">
              <span>Show available</span>
            </a>
            <a href="${productEditionPageLink}?status=unavailable" class="uui-button ml5">
              <span>Show unavailable</span>
            </a>
          </div>
          <div class="myui_search_separator"></div>
          <div class="myui_search_sub">
            <@spring.url var="addProductForm" value="/admin/product/add/form"/>
            <a href="${addProductForm}" class="uui-button">Add new product...</a>
          </div>
        </@form.form>
        <!-- end of: search bar -->
        
        <!-- content -->
        <div class="content">
          
          <!-- product card -->
          <#if products??>
            <#list products>
              <#items as product>
                <div class="card product_card">
                  <div class="card_header">
                    <p class="card_title">${product.name}</p>
                    <p class="card_title">Status: ${product.status}</p>
                  </div>
                  
                  <div class="card_content">
                    <div class="card_content_subcontent">
                      <p class="card_content_link">${product.info}</p>
                      <p class="card_content_link">${product.cost}</p>
                    </div>
                  </div>
                  
                  <div class="card_footer">
                    <@spring.url var="productEditorPageLink" value="/admin/product/editor"/>
                    <a href="${productEditorPageLink}?productId=${product.id}" class="uui-button">
                      <span>Edit</span>
                    </a>
                    <#if product.status = "available">
                      <@spring.url var="deleteProductCommandLink" value="/admin/product/delete"/>
                      <a href="${deleteProductCommandLink}?productId=${product.id}" class="uui-button">
                        <span>Delete product</span>
                      </a>
                      <#elseif product.status = "unavailable">
                      <@spring.url var="restoreProductCommandLink" value="/admin/product/restore"/>
                      <a href="${restoreProductCommandLink}?productId=${product.id}" class="uui-button">
                        <span>Restore</span>
                      </a>
                    </#if>
                  </div>
                </div>
              </#items>
            </#list>
          </#if>
          <!-- end of: product card -->
        
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
<!-- <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< -->












</html>