<#ftl encoding="UTF-8"/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"] />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<#include "styles-component.ftl"/>
<style>
  .content {
    display: flex;
    flex-direction: column;
  }
  
  .sp {
    font-size: 130%;
    font-family: 'Oswald Regular', Arial, sans-serif;
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
          
          <!-- order card -->
          <#if order??>
            <div class="card">
            
              <!-- order card header -->
              <div class="card_header df jcsb">
                <p class="card_title">ORDER NUMBER: ${order.id}</p>
                <p class="card_title">ORDER DATE: ${order.orderDate?string["dd-MM-yyyy HH:mm:ss"]}</p>
                <p class="card_title">ORDER STATUS: ${order.status}</p>
              </div>
              <!-- end of: order card header -->
              
              <!-- order card content -->
              <div class="card_content">
                <div class="card_content_subcontent">
                  <span class="sp">Owner:</span> <@c.import url="/admin/users/username">
                      <@c.param name="userId" value="${order.userId}"/>
                    </@c.import>
                </div>
                <div class="card_content_subcontent">
                  <#if order.endDate??>
          		    	<span class="sp">Order end date:</span> ${order.endDate?string["dd-MM-yyyy HH:mm:ss"]}
          		    	<#else>
          		    	 <span class="sp">Order end date:</span> order not closed yet.
          	    	</#if>
                </div>
                <div class="card_content_subcontent">
                
                  <!-- order products card -->
                  <div class="card">
                    <div class="card_header">
                      <p class="card_title">ORDER PRODUCTS</p>
                    </div>
                    
                    <!-- content -->
                    <div class="card_content df fdc">
                      <#list products>
                        <#items as product>
                          <div class="card_content_subcontent df jcsb">
                            <@spring.url var="viewProductPageLink" value="/product/viewProductPage"/>
                            <p class="card_content_link"><span class="sp">Name:</span> <a href="${viewProductPageLink}?productId=${product.getKey().id}" class="card_content_link">${product.getKey().name}</a></p>
                            <p class="card_content_link"><span class="sp">Info:</span> ${product.getKey().info}</p>
                            <p class="card_content_link"><span class="sp">Count:</span> ${product.getValue()}</p>
                          </div>
                          <sep>
                            <div class="card_content_separator"></div>                          
                          </sep>
                        </#items>
                      </#list>
                    </div>
                    <!-- end of: content -->
                    
                  </div>
                  <!-- end of: order products card -->
                  
                </div>
              </div>
              <!-- end of: order card content -->
              
              <!-- order card footer -->
              <div class="card_footer">
                <div class="card_content_subcontent">
                  <#if order.status = "new">
                    <@spring.url var="closeOrderLink" value="/employee/order/closeOrder"/>
                    <a href="${closeOrderLink}?id=${order.id}" class="uui-button small"><span>Close</span></a>
                    <@spring.url var="cancelOrderLink" value="/employee/order/cancelOrder"/>
                    <a href="${cancelOrderLink}?id=${order.id}" class="uui-button small"><span>Cancel</span></a>
                    <@spring.url var="readyOrderLink" value="/employee/order/readyOrder"/>
                    <a href="${readyOrderLink}?id=${order.id}" class="uui-button small"><span>Ready</span></a>
                    <#elseif order.status = "ready">
                      <@spring.url var="closeOrderLink" value="/employee/order/closeOrder"/>
                      <a href="${closeOrderLink}?id=${order.id}" class="uui-button small"><span>Close</span></a>
                      <@spring.url var="cancelOrderLink" value="/employee/order/cancelOrder"/>
                      <a href="${cancelOrderLink}?id=${order.id}" class="uui-button small"><span>Cancel</span></a>
                  </#if>
                </div>
              </div>
              <!-- end of: order card footer -->
            
            </div>
          </#if>
          <!-- end of: order card -->
          
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