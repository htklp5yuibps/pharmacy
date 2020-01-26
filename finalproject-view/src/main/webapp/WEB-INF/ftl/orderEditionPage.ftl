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
  .uui-checkbox {
    margin-bottom: 0;
  }
  
  .uui-checkbox label {
    color: white !important;
  }
  
  .content {
    display: grid;
    grid-template-columns: repeat(2, auto);
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
        <div class="myui_search_bar">
          <div class="myui_search_sub">
            <@spring.url var="employee_orderEditionPage" value="/employee/orderEditionPage"/>
            <a href="${employee_orderEditionPage}" class="uui-button ml5">Show all</a>
            <a href="${employee_orderEditionPage}?show=new" class="uui-button ml5">Show only new</a>
            <a href="${employee_orderEditionPage}?show=ready" class="uui-button ml5">Show only ready</a>
            <a href="${employee_orderEditionPage}?show=closed" class="uui-button ml5">Show only closed</a>
            <a href="${employee_orderEditionPage}?show=canceled" class="uui-button ml5">Show only canceled</a>
          </div>
          <div class="myui_search_separator"></div>
          <@form.form class="myui_search_sub df aic" action="${employee_orderEditionPage}" method="get">
            <input type="text" placeholder="Username of owner..." name="username" class="uui-form-element"/>
            <input type="submit" value="Search.." class="uui-button">
            <p class="uui-checkbox large ml5">
              <input type="checkbox" id="a1" name="strict"/>
              <label for="a1">strict</label>
            </p>
          </@form.form>
        </div>
        
        <!-- content -->
        <div class="content">
          
          <!-- order card -->
          <#if orders??>
            <#list orders>
              <#items as order>
                <div class="card">
                  <!-- order card header -->
                  <div class="card_header df jcsb">
                    <p class="card_title">ORDER NUMBER: ${order.id}</p>
                    <p class="card_title">ORDER DATE: ${order.orderDate?string["dd-MM-yyyy HH:mm:ss"]}</p>
                    <p class="card_title">STATUS: ${order.status}</p>
                  </div>
                  <!-- end of: order card header -->
                  
                  <!-- order card content -->
                  <div class="card_content">
                    <div class="card_content_subcontent">
                      Owner: <@c.import url="/admin/users/username">
                        <@c.param name="userId" value="${order.userId}"/>
                      </@c.import>
                    </div>
                    <div class="card_content_subcontent">
                      <#if order.endDate??>
                        Order end-date: ${order.endDate?string["dd-MM-yyyy HH:mm:ss"]}<br>
                        <#else>
                        Order end-date: Order not closed yet.<br>
                      </#if>
                    </div>
                    <div class="card_content_subcontent">
                      <@spring.url var="viewOrderLink" value="/employee/order/viewOrder"/>
                      <a href="${viewOrderLink}?id=${order.id}" class="card_content_link">Order consist of: ${order.parts?size}</a>
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
              </#items>
            </#list>
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