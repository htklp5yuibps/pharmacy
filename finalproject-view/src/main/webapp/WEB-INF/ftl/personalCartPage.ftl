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
        
        <!-- content -->
        <div class="content df fdc">
          
          <#if RequestParameters.cleared??>
            <p class="myui_message-success">
              Cart was successfully cleared.
            </p>
            <#elseif RequestParameters.created??>
              <p class="myui_message-success">
                Order was successfully created.
              </p>
            <#elseif RequestParameters.error??>
              <p class="myui_message-error">
                There was some error while processing request.
              </p>
          </#if>
            
          <!-- cart card -->
          <div class="card">
            
            <!-- cart header -->
            <div class="card_header df fdr jcsb mb10 aic">
              <p class="card_title">
                Cart of: <@security.authentication property="principal.username"/>
              </p>
              <#if products?? & products?size != 0>
                <div class="df">
                  <@spring.url var="createOrderCommandLink" value="/cart/createOrder"/>
                  <a class="uui-button small" href="${createOrderCommandLink}"><span>CREATE ORDER</span></a>
                  <@spring.url var="clearCartCommandLink" value="/cart/clearCart"/>
                  <a class="uui-button small ml5" href="${clearCartCommandLink}"><span>CLEAR CART</span></a>              
                </div>
              </#if>
            </div>
            <!-- end of: cart header -->
          
            <!-- cart content -->
            <div class="card_content df fdc">
            
              <#if products??>
                <#list products>
                  <#items as product>
                    <div class="card mb10">
                      <div class="card_header df fdr jcsb aic">
                        <div>
                          <p class="card_title">
                          NAME: ${product.name}
                          </p>
                          <p class="card_title">TOTAL COST: ${product.cost * Session.cart.getPartItemsCount(product.id)}</p> 
                        </div>
                        <@spring.url var="deleteFromCartCommandLink" value="/cart/deleteFromCart?productId=${product.id}"/>
                        <a href="${deleteFromCartCommandLink}" class="uui-button"><span>Delete from cart</span></a>
                      </div>
                    </div>
                  </#items>
                </#list>
              </#if>
            
            </div>
            <!-- end of: cart content -->
          
            <!-- cart footer -->
            <div class="card_footer">
            
            </div>
            <!-- end of: cart footer -->
          </div>
          <!-- end of: cart card -->
          
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