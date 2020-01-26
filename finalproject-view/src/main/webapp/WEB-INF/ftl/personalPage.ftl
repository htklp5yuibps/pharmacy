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
  .content {
    display: grid;
    grid-template-columns: repeat(5, auto);
    grid-gap: 10px;
  }
  
  .user_card {
    grid-column-start: 1;
    grid-column-end: 2;
    align-self: start;
  }
  
  .orders_card {
    grid-column-start: 2;
    grid-column-end: 6;
    align-self: start;
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
          
          <!-- user info card -->
          <div class="card user_card">
            <div class="card_header mb10">
              <p class="card_title">
                USER INFO
              </p>
            </div>
          
            <#if user??>
              <div class="card_content df fdc">
                <@spring.url var="avatar" value="/images/default/default_avatar.jpg"/>
                <div class="avatar-original asc" style="background-image: url('${avatar}')"></div>
                <p class="mt10"><span class="sp">USERNAME:</span> ${user.username}</p>
                <p><span class="sp">NAME:</span> ${user.name}</p>
                <p><span class="sp">SURNAME:</span> ${user.surname}</p>
                <p><span class="sp">PATRONYMIC:</span> ${user.patronymic}</p>
                <p><span class="sp">ROLE:</span> ${user.role}</p>
              </div>
              <div class="card_footer">
                <div class="card_content_subcontent df fdr">
                  <@spring.url var="user_personalDataEditionPage" value="/user/personalDataEditionPage"/>
                  <a href="${user_personalDataEditionPage}" class="uui-button"><span>Update N.S.P.</span></a>
                  <@spring.url var="user_passwordChangingPage" value="/user/passwordChangingPage"/>
                  <a href="${user_passwordChangingPage}" class="uui-button ml5"><span>Update password</span></a>              
                </div>
              </div>
            </#if>
          </div>
          <!-- end of: user info card -->
          
          <!-- orders card -->
          <div class="card orders_card">
            <div class="card_header mb10">
              <p class="card_title">
                ORDERS HISTORY
              </p>
            </div>
            
            <div class="card_content">
              <#if RequestParameters.created??>
                <div class="card_content_subcontent">
                  <p class="myui_message-success">
                    ORDER WAS SUCCESSFULLY CREATED.
                  </p>
                </div>
              </#if>
            
              <#if orders??>
                <#list orders>
                  <#items as order>
                    <div class="card mb10">
                      
                      <div class="card_header df jcsb">
                        <@spring.url var="viewOrderPagelink" value="/user/order/view"/>
                        <p class="card_title">
                          <a href="${viewOrderPagelink}?orderId=${order.id}" class="card_title-link"><span>ORDER NUMBER: ${order.id}</span></a>
                        </p>
                        <p class="card_title">
                          ORDER DATE: ${order.orderDate?string["dd-MM-yyyy HH:mm:ss"]}
                        </p>
                        <p class="card_title">
                          <#if order.endDate??>
                            ORDER END DATE: ${order.endDate?string["dd-MM-yyyy HH:mm:ss"]}
                            <#else>
                            ORDER END DATE: NOT CLOSED YET
                          </#if>
                        </p>
                        <p class="card_title">
                          PARTS: ${order.parts?size}
                        </p>
                        <p class="card_title">
                          ORDER STATUS: ${order.status}
                        </p>
                      </div>
                      
                      <div class="card_content df fdrr">
                        <#if order.status != "closed" & order.status != "canceled">
                          <div class="card_content_subcontent df fdrr">
                            <@spring.url var="cancelOrderLink" value="/employee/order/cancelOrder"/>
                            <a class="uui-button small" href="${cancelOrderLink}?id=${order.id}"><span>CANCEL ORDER</span></a>
                          </div>
                        </#if>
                      </div>
                    </div>
                  </#items>
                </#list>
              </#if>
            
            </div>
          </div>
          <!-- end of: orders card -->
          
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