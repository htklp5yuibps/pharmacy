<#ftl encoding="UTF-8" />
<#assign spring=JspTaglibs["http://www.springframework.org/tags"] />
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"] />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<#include "../styles-component.ftl"/>
<style>
  
  main > .content {
    display: flex;
  }
  .admin_panel {
    flex-grow: 1;
  }

  .admin_panel>.card_content>.info_panel {
    display: grid;
    grid-template-columns: repeat(4, auto);
    grid-auto-raws: auto;
    grid-gap: 10px;
  }
  
  .info_panel {
  	grid-template-columns: repeat(4, 0.25fr) !important;
  }
</style>
</head>
<body>
  <!-- wrapper -->
  <div class="wrapper">
    <#include "../header-menu-component.ftl"/>

    <div class="uui-main-container">

      <!-- main -->
      <main>

      <!-- content -->
      <div class="content">
        <!-- admin_panel -->
        <div class="card admin_panel">
          <!-- admin_panel header -->
          <div class="card_header">
            <p class="card_title">
              Administrative page
            </p>
          </div>
          <!-- end of: admin_panel header -->

          <!-- admin_panel content -->
          <div class="card_content">

            <!-- info panel -->
            <div class="card_content_subcontent info_panel">

              <!-- product control panel -->
              <div class="card">
                <!-- card_header -->
                <div class="card_header">
                  <p class="card_title">Product C.P.</p>
                </div>
                <!-- end of: card_header -->

                <!-- card_content -->
                <div class="card_content">
                  <div class="card_content_subcontent">
                    <@spring.url var="admin_productEditionPage"
                    value="/admin/productEditionPage"/> <a
                      href="${admin_productEditionPage}"
                      class="card_content_link"> Total product count:
                      <@c.import url="/admin/products/count"/> </a><br> <a
                      href="${admin_productEditionPage}?status=available"
                      class="card_content_link"> Available products:
                      <@c.import url="/admin/products/count"><@c.param
                      name="type" value="available"/></@c.import> </a><br> <a
                      href="${admin_productEditionPage}?status=unavailable"
                      class="card_content_link"> Unavailable products:
                      <@c.import url="/admin/products/count"><@c.param
                      name="type" value="unavailable"/></@c.import> </a><br>
                  </div>
                </div>
                <!-- end of: card_content -->

              </div>
              <!-- end of: prodcut control panel -->

              <!-- order control panel -->
              <div class="card">
                <!-- card_header -->
                <div class="card_header">
                  <p class="card_title">Order C.P.</p>
                </div>
                <!-- end of: card_header -->

                <!-- card_content -->
                <div class="card_content">
                  <div class="card_content_subcontent">
                    <@spring.url var="employee_orderEditionPage" value="/employee/orderEditionPage"/>
                    <a href="${employee_orderEditionPage}" class="card_content_link">
                      Total order count: <@c.import url="/admin/orders/count"/>
                    </a><br>
                    <a href="${employee_orderEditionPage}?show=new"  class="card_content_link">
                      New Orders: <@c.import url="/admin/orders/count"><@c.param name="status" value="new"/></@c.import>
                    </a><br>
                    <a href="${employee_orderEditionPage}?show=closed"  class="card_content_link">
                      Closed orders: <@c.import url="/admin/orders/count"><@c.param name="status" value="closed"/></@c.import>
                    </a><br>
                    <a href="${employee_orderEditionPage}?show=canceled"  class="card_content_link">
                      Canceled orders: <@c.import url="/admin/orders/count"><@c.param name="status" value="canceled"/></@c.import>
                    </a><br>
                    <a href="${employee_orderEditionPage}?show=ready"  class="card_content_link">
                      Ready orders: <@c.import url="/admin/orders/count"><@c.param name="status" value="ready"/></@c.import>
                    </a><br>
                  </div>
                </div>
                <!-- end of: card_content -->

              </div>
              <!-- end of: order control panel -->

              <!-- access level panel -->
              <div class="card">
                <!-- card_header -->
                <div class="card_header">
                  <p class="card_title">Access level C.P.</p>
                </div>
                <!-- end of: card_header -->

                <!-- card_content -->
                <div class="card_content">
                  <div class="card_content_subcontent">
                    <@spring.url var="admin_accessLevelEditionPage" value="/admin/accessLevelEditionPage"/>
                    <a href="${admin_accessLevelEditionPage}"  class="card_content_link">
                    Total user count: <@c.import url="/admin/users/count"/>
                    </a><br>
                    <a href="${admin_accessLevelEditionPage}?show=admin" class="card_content_link">
                      Administrators in system: <@c.import url="/admin/users/count"><@c.param name="role" value="admin"/></@c.import>
                    </a><br>
                    <a href="${admin_accessLevelEditionPage}?show=employee" class="card_content_link">
                      Employee in system: <@c.import url="/admin/users/count"><@c.param name="role" value="employee"/></@c.import>
                    </a><br> <a href="${admin_accessLevelEditionPage}?show=user" class="card_content_link">
                      Users in system: <@c.import url="/admin/users/count"><@c.param name="role" value="user"/></@c.import>
                    </a><br>
                  </div>
                </div>
              </div>
              <!-- end of: access level panel -->
              
              <!-- images control panel -->
              <div class="card">
              	<!-- card header -->              
              	<div class="card_header">
              		<p class="card_title">Images C.P.</p>
              	</div>
              	<!-- end of: card header -->
              	
              	<!-- card content -->
              	<div class="card_content">
              		<div class="card_content_subcontent">
						Total images count: <@c.import url="/admin/image/count"/><br>
						Product images count: <@c.import url="/admin/image/count"><@c.param name="type" value="productImage"/></@c.import><br>
						User avatars count: <@c.import url="/admin/image/count"><@c.param name="type" value="avatar"/></@c.import>
              		</div>
              	</div>
              	<!-- end of: card content -->
              </div>
              <!-- end of: imagescontrol panel -->
              
            </div>
            <!-- end of: info panel -->
            
            <!-- link panel -->
            <div class="card_content_subcontent links_panel">
              <div class="card">
                <div class="card_header">
                  <p class="card_title">Links</p>
                </div>
                <div class="card_content">
                  <div class="card_content_subcontent">
                    <@spring.url var="admin_productEditionPage" value="/admin/productEditionPage"/>
                    <a href="${admin_productEditionPage}" class="card_content_link">
                      Product edition
                    </a><br>
                    <@spring.url var="employee_orderEditionPage"
                    value="/employee/orderEditionPage"/>
                    <a href="${employee_orderEditionPage}" class="card_content_link">
                      Order edition
                    </a><br>
                    <@spring.url var="admin_accessLevelEditionPage" value="/admin/accessLevelEditionPage"/>
                    <a href="${admin_accessLevelEditionPage}" class="card_content_link">
                      Access level edition
                    </a><br>
                  </div>
                </div>
              </div>
            </div>
            <!-- end of: link panel -->
            
          </div>
          <!-- end of: card_content -->
        </div>
        
      </div>
      <!-- end of: admin_panel content -->
    </div>
    <!-- end of: admin_panel -->
  </div>
  <!-- end of: content -->
  </main>
  <!-- end of: main -->

  </div>
  <#include "../footer-component.ftl"/>
  </div>
  <!-- end of: wrapper -->
  <#include "../js-component.ftl">
</body>
</html>


