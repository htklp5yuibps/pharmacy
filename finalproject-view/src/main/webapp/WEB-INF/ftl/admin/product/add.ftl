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

<#include "../../styles-component.ftl"/>
<style>
  .content {
    display: flex;
    flex-direction: column;
  }
</style>
</head>
<body>
  <!-- wrapper -->
  <div class="wrapper">
    <#include "../../header-menu-component.ftl"/>
    
    <div class="uui-main-container">
    
      <!-- main -->
      <main>
        
        <!-- content -->
        <div class="content">
          <#if RequestParameters.success??>
            <p class="myui_message-success">Adding success.</p>
            <#elseif RequestParameters.error??>
              <p class="myui_message-error">Adding operation failed.</p>
          </#if>
          <!-- adding form -->
          <@spring.url var="addNewProductCommandLink" value="/admin/product/add"/>
          <@form.form class="card fg1" action="${addNewProductCommandLink}" method="post">
            <!-- adding form header -->
            <div class="card_header">
              <p class="card_title">ADD NEW PRODUCT FORM</p>
            </div>
            <!-- end of: adding form header -->
            
            <!-- adding form content -->
            <div class="card_content">
              <div class="card_content_subcontent df">
                <input type="text" name="name" placeholder="Product name..." class="uui-form-element fg1"/>
              </div>
              <div class="card_content_subcontent df">
                <textarea name="info" placeholder="Product info..." class="uui-form-element fg1"></textarea>
              </div>
              <div class="card_content_subcontent df">
                <input type="text" name="cost" placeholder="Cost..." class="uui-form-element fg1"/>
              </div>
              <div class="card_content_subcontent df aic">
                <#if types??>
                  <#list types>
                    <label class="uui-label" for="type">Type</label>
                    <select id="type" name="type" class="selectpicker uui-form-element fg1 ml5">
                    <#items as type>
                      <option value="${type.ordinal()}">${type.toString()}</option>
                    </#items>
                    </select>
                  </#list>
                </#if>
              </div>
            </div>
            <!-- end of: adding form content -->
            
            <!-- adding form footer -->
            <div class="card_footer">
              <div class="card_content_subcontent df jcsb">
                <@spring.url var="productEditionPageCommandLink" value="/admin/productEditionPage"/>
                <a href="${productEditionPageCommandLink}" class="uui-button">Back to list</a>
                <input type="submit" value="Add product" class="uui-button">
              </div>
            </div>
            <!-- end of: adding form footer -->
            
          </@form.form>
          <!-- end of: adding form -->
          
        </div>
        <!-- end of: content -->
        
      </main>
      <!-- end of: main -->

    </div>
    <#include "../../footer-component.ftl"/>
  </div>
  <!-- end of: wrapper -->
  <#include "../../js-component.ftl">
</body>
</html>